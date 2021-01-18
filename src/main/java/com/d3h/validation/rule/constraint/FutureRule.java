package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Future;

import java.util.Calendar;
import java.util.Date;

public class FutureRule implements Rule<Future, Object> {
    @Override
    public boolean check(Future annotation, Object value) {

        if (value == null)
            return true;

        if (!(value instanceof Date) && !(value instanceof Calendar))
            return false;

        if ((value instanceof Date) && (new Date()).compareTo((Date) value) < 0)
            return true;

        if (value instanceof Calendar && (Calendar.getInstance()).compareTo((Calendar) value) < 0)
            return true;

        return false;
    }
}
