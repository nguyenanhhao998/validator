package com.d3h.validation.rule.constraint.composite;

import com.d3h.validation.rule.constraint.Rule;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class CombinedRule<T extends Annotation, V extends Object> implements Rule<T, V> {
    private List<Annotation> getAnnotationMembers(Annotation annotation) {
        List<Annotation> annotationList = new ArrayList<>();

        try {
            Method[] methods = annotation.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getParameterCount() == 0) {
                    Object returnValue = method.invoke(annotation);
                    if (returnValue instanceof Annotation) {
                        annotationList.add((Annotation) returnValue);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return annotationList;
    }

    private boolean checkDeclaredRules(T source, V value) {
        List<Annotation> annotationList = getAnnotationMembers(source);

        for (Annotation annotation : annotationList) {
            ConstraintViolation violation = Validator.getInstance().validateAnnotation(annotation, value);
            if (violation != null) {
                return false;
            }
        }

        return true;
    }


    @Override
    public final boolean check(T annotation, V value) {
        boolean passCheckDeclaredRules = checkDeclaredRules(annotation, value);
        return passCheckDeclaredRules && checkValues(annotation, value);
    }

    protected abstract boolean checkValues(T annotation, V value);

}
