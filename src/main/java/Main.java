import com.validation.validator.Validator;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        try {
            Validator.getInstance().validateFields(student);
        } catch (Exception exception) {
            System.out.println("Message: " + exception.getMessage());
        }
    }
}
