package com.d3h.validation.rule.annotation;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.PastRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(PastRule.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Past {
    String message() default "{d3h.validation.constraints.Past.message}";
}
