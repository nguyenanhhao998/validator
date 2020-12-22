package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Length {
    int min() default 0;

    int max() default Integer.MAX_VALUE;

    String message() default "Chiều dài không hợp lệ";
}
