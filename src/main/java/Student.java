import com.d3h.validation.exception.D3HException;
import com.d3h.validation.rule.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Student {
    @NotNull
    @NotEmpty
    @Digits(integer = 7, fraction = 0)
    private String mssv;

    @DecimalMax(value = "123456789")
    @DecimalMin(value = "1234")
    private String bignumber;

    @AssertFalse
    private boolean isStudent;

    @Past
    private Date dob;

    @Future
    private Date gdate;

    @Size(min = 0, max = 4)
    private ArrayList<String> subject = new ArrayList<>();

    public Student() {
        mssv = "1712393";
        dob = new GregorianCalendar(1999, Calendar.OCTOBER, 04).getTime();

        gdate = new GregorianCalendar(2021, Calendar.OCTOBER, 10).getTime();
        subject.add("Math");
        subject.add("Web");
        bignumber = "123456789";
        isStudent = false;
    }

    @NotEmpty
    public String setMssv(@NotEmpty String mssv){
        this.mssv = mssv;
        return "1712393";
    }
}
