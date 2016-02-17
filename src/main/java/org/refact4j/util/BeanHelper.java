package org.refact4j.util;

import java.lang.reflect.Method;
import java.util.stream.StreamSupport;

final class BeanHelper {
    private static final String[] ACCESSOR_PREFIXES = new String[]{"is", "get", "has"};
    private static final String[] MUTATOR_PREFIXES = new String[]{"set"};

    private BeanHelper() {
    }

    public static boolean isAccessor(Method method) {
        for (String prefix : ACCESSOR_PREFIXES) {
            if (method.getName().startsWith(prefix)
                    && method.getParameterTypes().length == 0
                    && method.getReturnType() != null) return true;
        }
        return false;
    }

    public static Method getAccessor(Class clazz, String propertyName) {
        propertyName = StringHelper.capitalize(propertyName);
        for (String prefix : ACCESSOR_PREFIXES) {
            try {
                return clazz.getMethod(prefix + propertyName);
            } catch (NoSuchMethodException ignored) {
                // ignore
            }
        }
        return null;
    }

    public static boolean isMutator(Method method) {
        for (String prefix : MUTATOR_PREFIXES) {
            if (method.getName().startsWith(prefix)) return true;
        }
        return false;
    }

    public static Method getMutator(Class clazz, String propertyName, Class... params) {
        propertyName = StringHelper.capitalize(propertyName);
        for (String prefix : MUTATOR_PREFIXES) {
            try {
                return clazz.getDeclaredMethod(prefix + propertyName, params);
            } catch (NoSuchMethodException ignored) {
                // ignore
            }
        }
        return null;
    }

    public static String getPropertyName(String methodName) throws IllegalArgumentException {
        for (String prefix : MUTATOR_PREFIXES) {
            if (methodName.startsWith(prefix)) {
                return StringHelper.uncapitalize(methodName.substring(prefix.length()));
            }
        }
        for (String prefix : ACCESSOR_PREFIXES) {
            if (methodName.startsWith(prefix)) {
                return StringHelper.uncapitalize(methodName.substring(prefix.length()));
            }
        }
        throw new IllegalArgumentException("Method '" + methodName + "' not a JavaBean method.");
    }

}
