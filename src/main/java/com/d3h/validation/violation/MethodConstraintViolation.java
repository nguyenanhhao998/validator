package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MethodConstraintViolation extends ConstraintViolation {
    private final Method method;
    private final Object executableReturnValue;

    public MethodConstraintViolation(String message, Annotation annotation,
                                     Method method, Object executableReturnValue) {
        this.message = message;
        this.method = method;
        this.annotation = annotation;
        this.executableReturnValue = executableReturnValue;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object getExecutableReturnValue() {
        return executableReturnValue;
    }
}
