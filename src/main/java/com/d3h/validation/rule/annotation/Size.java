package com.d3h.validation.rule.annotation;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.SizeRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(SizeRule.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Size {
    int max();

    int min();

    String message() default "{d3h.validation.constraints.Size.message}";
}
