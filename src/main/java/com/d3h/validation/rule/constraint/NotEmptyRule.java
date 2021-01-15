package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.NotEmpty;

import java.lang.CharSequence;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class NotEmptyRule implements Rule<NotEmpty> {
    @Override
    public boolean check(NotEmpty annotation, Object value) {
        Class clazz = value.getClass();

        if(value == null && clazz != CharSequence.class && clazz!= Collection.class && clazz != Map.class && !clazz.isArray())
            return false;

        if (CharSequence.class.equals(clazz) && ((CharSequence)value).length() != 0)
            return true;

        if(Collection.class.equals(clazz) && !((Collection)value).isEmpty())
            return true;

        if(Map.class.equals(clazz) && !((Map)value).isEmpty())
            return true;

        if(clazz.isArray() && Array.getLength(value) != 0)
            return true;

        return false;
    }

    @Override
    public Class<NotEmpty> getAnnotationClass() {
        return null;
    }
}
