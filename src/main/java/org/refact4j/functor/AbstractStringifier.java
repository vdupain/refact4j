package org.refact4j.functor;

/**
 * This abstract class provides a skeletal implementation of the Stringifier
 * interface, to minimize the effort required to implement this interface. To
 * implement a stringifier, the programmer needs only to extend this class and
 * provide implementation for the stringify method.
 *
 * @param <T>
 */
public abstract class AbstractStringifier<T> implements Stringifier<T> {

    /**
     * Default implementation of a Stringifier calls the toString method of the
     * object or null if the object is null.
     */
    public static final AbstractStringifier<Object> DEFAULT = new AbstractStringifier<Object>() {

        @Override
        public String stringify(Object arg) {
            return arg != null ? arg.toString() : "null";
        }

    };

    /**
     * Produce a String representation of an object.
     *
     * @param arg the Object for which a String should be produced
     * @return
     */
    public abstract String stringify(T arg);

    public String apply(T arg) {
        return this.stringify(arg);
    }

}
