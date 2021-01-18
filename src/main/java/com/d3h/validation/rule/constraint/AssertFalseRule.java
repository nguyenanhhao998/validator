package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.AssertFalse;

public class AssertFalseRule implements Rule<AssertFalse, Object> {
    @Override
    public boolean check(AssertFalse annotation, Object value) {

        if (!(value instanceof Boolean))
            return false;

        if (value == null) return true;

        return !((Boolean) value);
    }
}
