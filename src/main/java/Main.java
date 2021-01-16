import com.d3h.validation.creator.CGlibClassCreator;
import com.d3h.validation.exception.D3HException;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;

import java.lang.reflect.Field;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Student student = CGlibClassCreator.getInstance().create(Student.class);
        try {
            student.setMssv("1712999");
        } catch (D3HException d3HException){
            System.out.println(d3HException.getConstraintViolations().get(0).getMessage());
        }
    }
}
