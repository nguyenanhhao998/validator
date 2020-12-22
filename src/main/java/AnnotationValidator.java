import api.EntityValidator;
import rule.Rule;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class AnnotationValidator implements EntityValidator {
    private final Map<Class<? extends Annotation>, Rule<?>> rules;

    public AnnotationValidator(List<Rule<?>> rules) {
        this.rules = rules.stream()
                .collect(toMap(Rule::getAnnotationClass, identity()));

    }

    @Override
    public List<Exception> validate(Object entity) {
        List<Exception> exceptions = new ArrayList<>();

        Class uClass = entity.getClass();
        Field[] fields = uClass.getDeclaredFields();

        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = getValue(field, entity);
            Annotation[] annotations = field.getAnnotations();

            for (Annotation annotation : annotations) {
                try{
                    doCheck(annotation, field.getName(), fieldValue);
                }catch (Exception ex){
                    exceptions.add(ex);
                }
            }
        }

        return exceptions;
    }

    private void doCheck(Annotation annotation, String field, Object fieldValue) {
        Rule r = rules.get(annotation.annotationType());
        if (r == null) return;

        r.check(annotation, field, fieldValue);
    }

    private Object getValue(Field field, Object entity) {
        try {
            return field.get(entity);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}