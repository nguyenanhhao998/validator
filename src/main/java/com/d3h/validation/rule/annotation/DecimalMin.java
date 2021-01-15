package com.d3h.validation.rule.annotation;


import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.DecimalMinRule;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(DecimalMinRule.class)
public @interface DecimalMin {
    String value();
    boolean inclusive();
    String message() default "{javax.validation.constraints.DecimalMin.message}";
}
