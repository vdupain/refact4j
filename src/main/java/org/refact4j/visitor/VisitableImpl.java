package org.refact4j.visitor;

/**
 * VisitableImpl is a default implementation of the Visitable interface.
 *
 * @param <T>
 */
public class VisitableImpl<T> implements Visitable {
    private final T object;

    public VisitableImpl(T object) {
        this.object = object;
    }

    void accept(AbstractVisitor visitor) {
        visitor.visit(this.object);
    }

    public void accept(Visitor visitor) {
        this.accept((AbstractVisitor) visitor);
    }

}
