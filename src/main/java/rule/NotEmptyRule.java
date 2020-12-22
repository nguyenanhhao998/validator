package rule;

import annotation.NotEmpty;

public class NotEmptyRule implements Rule<NotEmpty> {

    public void check(NotEmpty annotation, String fieldName, Object target) {
        if (target == null || (target instanceof String && target.toString().isEmpty())) {
            throw new NullPointerException(fieldName + ": " +  annotation.message());
        }
    }
    public Class<NotEmpty> getAnnotationClass() {
        return NotEmpty.class;
    }
}