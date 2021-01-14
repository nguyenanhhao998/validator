import com.d3h.validation.exception.D3HException;
import com.d3h.validation.rule.annotation.Bound;
import com.d3h.validation.rule.annotation.Min;
import com.d3h.validation.rule.annotation.NotNull;

public class Student {

    @NotNull
    private String mssv;
    private String name;

    private int age;

    public Student() {
        mssv = null;

    }

    public Student(String mssv) {
        this.mssv = mssv;
    }


    public Student( String mssv,String name) {
        this.mssv = mssv;
        this.name = name;
    }

    @NotNull
    public String setMssv(@NotNull String mssv){
        this.mssv = mssv;
        return "AHII";
    }
}
