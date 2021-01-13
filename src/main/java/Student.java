import com.d3h.validation.exception.D3HException;
import com.d3h.validation.rule.annotation.Min;
import com.d3h.validation.rule.annotation.NotNull;

public class Student {

    private String mssv;
    private String name;

    public Student() {
        mssv = null;
    }

    @Min(length = 1)
    public Student(String mssv) {
        this.mssv = mssv;
    }


    public Student(@Min(length = 1) String mssv, @Min(length = 4) String name) {
        this.mssv = mssv;
        this.name = name;
    }

    @NotNull
    public String setMssv(@NotNull String mssv){
        this.mssv = mssv;
        return "AHII";
    }
}
