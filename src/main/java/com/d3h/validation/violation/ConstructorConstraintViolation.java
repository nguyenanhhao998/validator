package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public class ConstructorConstraintViolation extends ConstraintViolation {
    private final Constructor<Object> constructor;
    private final Object[] executableParameters;

    public ConstructorConstraintViolation(String message, Annotation annotation,
                                          Constructor<Object> constructor,
                                          Object[] executableParameters) {
        this.message = message;
        this.annotation = annotation;
        this.constructor = constructor;
        this.executableParameters = executableParameters;
    }

    @Override
    public Object[] getExecutableParameters() {
        return executableParameters;
    }

    @Override
    public Constructor<Object> getConstructor() {
        return constructor;
    }
}
