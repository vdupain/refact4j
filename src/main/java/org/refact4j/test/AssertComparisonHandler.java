package org.refact4j.test;

import org.refact4j.functor.BinaryPredicate;

interface AssertComparisonHandler<T> extends BinaryPredicate<T, T>, AssertionHandler {
}
