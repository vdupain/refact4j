package org.refact4j.evt;

import org.refact4j.evt.EventLogger.Log;
import org.refact4j.util.MethodHelper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class EventLoggerInvocationHandler implements InvocationHandler {

    private final Object target;
    private final EventLogger eventLogger;

    public EventLoggerInvocationHandler(Object target, EventLogger eventLogger) {
        this.target = target;
        this.eventLogger = eventLogger;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            if (!MethodHelper.isToStringMethod(method)) {
                Log log = eventLogger.log(target.getClass().getSimpleName() + "." + method.getName());
                if (args != null) {
                    for (Object object : args) {
                        log.add(object.getClass().getSimpleName(), object);
                    }
                }
            }
            result = method.invoke(target, args);
        } finally {
        }
        return result;
    }

}
