package com.d3h.validation;

import com.d3h.validation.creator.CreatorFactory;
import com.d3h.validation.exception.D3HException;
import com.d3h.validation.validator.Validator;
import com.d3h.validation.violation.ConstraintViolation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException {


        try {
            Student student1 = CreatorFactory.getDefaultCreator().create(Student.class);
//            Student student2 = CreatorFactory.getDefaultCreator().create(Student.class, new Class[]{String.class, String.class, int.class, int.class, int.class, int.class},
//                    new Object[]{"1712479", "Le Tan Hung", 20, 7, 8, 9});
//
//            MyClass myClass = CreatorFactory.getDefaultCreator().create(MyClass.class);
//            myClass.addStudent(student1);
//            myClass.addStudent(student2);
//
//            int size = myClass.getStudentNumbers();
//            student1.setAge(19);
        } catch (D3HException ex) {
            for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
                System.out.println(constraintViolation.getMessage());
            }
        }

//        Student student1 = new Student();
//
//        List<ConstraintViolation> constraintViolations = Validator.getInstance().validate(student1);
//
//        Constructor constructor = Student.class.getConstructor(new Class[]{String.class, String.class, int.class, int.class, int.class, int.class});
//        constraintViolations = Validator.getInstance().validateConstructor(constructor, new Object[]{"1712479", "Le Tan Hung", 20, 7, 8 , 9});
//
//
//        Student student2 = new Student("1712479", "Le Tan Hung", 20, 7 , 8 , 9);
//
//        Method method = MyClass.class.getMethod("addStudent", Student.class);
//        MyClass myClass = new MyClass();
//        myClass.addStudent(student1);
//
//        constraintViolations = Validator.getInstance().validateMethodParameters(method, new Object[]{student1});
//
//        method = MyClass.class.getMethod("getStudentNumbers");
//        int size = myClass.getStudentNumbers();
//
//        constraintViolations = Validator.getInstance().validateMethodReturnValue(method, size);
//
//        for (ConstraintViolation constraintViolation: constraintViolations) {
//            System.out.println(constraintViolation.getMessage());
//        }

    }
}
