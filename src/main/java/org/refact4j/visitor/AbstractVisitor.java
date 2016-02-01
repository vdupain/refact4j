package org.refact4j.visitor;

import java.lang.reflect.Method;

/**
 * This abstract class provides a skeletal implementation of the Visitor
 * interface, to minimize the effort required to implement this interface. This
 * abstract implementation uses introspection to allow visiting objects that do
 * not implement the Visitor interface.
 */
public abstract class AbstractVisitor implements Visitor {

    private void defaultVisit(Object o) throws Exception {
    }

    private Method getMethod(String visitMethodName, Class<?> clazz) {
        Class<?> cl = clazz; // the bottom-most class
        // Check through superclasses for matching method
        while (cl != null && !cl.equals(Object.class)) {
            try {
                return this.getClass().getDeclaredMethod(visitMethodName, cl);
            } catch (NoSuchMethodException ex) {
                cl = cl.getSuperclass();
            }
        }
        // Check through interfaces for matching method;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            // System.out.println("interface:" + interfaces[i]);
            try {
                String visitMethod = "visit";
                return this.getMethod(visitMethod + anInterface.getSimpleName(), anInterface);
            } catch (Exception ex) {
                //ignored
            }
        }
        return null;
    }

    public void visit(Object object) {
        visitImpl("visit" + object.getClass().getSimpleName(), object);
    }

    public void visit(Visitable visitable) {
    }

    private void visitImpl(String visitMethodName, Object object) {
        Method method;
        try {
            method = getMethod(visitMethodName, object.getClass());
            if (method == null) {
                defaultVisit(object);
            } else {
                method.invoke(this, object);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
