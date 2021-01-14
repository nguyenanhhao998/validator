package com.d3h.validation.rule.constraint;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

public interface Rule<T extends Annotation, O> {
    boolean check(T annotation, O value);
}
