package org.refact4j.function;

/**
 * Convert an object to a String. The intent of this is to provide a flexible
 * means to control the string representation of an Object. The toString()
 * routine has many issues, including: - appropriateness for end-user viewing -
 * an object may not have implemented a toString() method - the default output
 * of toString() may simply be unacceptable (eg class@eebc1933) - it may be
 * desirable to have many variations on the output - entityObjectdifying
 * toString() requires entityObjectdifying the original class; a Stringifier or
 * many of them can exist independently, making it easy to apply many different
 * types of formatting to the same class.
 * <p>
 * The intended use is generally to have a separate class implement Stringifier,
 * rather than the class to be stringified.
 *
 * @param <T>
 */
@FunctionalInterface
public interface Stringifier<T> /*extends java.util.function.Function<T, String>*/ {

    /**
     * Default implementation of a Stringifier calls the toString method of the
     * object or null if the object is null.
     */
    Stringifier DEFAULT = (arg) -> (arg != null ? arg.toString() : "null");

    /**
     * Produce a String representation of an object.
     *
     * @param arg the Object for which a String should be produced
     * @return String string
     */
    String stringify(T arg);

}
