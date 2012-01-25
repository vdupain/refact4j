package org.refact4j.util.async;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class DoneFuture<T> implements Future<T> {
    private final T v;
    private final Throwable throwable;

    public DoneFuture(T v, Throwable throwable) {
        this.v = v;
        this.throwable = throwable;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }

    public T get() throws ExecutionException {
        if (throwable != null) {
            throw new ExecutionException(throwable);
        }
        return v;
    }

    public T get(long timeout, TimeUnit unit) throws ExecutionException {
        return get();
    }
}
