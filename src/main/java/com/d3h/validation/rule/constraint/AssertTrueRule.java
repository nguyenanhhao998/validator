package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.AssertTrue;

public class AssertTrueRule implements Rule<AssertTrue, Object> {
    @Override
    public boolean check(AssertTrue annotation, Object value) {

        if (!(value instanceof Boolean))
            return false;

        if (value == null) return true;

        return (Boolean) value;
    }
}
