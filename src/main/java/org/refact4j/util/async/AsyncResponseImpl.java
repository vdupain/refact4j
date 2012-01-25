package org.refact4j.util.async;

public class AsyncResponseImpl<T> extends ResponseImpl<T> {

    private final AsyncHandler<T> asyncHandler;

    public AsyncResponseImpl(Runnable runnable, AsyncHandler<T> asyncHandler) {
        super(runnable, null);
        this.asyncHandler = asyncHandler;
    }

    @Override
    public void setResult(T result, Throwable throwable) {
//        System.out.println("AsyncResponseImpl.setResult(): result:" + result + " , throwable=" + throwable);
        // call the handler before we mark the future as 'done'
        if (asyncHandler != null) {
            try {
                /**
                 * Response object passed into the callback. We need a separate
                 * java.util.concurrent.Future because we don't want
                 * ResponseImpl to be marked as 'done' before the callback
                 * finishes execution. That would provide implicit
                 * synchronization between the application code in the main
                 * thread and the callback code.
                 */
                asyncHandler.handleResponse(new CallbackResponse<T>(result, throwable));
            } catch (Throwable e) {
                super.setException(e);
                return;
            }
        }
        super.setResult(result, throwable);
    }

    class CallbackResponse<T> extends DoneFuture<T> implements Response<T> {
        public CallbackResponse(T v, Throwable t) {
            super(v, t);
        }
    }

}
