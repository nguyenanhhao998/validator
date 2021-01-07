import com.d3h.validation.exception.D3HException;
import com.d3h.validation.rule.annotation.NotNull;

public class Student {

    @NotNull
    private String mssv;

    public Student() {
        mssv = "1712418";
    }

    @NotNull
    public String setMssv(@NotNull String mssv){
        this.mssv = mssv;
        return "AHII";
    }
}
