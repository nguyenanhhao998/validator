import com.d3h.validation.creator.CGlibClassCreator;
import com.d3h.validation.exception.D3HException;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;

import java.lang.reflect.Field;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Student student = CGlibClassCreator.getInstance().create(Student.class);
//
//        A a = new A();
//        B b = new B();
//
//        for (Field field: b.getClass().getSuperclass().getDeclaredFields()){
//            field.setAccessible(true);
//            System.out.println(field.get(a));
//        }



        try {
            student.setMssv(null);
        } catch (D3HException d3HException){
            System.out.println(d3HException.getConstraintViolations().get(0).getMessage());
        }

//        List<ConstraintViolation> constraintViolationList = Validator.getInstance().validateFields(student);
//        for (ConstraintViolation constraintViolation: constraintViolationList) {
//            System.out.println(constraintViolation.getMessage());
//        }

//        Class<?> clazz = new Student().getClass();
//        for (Field field: clazz.getDeclaredFields()){
//            field.setAccessible(true);
//
//            //get annotation
//            Annotation[] annotations = field.getDeclaredAnnotations();
//            if (annotations.length != 0){
//                for (Annotation annotation: annotations){
//                    Class<?> aC = annotation.annotationType().getClass();
//                    Method method = annotation.getClass().getMethod("message");
//                    System.out.println(method.invoke(annotation));
//
//                }
//            }
//        }

    }
}
