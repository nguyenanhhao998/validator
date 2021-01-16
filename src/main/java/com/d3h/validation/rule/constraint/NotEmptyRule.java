package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.NotEmpty;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class NotEmptyRule implements Rule<NotEmpty, Object> {
    @Override
    public boolean check(NotEmpty annotation, Object value) {
        Class clazz = value.getClass();

        if (value == null && !(value instanceof CharSequence) && !(value instanceof Collection) && !(value instanceof Map) && !clazz.isArray())
            return false;

        if (value instanceof CharSequence && ((CharSequence) value).length() != 0)
            return true;

        if (value instanceof Collection && ((Collection) value).isEmpty())
            return true;

        if (value instanceof Map && !((Map) value).isEmpty())
            return true;

        if (clazz.isArray() && Array.getLength(value) != 0)
            return true;

        return false;
    }
}
