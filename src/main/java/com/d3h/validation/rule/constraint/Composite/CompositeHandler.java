package com.d3h.validation.rule.constraint.Composite;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.Composite.RuleComposite;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class CompositeHandler implements MethodInterceptor {
    private boolean checkDeclare(Annotation source, Object value) {
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
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if(obj instanceof RuleComposite && method.getName() == "check"){
            if(!checkDeclare((Annotation)args[0], args[1]))
                return false;
        }
        return proxy.invokeSuper(obj, args);
    }
}
