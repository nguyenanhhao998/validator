package com.d3h.validation.rule.annotation;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.MinRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(MinRule.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Min {
    long value();
    String message() default "{com.d3h.validation.rule.constraint.Min.message}";
}