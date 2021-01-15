import com.d3h.validation.creator.CGlibClassCreator;
import com.d3h.validation.exception.D3HException;
import com.d3h.validation.violation.ConstraintViolation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CGlibClassCreator classCreator = CGlibClassCreator.getInstance();
        Student student = classCreator.create(Student.class);
        try {
            student.update("1712500", "Tran Phuc Quang Huy", 10, 22);
            student.getAge();
        } catch (D3HException exception) {
            exception.printStackTrace();
            List<ConstraintViolation> constraintViolations = exception.getConstraintViolations();
            for(ConstraintViolation constraintViolation : constraintViolations) {
                System.out.println(constraintViolation.getMessage());
            }
        }
    }
}
