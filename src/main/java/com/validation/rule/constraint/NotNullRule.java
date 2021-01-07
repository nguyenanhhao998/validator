package com.validation.rule.constraint;

import javax.validation.constraints.NotNull;

public class NotNullRule implements Rule<NotNull> {

    @Override
    public boolean check(NotNull annotation, Object value) {
        return value != null;
    }

    @Override
    public Class<NotNull> getAnnotationClass() {
        return NotNull.class;
    }
}
