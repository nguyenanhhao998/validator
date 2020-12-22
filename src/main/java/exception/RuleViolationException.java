package exception;

import java.lang.reflect.Field;

public class RuleViolationException extends ValidateException {

    protected RuleViolationException(Field errorField, String message) {
        super(errorField, message);
    }
}
