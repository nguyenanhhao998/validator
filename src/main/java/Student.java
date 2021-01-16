import com.d3h.validation.rule.annotation.Bound;
import com.d3h.validation.rule.annotation.Max;
import com.d3h.validation.rule.annotation.Min;
import com.d3h.validation.rule.annotation.NotNull;

public class Student {
    private String id;

    private String name;

    private int finalGrade;

    @Bound(min = @Min(5), max = @Max(19))
    private int age;

    public Student() {
        age = 9;
    }

    public void update(@NotNull String id, @NotNull String name,
                       @Min(0) int finalGrade, @Min(1) int age) {
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
