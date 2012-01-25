package org.refact4j.util.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

public class AsyncCallTest extends TestCase {
	static final long AWAIT_TERMINATION_TIME = 1000L;
	final RuntimeException runtimeException = new RuntimeException("a runtime exception");

	private AsyncCallServiceImpl<Object[], String> echoService = new AsyncCallServiceImpl<Object[], String>() {
		@Override
		public String callSync(Object[] params) {
			try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return (String) params[0];
		}
	};
	private AsyncCallServiceImpl<Object, Object> errorService = new AsyncCallServiceImpl<Object, Object>() {

		@Override
		public Object callSync(Object arg) {
			throw runtimeException;
		}
	};

	public void testAsyncCall() throws Exception {
		Response<AsyncCallServiceResult<String>> resp1 = echoService.callAsync(new Object[] { "aaa" });
		Response<AsyncCallServiceResult<String>> resp2 = echoService.callAsync(new Object[] { "bbb" });
		assertFalse(resp1.isDone());
		assertFalse(resp2.isDone());
		assertTrue(resp1.cancel(true));
		assertTrue(resp1.isDone());
		while (!resp2.isDone()) {
		}
		AsyncCallServiceResult<String> result = resp2.get();
		assertEquals("bbb", result.get());
	}

	public void testAsyncCallWithException() throws Exception {
		Response<AsyncCallServiceResult<Object>> resp = errorService.callAsync(null);
		while (!resp.isDone()) {
		}
		try {
			resp.get();
			fail("ExecutionException Expected");
		} catch (ExecutionException e) {
			assertSame(runtimeException, e.getCause());
			assertTrue(resp.isDone());
		}
	}

	class _AsyncHandler<T> implements AsyncHandler<AsyncCallServiceResult<T>> {
		private final T expected;

		public _AsyncHandler(T expected) {
			this.expected = expected;
		}

		public void handleResponse(Response<AsyncCallServiceResult<T>> response) {
			System.err.println("_AsyncHandler.handleResponse()" + response);
			assertTrue(response.isDone());
			AsyncCallServiceResult<T> result = null;
			try {
				result = response.get();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			T actual = result.get();
			assertEquals(expected, actual);
		}

	}

	public void testAsyncCallWithHandler() throws Exception {
		AsyncHandler<AsyncCallServiceResult<String>> handler1 = new _AsyncHandler<String>("aaa");
		AsyncHandler<AsyncCallServiceResult<String>> handler2 = new _AsyncHandler<String>("bbb");

		Response<AsyncCallServiceResult<String>> resp1 = echoService.callAsync(new Object[] { "aaa" }, handler1);
		Response<AsyncCallServiceResult<String>> resp2 = echoService.callAsync(new Object[] { "bbb" }, handler2);
		while (!resp1.isDone() && !resp2.isDone()) {
		}
		System.err.println("resp=" + resp1);
		assertEquals("aaa", resp1.get().get());
		assertEquals("bbb", resp2.get().get());
	}

	private abstract class AsyncCallServiceImpl<T, R> implements AsyncCallService<T, R> {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		public abstract R callSync(T arg);

		public Response<AsyncCallServiceResult<R>> callAsync(final T arg) {
			return this.callAsync(arg, null);
		}

		public Response<AsyncCallServiceResult<R>> callAsync(final T arg,
				AsyncHandler<AsyncCallServiceResult<R>> handler) {
			AsyncInvoker<AsyncCallServiceResult<R>> invoker = new AsyncInvoker<AsyncCallServiceResult<R>>() {
				public void run() {
					final R value;
					value = callSync(arg);
					AsyncCallServiceResult<R> result = new AsyncCallServiceResult<R>() {
						public R get() {
							return value;
						}
					};
					getResponse().setResult(result, null);
				}
			};
			AsyncResponseImpl<AsyncCallServiceResult<R>> asyncResponse = new AsyncResponseImpl<AsyncCallServiceResult<R>>(
					invoker, handler);
			invoker.setResponse(asyncResponse);
			// temp needed so that unit tests run and complete otherwise they
			// may
			// not.
			try {
				executorService.awaitTermination(AWAIT_TERMINATION_TIME, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			executorService.execute(asyncResponse);
			return asyncResponse;
		}

	}

	interface AsyncCallService<T, R> {
		R callSync(T arg);

		Response<AsyncCallServiceResult<R>> callAsync(T arg);

		Response<AsyncCallServiceResult<R>> callAsync(T arg, AsyncHandler<AsyncCallServiceResult<R>> handler);

	}

	public interface AsyncCallServiceResult<T> {
		T get();
	}
}
