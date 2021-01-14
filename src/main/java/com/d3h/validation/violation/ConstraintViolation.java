package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface ConstraintViolation {
    String getMessage();
    Annotation getAnnotation();
    Object getInvalidValue();
    Field getField() ;
    Method getMethod() ;
    Object[] getExecutableParameters();
    Object getExecutableReturnValue();
}
