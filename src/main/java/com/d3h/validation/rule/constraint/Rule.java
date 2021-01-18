package com.d3h.validation.rule.constraint;

import java.lang.annotation.Annotation;

public interface Rule<T extends Annotation, V extends Object> {
    boolean check(T annotation, V value);
}
