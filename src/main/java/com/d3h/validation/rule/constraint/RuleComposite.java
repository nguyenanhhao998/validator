package com.d3h.validation.rule.constraint;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

public abstract class RuleComposite<T extends Annotation, Object> implements Rule<T, Object> {
    protected boolean checkDeclare(Annotation source, Object value) {
        Annotation[] annotations = source.annotationType().getAnnotations();
        for (Annotation annotation: annotations) {
            String name =annotation.annotationType().getName();
            if(Constraint.class.getName() != name && Target.class.getName() != name && name != Retention.class.getName())
            {
                ConstraintViolation constraintViolation = Validator.getInstance().validateAnnotation(annotation, value);
                if(constraintViolation != null)
                    return false;
            }
        }
        return true;
    }

    @Override
    public abstract boolean check(T annotation, Object value);

}
