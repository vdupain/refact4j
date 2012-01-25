package org.refact4j.util;

public class SecurityManagerSecurityException extends SecurityException {
    private int status;

    public SecurityManagerSecurityException(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}