package com.d3h.validation.rule.constraint.composite;

import com.d3h.validation.rule.annotation.Bound;
import com.d3h.validation.rule.constraint.composite.CombinedRule;

public class BoundRule extends CombinedRule<Bound, Object> {
    @Override
    protected boolean checkValues(Bound annotation, Object value) {
        return true;
    }
}
