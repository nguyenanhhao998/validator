package com.d3h.validation.rule.annotation;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.DecimalMaxRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(DecimalMaxRule.class)
public @interface DecimalMax {
    String value();

    boolean inclusive() default true;

    String message() default "{d3h.validation.constraints.DecimalMax.message}";
}
