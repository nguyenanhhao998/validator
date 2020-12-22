package exception;

import java.lang.reflect.Field;

public class DataTypeNotPresentException extends ValidateException {
    protected DataTypeNotPresentException(Field errorField, String message) {
        super(errorField, message);
    }
}
