package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.NotNull;

public class NotNullRule implements Rule<NotNull> {
    @Override
    public boolean check(NotNull annotation, Object value) {
        return value != null;
    }
}
