package com.validation.rule;

import com.validation.rule.constraint.Rule;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {
    Class<? extends Rule<? extends Annotation>> value();
}
