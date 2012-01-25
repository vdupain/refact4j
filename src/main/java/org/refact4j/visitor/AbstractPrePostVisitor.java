package org.refact4j.visitor;

/**
 * This abstract class provides a skeletal implementation of the PrePostVisitor
 * interface, to minimize the effort required to implement this interface.
 */
public abstract class AbstractPrePostVisitor extends AbstractVisitor implements PrePostVisitor {

    public void postVisit(Object object) {
        visitImpl("postVisit", object);
    }

    public void preVisit(Object object) {
        visitImpl("preVisit", object);

    }

    public void postVisit(Visitable visitable) {
    }

    public void preVisit(Visitable visitable) {
    }

}
