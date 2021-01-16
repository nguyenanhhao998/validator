package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.AssertTrue;

public class AssertTrueRule implements Rule<AssertTrue> {
    @Override
    public boolean check(AssertTrue annotation, Object value) {
        Class clazz = value.getClass();

        if(!(value instanceof Boolean) && clazz != boolean.class)
            return false;

        if(value == null) return true;

        return (Boolean) value;
    }

    @Override
    public Class<AssertTrue> getAnnotationClass() {
        return null;
    }
}
