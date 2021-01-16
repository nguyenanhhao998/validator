package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.AssertFalse;

public class AssertFalseRule implements Rule<AssertFalse> {
    @Override
    public boolean check(AssertFalse annotation, Object value) {
        Class clazz = value.getClass();

        if(!(value instanceof Boolean) && clazz != boolean.class)
            return false;

        if(value == null) return true;

        return !((Boolean) value);
    }

    @Override
    public Class<AssertFalse> getAnnotationClass() {
        return null;
    }
}
