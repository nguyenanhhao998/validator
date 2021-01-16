package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public abstract class ConstraintViolation {
    protected String message;
    protected Annotation annotation;

    public String getMessage() {
        return message;
    }
    public Annotation getAnnotation() {
        return annotation;
    }
    public Object getInvalidValue() {
        return null;
    };
    public Field getField() {
        return null;
    };
    public Method getMethod() {
        return null;
    };
    public Object[] getExecutableParameters() {
     return null;
    }
    public Object getExecutableReturnValue() {
        return null;
    }
    public Constructor getConstructor() {
        return null;
    }
    public Parameter getParameter() {
        return null;
    }
}
