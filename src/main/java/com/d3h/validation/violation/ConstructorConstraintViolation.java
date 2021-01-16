package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ConstructorConstraintViolation implements ConstraintViolation {
    String message;
    Annotation annotation;
    Constructor constructor;
    Object[] executableParameters;

    public ConstructorConstraintViolation(String message, Annotation annotation, Constructor constructor, Object[] executableParameters) {
        this.message = message;
        this.annotation = annotation;
        this.constructor = constructor;
        this.executableParameters = executableParameters;
    }

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
        return null;
    }

    @Override
    public Object[] getExecutableParameters() {
        return executableParameters;
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Constructor getConstructor() {
        return constructor;
    }

    @Override
    public Parameter getParameter() {
        return null;
    }
}
