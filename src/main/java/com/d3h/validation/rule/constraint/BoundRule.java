package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Bound;

import java.lang.annotation.Annotation;

public class BoundRule extends RuleComposite<Bound, Object>{
    @Override
    public boolean check(Bound annotation, Object value) {
        boolean temp = super.checkDeclare(annotation, value);
        return temp;
    }
}
