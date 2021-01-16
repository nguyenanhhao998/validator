package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodConstraintViolation implements ConstraintViolation {
    String message;
    Method method;
    Annotation annotation;
    Object executableReturnValue;

    public MethodConstraintViolation(String message, Annotation annotation,
                                     Method method, Object executableReturnValue) {
        this.message = message;
        this.method = method;
        this.annotation = annotation;
        this.executableReturnValue = executableReturnValue;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Annotation getAnnotation() {
        return annotation;
    }

    @Override
    public Object getExecutableReturnValue() {
        return executableReturnValue;
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
    public Object[] getExecutableParameters() {
        return null;
    }

    @Override
    public Constructor getConstructor() {
        return null;
    }

    @Override
    public Parameter getParameter() {
        return null;
    }
}
