package exception;

import java.lang.reflect.Field;

public abstract class ValidateException extends Exception {
    Field errorField;
    String message;
}