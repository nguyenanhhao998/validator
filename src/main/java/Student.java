import com.d3h.validation.rule.annotation.Min;
import com.d3h.validation.rule.annotation.NotNull;

public class Student {
    private String id;

    private String name;

    private int finalGrade;

    private int age;

    public Student() {
    }

    public void update(@NotNull String id, @NotNull String name,
                       @Min(value = 0) int finalGrade,@Min(value = 1) int age) {
        this.id = id;
        this.name = name;
        this.finalGrade = finalGrade;
        this.age = age;
    }

    @Min(value = 1)
    public int getAge() {
        return age - 100;
    }

}
