package com.d3h.validation.violation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class FieldConstraintViolation extends ConstraintViolation{
    private final Object invalidValue;
    private final Field field;

    public FieldConstraintViolation(String message, Annotation annotation,
                                    Object invalidValue, Field field) {
        this.message = message;
        this.annotation = annotation;
        this.invalidValue = invalidValue;
        this.field = field;
    }

    @Override
    public Object getInvalidValue() {
        return invalidValue;
    }

    @Override
    public Field getField() {
        return field;
    }
}
