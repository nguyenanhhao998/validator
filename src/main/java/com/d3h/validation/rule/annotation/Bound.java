package com.d3h.validation.rule.annotation;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.BoundRule;
import com.d3h.validation.rule.constraint.MaxRule;
import com.d3h.validation.rule.constraint.RuleComposite;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(BoundRule.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Max(value = 5)
@Min(value = 1)
public @interface Bound {
    String message() default "Lỗi bound";
}
