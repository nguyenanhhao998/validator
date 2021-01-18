package com.d3h.validation;

import com.d3h.validation.rule.annotation.*;
import com.d3h.validation.rule.constraint.MinRule;

public class Student {
    @NotNull
    @Size(min = 7, max = 7)
    private String code;

    private String name;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Ngu af")
    private String email;

    @Min(18)
    public int age;

    @Bound(min = @Min(0), max = @Max(10))
    private int mathPoint;

    @Bound(min = @Min(0), max = @Max(10))
    private int physicsPoint;

    @Bound(min = @Min(0), max = @Max(10))
    private int englishPoint;

    public Student(){
        this.code = "1712000";
        this.age = 18;
        this.name = "Nguyen Van A";
        this.email = "leetankhongs";
        this.mathPoint = 0;
        this.physicsPoint = 0;
        this.englishPoint = 0;
    }

    public Student(String code, String name, int age, int mathPoint, int physicsPoint,@Bound(min = @Min(0), max = @Max(10)) int englishPoint) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.mathPoint = mathPoint;
        this.physicsPoint = physicsPoint;
        this.englishPoint = englishPoint;
    }

    public void setAge(int age){

        this.age = age;
    }
}
