package com.validation.violation;

import java.lang.reflect.Field;

public class FieldConstraintViolation implements ConstraintViolation{
    private Field field;
    private String message;

    public FieldConstraintViolation(Field field, String message) {
        this.field = field;
        this.message = message;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Field getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
