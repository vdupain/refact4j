package org.refact4j.util.async;

import java.util.concurrent.FutureTask;

public class ResponseImpl<T> extends FutureTask<T> implements Response<T> {

	protected final Runnable runnable;

	public ResponseImpl(Runnable runnable, T result) {
		super(runnable, result);
		this.runnable = runnable;
	}

	public void setResult(T result, Throwable throwable) {
		if (throwable != null) {
			this.setException(throwable);
		} else
			this.set(result);
	}

}
