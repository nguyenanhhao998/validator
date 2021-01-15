package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MethodConstraintViolation implements ConstraintViolation{
    String message;
    Annotation annotation;
    Method method;
    Object[] executableParameters;
    Object executableReturnValue;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Annotation getAnnotation() {
        return annotation;
    }

    @Override
    public Object getInvalidValue() {
        return null;
    }

    @Override
    public Field getField() {
        return null;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getExecutableParameters() {
        return executableParameters;
    }

    @Override
    public Object getExecutableReturnValue() {
        return executableReturnValue;
    }

    @Override
    public Constructor getConstructor() {
        return null;
    }
}
