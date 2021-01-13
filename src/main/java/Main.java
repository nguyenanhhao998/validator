import com.d3h.validation.creator.CGlibClassCreator;
import com.d3h.validation.exception.D3HException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        try {

            Student student = CGlibClassCreator.getInstance().create(Student.class, new Object[] {"1", "1234"});
        } catch (D3HException d3HException){
            System.out.println(d3HException.getConstraintViolations().get(0).getMessage());
        }


    }
}
