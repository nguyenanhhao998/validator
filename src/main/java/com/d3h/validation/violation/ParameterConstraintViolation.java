package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ParameterConstraintViolation extends ConstraintViolation {
    private final Parameter parameter;
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
    public Parameter getParameter() {
        return parameter;
    }

    @Override
    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public Method getMethod() {
        return method;
    }
}
