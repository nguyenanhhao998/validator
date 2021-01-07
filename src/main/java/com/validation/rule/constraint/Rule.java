package com.validation.rule.constraint;

import java.lang.annotation.Annotation;

public interface Rule<T extends Annotation> {
    boolean check(T annotation, Object value);
    Class<T> getAnnotationClass();
}
