package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface ConstraintViolation {
    public String getMessage();
    public Annotation getAnnotation();
    public Object getInvalidValue();
    public Field getField() ;
    public Method getMethod() ;
    public Object[] getExecutableParameters();
    public Object getExecutableReturnValue();
}
