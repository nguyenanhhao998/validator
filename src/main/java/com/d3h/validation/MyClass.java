package com.d3h.validation;

import com.d3h.validation.rule.annotation.Min;
import com.d3h.validation.rule.annotation.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    List<Student> students = new ArrayList<>();

    public boolean addStudent(@NotNull Student student){
        students.add(student);
        return true;
    }

    @Min(1)
    public int getStudentNumbers(){
        return students.size();
    }
}
