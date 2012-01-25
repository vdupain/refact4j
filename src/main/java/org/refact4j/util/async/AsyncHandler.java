package org.refact4j.util.async;

public interface AsyncHandler<T> {

    void handleResponse(Response<T> response);
}
