package org.refact4j.util;

import java.beans.PropertyChangeSupport;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BeanPropertyChangeSupportProxy implements InvocationHandler {
    private PropertyChangeSupport propertyChangeSupport;
    private Object bean;

    private BeanPropertyChangeSupportProxy(Object bean, PropertyChangeSupport propertyChangeSupport) {
        this.bean = bean;
        this.propertyChangeSupport = propertyChangeSupport;
    }

    public static Object createProxy(Object bean, PropertyChangeSupport propertyChangeSupport) {
        int interfaceCount = bean.getClass().getInterfaces().length;
        if (interfaceCount == 0) {
            throw new IllegalArgumentException("object to proxy must implement an interface.");
        }
        Class[] interfaces = new Class[interfaceCount];
        System.arraycopy(bean.getClass().getInterfaces(), 0, interfaces, 0, interfaceCount);
        ClassLoader cl = bean.getClass().getClassLoader();
        return Proxy.newProxyInstance(cl, interfaces, new BeanPropertyChangeSupportProxy(bean, propertyChangeSupport));
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // mutator call
        if (BeanHelper.isMutator(method) && args.length == 1) {
            // get the previous value from our bean spec'd accessor
            String property = BeanHelper.getPropertyName(method.getName());
            Method acessor = BeanHelper.getAccessor(bean.getClass(), property);
            Object oldValue = acessor != null ? acessor.invoke(bean) : null;
            Object result = method.invoke(bean, args);
            propertyChangeSupport.firePropertyChange(property, oldValue, args[0]);
            return result;
        }
        return method.invoke(bean, args);
    }
}
