package com.d3h.validation.validator;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.Rule;
import com.d3h.validation.violation.ConstraintViolation;
import com.d3h.validation.violation.FieldConstraintViolation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<ConstraintViolation> validateFields(Object object)  {

        Class<?> clazz = object.getClass();

        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

        try{
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

                        if (!rule.check(annotation, value)){
                            String errorMessage = getMessageFromAnnotation(annotation) ;
                            errorMessage = errorMessage == null ? "Error in " + field.getName() : errorMessage;
                            ConstraintViolation constraintViolation = new FieldConstraintViolation(errorMessage, annotation, value, field);
                            listConstraintViolations.add(constraintViolation);
                        }

                    }
                }
            }
        } catch (Exception exception) {
            return listConstraintViolations;
        }


        return listConstraintViolations;
    }

    private String getMessageFromAnnotation(Annotation annotation)  {
        try {
            Method method = annotation.getClass().getMethod("message");
            return (String) method.invoke(annotation);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ConstraintViolation> validateMethodParameters(Object object, Method method, Object[] parameterValues){
        return new ArrayList<>();
    }

    public List<ConstraintViolation> validateMethodReturnValue(Method method, Object returnValue){
        return new ArrayList<>();
    }

    public List<ConstraintViolation> validateFieldsFromSubClassInstance(Object object) {
        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

        try{
            Class<?> clazz = object.getClass();
            Class<?> superClazz = object.getClass().getSuperclass();

            for (Field field: clazz.getSuperclass().getDeclaredFields()){
                field.setAccessible(true);
                //get value
                Object value = field.get(object);
                //get annotation
                Annotation[] annotations = field.getDeclaredAnnotations();
                if (annotations != null && annotations.length != 0){
                    for (Annotation annotation: annotations){
                        Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
                        if (bindAnnotation == null){
                            continue;
                        }
                        Class<? extends Rule<? extends Annotation>> ruleClazz = bindAnnotation.value();
                        Rule rule = ruleClazz.newInstance();

                        if (!rule.check(annotation, field.get(object))){
                            String errorMessage = getMessageFromAnnotation(annotation) ;
                            errorMessage = errorMessage == null ? "Error in " + field.getName() : errorMessage;
                            ConstraintViolation constraintViolation = new FieldConstraintViolation(errorMessage, annotation, value, field);
                            listConstraintViolations.add(constraintViolation);
                        }

                    }
                }
            }
        } catch (Exception exception) {
            return listConstraintViolations;
        }
        
        return listConstraintViolations;
    }
}
