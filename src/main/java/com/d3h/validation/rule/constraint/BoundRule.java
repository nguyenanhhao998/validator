package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.annotation.Bound;
import com.d3h.validation.rule.constraint.Composite.RuleComposite;

public class BoundRule extends RuleComposite<Bound, Object> {
    @Override
    public boolean check(Bound annotation, Object value) {
        return true;
    }
}
