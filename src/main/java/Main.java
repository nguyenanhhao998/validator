import com.d3h.validation.creator.CGlibClassCreator;
import com.d3h.validation.exception.D3HException;
import com.d3h.validation.violation.ConstraintViolation;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        try {
            CGlibClassCreator classCreator = CGlibClassCreator.getInstance();
            Student student = classCreator.create(Student.class);
        } catch (D3HException exception) {

//            exception.printStackTrace();
            List<ConstraintViolation> constraintViolations = exception.getConstraintViolations();
            for(ConstraintViolation constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getMessage());
            }
        }


    }
}
