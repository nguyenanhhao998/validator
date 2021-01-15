package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FieldConstraintViolation implements ConstraintViolation{
    String message;
    Annotation annotation;
    Object invalidValue;
    Field field;

    public FieldConstraintViolation(String message, Annotation annotation, Object invalidValue, Field field) {
        this.message = message;
        this.annotation = annotation;
        this.invalidValue = invalidValue;
        this.field = field;
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
        return invalidValue;
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Object[] getExecutableParameters() {
        return null;
    }

    @Override
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Constructor getConstructor() {
        return null;
    }

}
