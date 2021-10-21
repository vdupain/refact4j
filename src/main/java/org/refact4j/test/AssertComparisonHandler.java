package org.refact4j.test;


import java.util.function.BiPredicate;

interface AssertComparisonHandler<T> extends BiPredicate<T, T>, AssertionHandler {
}
