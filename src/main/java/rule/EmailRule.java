package rule;

import annotation.Email;

import java.util.regex.Pattern;

public class EmailRule implements Rule<Email> {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";// cấu trúc 1 email thông thường

    public void check(Email checkLength, String fieldName, Object target) {
        String str = (String)target;
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if (!pattern.matcher(str).matches()) {
            throw new IllegalStateException(fieldName + ": " +  checkLength.message()
            );
        }
    }
    private int length(Object target) {
        return target == null ? 0 : target.toString().length();
    }
    public Class<Email> getAnnotationClass() {
        return Email.class;
    }
}