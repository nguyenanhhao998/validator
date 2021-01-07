import com.validation.rule.annotation.NotNull;

public class Student {

    @NotNull
    private String mssv;

    public Student(){
        mssv = null;
    }
}
