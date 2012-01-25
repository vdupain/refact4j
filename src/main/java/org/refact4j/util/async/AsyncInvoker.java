package org.refact4j.util.async;

public abstract class AsyncInvoker<T> implements Runnable {
    private AsyncResponseImpl<T> responseImpl;

    public void setResponse(AsyncResponseImpl<T> responseImpl) {
        this.responseImpl = responseImpl;
    }

    public AsyncResponseImpl<T> getResponse() {
        return this.responseImpl;
    }
}
