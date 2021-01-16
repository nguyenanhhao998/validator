package com.d3h.validation.validator;

import com.d3h.validation.rule.Constraint;
import com.d3h.validation.rule.constraint.Rule;
import com.d3h.validation.violation.*;
import net.sf.cglib.proxy.Factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Validator {
    private static Validator instance;

    private Validator() {
    }

    public static Validator getInstance() {
        if (instance == null) {
            instance = new Validator();
        }
        return instance;
    }

    public List<ConstraintViolation> validate(Object object) {
        Class<?>[] fromInterfaces = object.getClass().getInterfaces();
        for (Class fromInterface: fromInterfaces) {
            if (fromInterface.equals(Factory.class)) {
                return validateFields(object, object.getClass().getSuperclass());
            }
        }
        return validateFields(object, object.getClass());
    }

    private List<ConstraintViolation> validateFields(Object object, Class<?> clazz) {
        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

        try {
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                //get value
                Object value = field.get(object);
                //get annotation
                Annotation[] annotations = field.getDeclaredAnnotations();

                if (annotations.length != 0) {
                    for (Annotation annotation : annotations) {
                        Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);

                        if (bindAnnotation == null) {
                            continue;
                        }

                        Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
                        Rule rule = (Rule) ruleClazz.newInstance();

                        if (!rule.check(annotation, value)) {
                            String errorMessage = getMessageFromAnnotation(annotation);
                            errorMessage = errorMessage == null ? "Error in " + field.getName() : errorMessage;
                            ConstraintViolation constraintViolation = new FieldConstraintViolation(errorMessage, annotation, value, field);
                            listConstraintViolations.add(constraintViolation);
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return listConstraintViolations;
    }

    private String getMessageFromAnnotation(Annotation annotation) {
        try {
            Method method = annotation.getClass().getMethod("message");
            return (String) method.invoke(annotation);
        } catch (Exception e) {
            return null;
        }
    }

    public List<ConstraintViolation> validateMethodParameters(Object object, Method method, Object[] parameterValues) {
        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

        try {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Parameter[] parameters = method.getParameters();

            for (int i = 0; i < parameterAnnotations.length && i < parameterValues.length && i < parameters.length; i++) {
                Annotation[] parameterAnnotation = parameterAnnotations[i];
                Parameter parameter = parameters[i];
                Object parameterValue = parameterValues[i];

                for (Annotation annotation : parameterAnnotation) {
                    Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);

                    if (bindAnnotation == null)
                        continue;

                    Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
                    Rule rule = (Rule) ruleClazz.newInstance();

                    if (!rule.check(annotation, parameterValue)) {
                        String errorMessage = getMessageFromAnnotation(annotation);
                        errorMessage = errorMessage == null ? String.format("%s: Error in %s", parameter.getName(), parameterValue) :
                                String.format("%s: %s", parameter.getName(), getMessageFromAnnotation(annotation));
                        ConstraintViolation constraintViolation =
                                new ParameterConstraintViolation(errorMessage, parameter, annotation, parameterValue, method);
                        listConstraintViolations.add(constraintViolation);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return listConstraintViolations;
    }

    public List<ConstraintViolation> validateMethodReturnValue(Method method, Object returnValue) {
        List<ConstraintViolation> constraintViolations = new ArrayList<>();
        Annotation[] annotations = method.getDeclaredAnnotations();

        for (Annotation annotation : annotations) {
            Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);

            if (bindAnnotation == null)
                continue;

            Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
            Rule rule = null;

            try {
                rule = (Rule) ruleClazz.getDeclaredConstructor().newInstance();
            } catch (NoSuchMethodException | InstantiationException |
                    IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }

            if (!rule.check(annotation, returnValue)) {
                String errorMessage = getMessageFromAnnotation(annotation);
                errorMessage = errorMessage == null ? String.format("%s: Error in %s", method.getName(), returnValue) :
                        String.format("%s: %s", method.getName(), errorMessage);
                ConstraintViolation constraintViolation = new MethodConstraintViolation(errorMessage, annotation, method, returnValue);
                constraintViolations.add(constraintViolation);
            }
        }

        return constraintViolations;
    }

    public List<ConstraintViolation> validateConstructor(Constructor constructor, Object[] parameterValues) {
        List<ConstraintViolation> listConstraintViolations = new ArrayList<>();

        try {
            Annotation[] declaredAnnotations = constructor.getDeclaredAnnotations();

            if (declaredAnnotations.length != 0) {
                for (Annotation annotation : declaredAnnotations) {
                    Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);

                    if (bindAnnotation == null) {
                        continue;
                    }

                    Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();
                    Rule rule = (Rule) ruleClazz.newInstance();

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
                    Rule rule = (Rule) ruleClazz.newInstance();

                    if (!rule.check(annotation, parameterValues[i])) {
                        String errorMessage = getMessageFromAnnotation(annotation);
                        errorMessage = errorMessage == null ? "Error in " + parameterValues[i] : errorMessage;
                        ConstraintViolation constraintViolation = new ConstructorConstraintViolation(errorMessage, annotation, constructor, parameterValues);
                        listConstraintViolations.add(constraintViolation);
                    }
                }
            }
        } catch (Exception ex) {
            return listConstraintViolations;
        }

        return listConstraintViolations;
    }

    public ConstraintViolation validateAnnotation(Annotation annotation, Object value) {
        try {
            Constraint bindAnnotation = annotation.annotationType().getDeclaredAnnotation(Constraint.class);
            if (bindAnnotation == null) {
                return null;
            }
            Class<? extends Rule<? extends Annotation, ?>> ruleClazz = bindAnnotation.value();

            Rule rule = null;
            rule = (Rule) ruleClazz.newInstance();

            if (!rule.check(annotation, value)) {
                String errorMessage = getMessageFromAnnotation(annotation);
                errorMessage = errorMessage == null ? "Error in " + value : errorMessage;
                return new ConstructorConstraintViolation(errorMessage, annotation, null, null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return null;
    }
}
