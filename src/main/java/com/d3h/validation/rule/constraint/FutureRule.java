package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Future;

import java.util.Calendar;
import java.util.Date;

public class FutureRule implements Rule<Future, Object> {
    @Override
    public boolean check(Future annotation, Object value) {
        Class clazz = value.getClass();

        if(clazz != Date.class && !(value instanceof Calendar))
            return false;

        if(value == null) return true;

        if(Date.class.equals(clazz) && (new Date()).compareTo((Date) value) < 0)
            return true;

        if(value instanceof Calendar && (Calendar.getInstance()).compareTo((Calendar) value) < 0)
            return true;

        return false;
    }
}
