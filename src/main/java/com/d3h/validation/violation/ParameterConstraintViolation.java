package com.d3h.validation.violation;

import com.d3h.validation.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterConstraintViolation implements ConstraintViolation {
    private final String message;
    private final Parameter parameter;
    private final Annotation annotation;
    private final Object invalidValue;
    private final Method method;

    public ParameterConstraintViolation(String message,
                                        Parameter parameter,
                                        Annotation annotation,
                                        Object invalidValue,
                                        Method method) {
        this.message = message;
        this.parameter = parameter;
        this.annotation = annotation;
        this.invalidValue = invalidValue;
        this.method = method;
    }
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Parameter getParameter() {
        return parameter;
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
    public Method getMethod() {
        return method;
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
    public Object getExecutableReturnValue() {
        return null;
    }

    @Override
    public Constructor getConstructor() {
        return null;
    }
}
