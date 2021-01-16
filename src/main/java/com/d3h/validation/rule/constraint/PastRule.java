package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Past;

import java.beans.PropertyEditorSupport;
import java.util.Calendar;
import java.util.Date;

public class PastRule implements Rule<Past> {
    @Override
    public boolean check(Past annotation, Object value) {
        Class clazz = value.getClass();

        if(clazz != Date.class && !(value instanceof Calendar))
            return false;

        if(value == null) return true;

        if(Date.class.equals(clazz) && (new Date()).compareTo((Date) value) > 0)
            return true;

        if(value instanceof Calendar && (Calendar.getInstance()).compareTo((Calendar) value) > 0)
            return true;

        return false;
    }

    @Override
    public Class<Past> getAnnotationClass() {
        return null;
    }
}
