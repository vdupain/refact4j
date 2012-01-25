package org.refact4j.util;

public class CheckExitSecurityManager extends SecurityManagerDecorator {

    public CheckExitSecurityManager(SecurityManager securityManager) {
        super(securityManager);
    }

    public void checkExit(int status) {
        throw new SecurityManagerSecurityException(status);
    }
}
