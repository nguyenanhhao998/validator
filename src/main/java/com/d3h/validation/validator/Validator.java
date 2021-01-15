package com.d3h.validation.validator;

import com.d3h.validation.rule.constraint.Composite.Creator;
import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.Rule;
import com.d3h.validation.violation.ConstraintViolation;
import com.d3h.validation.violation.ConstructorConstraintViolation;
import com.d3h.validation.violation.FieldConstraintViolation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
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

    public List<ConstraintViolation> validateFields(Object object)  {
        return validateFields(object, object.getClass());
    }

    public List<ConstraintViolation> validateFields(Object object, Class<?> clazz) {
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
                        Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
                        Rule rule = (Rule) Creator.getInstance().create(ruleClazz);

                        if (!rule.check(annotation, value)){
                            String errorMessage = getMessageFromAnnotation(annotation) ;
                            errorMessage = errorMessage == null ? "Error in " + field.getName() : errorMessage;
                            ConstraintViolation constraintViolation = new FieldConstraintViolation(errorMessage, annotation, value, field);
                            listConstraintViolations.add(constraintViolation);
                        }

                    }
                }
            }
            return listConstraintViolations;
        } catch (Exception exception) {
            return listConstraintViolations;
        }

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

    public List<ConstraintViolation> validateConstructor(Constructor constructor, Object[] parameterValues) {
        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

            Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();

            if (declaredAnnotations.length != 0) {
                for (Annotation annotation : declaredAnnotations) {
                    Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
                    if (bindAnnotation == null) {
                        continue;
                    }
                    Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
                    Rule rule = (Rule) Creator.getInstance().create(ruleClazz);

                    for (Object value : parameterValues) {
                        if (!rule.check(annotation, value)) {
                            String errorMessage = getMessageFromAnnotation(annotation);
                            errorMessage = errorMessage == null ? "Error with " + value : errorMessage;
                            ConstraintViolation constraintViolation = new ConstructorConstraintViolation(errorMessage, annotation, constructor, parameterValues);
                            listConstraintViolations.add(constraintViolation);
                        }
                    }
                }
            }

            Annotation[][] annotationMatrix = constructor.getParameterAnnotations();

            for (int i = 0; i < annotationMatrix.length; i++) {

                for (Annotation annotation : annotationMatrix[i]) {
                    Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
                    if (bindAnnotation == null) {
                        continue;
                    }
                    Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
                    Rule rule = (Rule) Creator.getInstance().create(ruleClazz);

                    if (!rule.check(annotation, parameterValues[i])) {
                        String errorMessage = getMessageFromAnnotation(annotation);
                        errorMessage = errorMessage == null ? "Error in " + parameterValues[i] : errorMessage;
                        ConstraintViolation constraintViolation = new ConstructorConstraintViolation(errorMessage, annotation, constructor, parameterValues);
                        listConstraintViolations.add(constraintViolation);
                    }
                }
            }


        return listConstraintViolations;
    }

    public ConstraintViolation validateAnnotation(Annotation annotation, Object value){
        Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
        if (bindAnnotation == null) {
            return null;
        }
        Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();

        Rule rule = null;
        try {
            rule = (Rule) ruleClazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (!rule.check(annotation, value)) {
            String errorMessage = getMessageFromAnnotation(annotation);
            errorMessage = errorMessage == null ? "Error in " + value : errorMessage;
            return new ConstructorConstraintViolation(errorMessage, annotation, null, null);
        }
        return null;
    }
}
