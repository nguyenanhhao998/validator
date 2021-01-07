package com.validation.rule.annotation;

import com.validation.rule.Constraint;
import com.validation.rule.constraint.NotNullRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(NotNullRule.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    String message() default "Hao Not Null";
}
