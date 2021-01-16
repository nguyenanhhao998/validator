package com.d3h.validation.rule.annotation;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.DigitsRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(DigitsRule.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Digits {
    int fraction();

    int integer();

    String message() default "{d3h.validation.constraints.Digits.message}";
}
