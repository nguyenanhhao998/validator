package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Min;

import java.lang.annotation.Annotation;

public class MinRule implements Rule<Min> {
    @Override
    public boolean check(Min annotation, Object value) {
        if(((String) value).length() < annotation.length())
                return false;
        return true;
    }
}
