import annotation.Email;
import annotation.Length;
import annotation.NotEmpty;

public class User {

    public User(String email){
       this.email = email;
    }

    @NotEmpty(message = "Mày bị ngu à, id không được bỏ trống")
    private String id;

    @Email
    @Length(min = 5, max = 10, message = "Làm ơn dùng não viết email dùm cái")
    private String email;

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void temp(@Length String a){

    }
}
