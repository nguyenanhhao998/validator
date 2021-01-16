package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Size;

import java.lang.CharSequence;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class SizeRule implements Rule<Size> {
    @Override
    public boolean check(Size annotation, Object value) {
        Class clazz = value.getClass();

        if(!(value instanceof CharSequence) && !(value instanceof Collection) && !(value instanceof Map) && !clazz.isArray())
            return false;

        if(value == null) return true;

        if (value instanceof CharSequence){
            int length = ((CharSequence)value).length();
            if(length >= annotation.min() && length <= annotation.max())
                return true;
        }

        if(value instanceof Collection){
            int length = ((Collection)value).size();
            if(length >= annotation.min() && length <= annotation.max())
                return true;
        }

        if(value instanceof Map){
            int length = ((Map)value).size();
            if(length >= annotation.min() && length <= annotation.max())
                return true;
        }

        if(clazz.isArray()){
            int length = Array.getLength(value);
            if(length >= annotation.min() && length <= annotation.max())
                return true;
        }

        return false;
    }

    @Override
    public Class<Size> getAnnotationClass() {
        return null;
    }
}
