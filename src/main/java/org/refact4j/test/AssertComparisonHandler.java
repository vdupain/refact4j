package org.refact4j.test;

import org.refact4j.function.BinaryPredicate;

interface AssertComparisonHandler<T> extends BinaryPredicate<T, T>, AssertionHandler {
}
