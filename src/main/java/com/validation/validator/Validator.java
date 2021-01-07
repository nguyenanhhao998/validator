package com.validation.validator;

import com.validation.rule.Constraint;
import com.validation.rule.constraint.Rule;
import com.validation.violation.ConstraintViolation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private static Validator instance;

    private Validator() {

    }

    public static Validator getInstance() {
        if (instance == null){
            instance = new Validator();
        }
        return instance;
    }

    public List<ConstraintViolation> validateFields(Object object) throws Exception {

        Class<?> clazz = object.getClass();

        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

        for (Field field: clazz.getDeclaredFields()){
            field.setAccessible(true);
            //get value
            Object value = field.get(object);
            //get annotation
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length != 0){
                for (Annotation annotation: annotations){
                    Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
                    if (bindAnnotation == null){
                        continue;
                    }
                    Class<? extends Rule<? extends Annotation>> ruleClazz = bindAnnotation.value();
                    Rule rule = ruleClazz.newInstance();

                    if (rule.check(annotation, value)){
                        throw new Exception("Hihi");
                    }

                }
            }
        }
        return listConstraintViolations;
    }

    public List<ConstraintViolation> validateMethod(Object object, Method method, Object[] parameterValues){
        return null;
    }
}
