package org.refact4j.util;

import java.lang.reflect.Method;

public class MethodHelper {

	private MethodHelper() {
	}

	public static boolean isToStringMethod(Method method) {
		return method.getName().equals("toString") && method.getGenericReturnType().equals(String.class)
				&& method.getParameterTypes().length == 0;
	}

}
