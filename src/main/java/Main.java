import com.d3h.validation.creator.CGlibClassCreator;
import com.d3h.validation.exception.D3HException;
import com.d3h.validation.rule.annotation.Bound;
import com.d3h.validation.validator.Validator;

import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        try {
            Student student = CGlibClassCreator.getInstance().create(Student.class);
        } catch (D3HException d3HException){
            System.out.println(d3HException.getConstraintViolations().get(0).getMessage());
        }
    }
}
