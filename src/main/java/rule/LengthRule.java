package rule;

import annotation.Length;

public class LengthRule implements Rule<Length> {
    public void check(Length checkLength, String fieldName, Object target) {
        int length = length(target);
        if (length < checkLength.min() || length > checkLength.max()) {
            throw new IllegalStateException(fieldName + ": " + checkLength.message()
            );
        }
    }
    private int length(Object target) {
        return target == null ? 0 : target.toString().length();
    }
    public Class<Length> getAnnotationClass() {
        return Length.class;
    }
}