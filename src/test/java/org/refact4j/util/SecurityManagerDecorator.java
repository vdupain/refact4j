package org.refact4j.util;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

public class SecurityManagerDecorator extends SecurityManager {

    private SecurityManager securityManager;

    public SecurityManagerDecorator(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    public void checkExit(int status) {
        if (securityManager != null)
            securityManager.checkExit(status);
    }

    public void checkAccept(String host, int port) {
        if (securityManager != null)
            securityManager.checkAccept(host, port);
    }

    public void checkAccess(Thread t) {
        if (securityManager != null)
            securityManager.checkAccess(t);
    }

    public void checkAccess(ThreadGroup g) {
        if (securityManager != null)
            securityManager.checkAccess(g);
    }

    public void checkAwtEventQueueAccess() {
        if (securityManager != null)
            securityManager.checkAwtEventQueueAccess();
    }

    public void checkConnect(String host, int port, Object context) {
        if (securityManager != null)
            securityManager.checkConnect(host, port, context);
    }

    public void checkConnect(String host, int port) {
        if (securityManager != null)
            securityManager.checkConnect(host, port);
    }

    public void checkCreateClassLoader() {
        if (securityManager != null)
            securityManager.checkCreateClassLoader();
    }

    public void checkDelete(String file) {
        if (securityManager != null)
            securityManager.checkDelete(file);
    }

    public void checkExec(String cmd) {
        if (securityManager != null)
            securityManager.checkExec(cmd);
    }

    public void checkLink(String lib) {
        if (securityManager != null)
            securityManager.checkLink(lib);
    }

    public void checkListen(int port) {
        if (securityManager != null)
            securityManager.checkListen(port);
    }

    public void checkMemberAccess(Class<?> clazz, int which) {
        if (securityManager != null)
            securityManager.checkMemberAccess(clazz, which);
    }

    /**
     * @deprecated
     */
    public void checkMulticast(InetAddress maddr, byte ttl) {
        if (securityManager != null)
            securityManager.checkMulticast(maddr, ttl);
    }

    public void checkMulticast(InetAddress maddr) {
        if (securityManager != null)
            securityManager.checkMulticast(maddr);
    }

    public void checkPackageAccess(String pkg) {
        if (securityManager != null)
            securityManager.checkPackageAccess(pkg);
    }

    public void checkPackageDefinition(String pkg) {
        if (securityManager != null)
            securityManager.checkPackageDefinition(pkg);
    }

    public void checkPermission(Permission perm, Object context) {
        if (securityManager != null)
            securityManager.checkPermission(perm, context);
    }

    public void checkPermission(Permission perm) {
        if (securityManager != null)
            securityManager.checkPermission(perm);
    }

    public void checkPrintJobAccess() {
        if (securityManager != null)
            securityManager.checkPrintJobAccess();
    }

    public void checkPropertiesAccess() {
        if (securityManager != null)
            securityManager.checkPropertiesAccess();
    }

    public void checkPropertyAccess(String key) {
        if (securityManager != null)
            securityManager.checkPropertyAccess(key);
    }

    public void checkRead(FileDescriptor fd) {
        if (securityManager != null)
            securityManager.checkRead(fd);
    }

    public void checkRead(String file, Object context) {
        if (securityManager != null)
            securityManager.checkRead(file, context);
    }

    public void checkRead(String file) {
        if (securityManager != null)
            securityManager.checkRead(file);
    }

    public void checkSecurityAccess(String target) {
        if (securityManager != null)
            securityManager.checkSecurityAccess(target);
    }

    public void checkSetFactory() {
        if (securityManager != null)
            securityManager.checkSetFactory();
    }

    public void checkSystemClipboardAccess() {
        if (securityManager != null)
            securityManager.checkSystemClipboardAccess();
    }

    public boolean checkTopLevelWindow(Object window) {
        if (securityManager != null)
            return securityManager.checkTopLevelWindow(window);
        return false;
    }

    public void checkWrite(FileDescriptor fd) {
        if (securityManager != null)
            securityManager.checkWrite(fd);
    }

    public void checkWrite(String file) {
        if (securityManager != null)
            securityManager.checkWrite(file);
    }

    /**
     * @deprecated
     */
    public boolean getInCheck() {
        if (securityManager != null)
            return securityManager.getInCheck();
        return false;
    }

    public Object getSecurityContext() {
        if (securityManager != null)
            return securityManager.getSecurityContext();
        return null;
    }

    public ThreadGroup getThreadGroup() {
        if (securityManager != null)
            return securityManager.getThreadGroup();
        return null;
    }

}
