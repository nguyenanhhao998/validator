package exception;

import java.lang.reflect.Field;

public abstract class ValidateException extends Exception {
    protected Field errorField;
    protected String message;

    protected ValidateException(Field errorField, String message){
        this.errorField = errorField;
        this.message = message;
    }

    public Field getErrorField() {
        return errorField;
    }

    @Override
    public String getMessage() {
        return message;
    }
}