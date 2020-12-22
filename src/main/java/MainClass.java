
import rule.EmailRule;
import rule.LengthRule;
import rule.NotEmptyRule;
import rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] argv){
        User user = new User("leetankhongsfgdfhfghfhghfg.com");
        user.setId("123456");

        List<Rule<?>> rules = new ArrayList<>();
        rules.add(new LengthRule());
        rules.add(new NotEmptyRule());
        rules.add(new EmailRule());
        AnnotationValidator validator = new AnnotationValidator(rules);

        List<Exception> exceptions = validator.validate(user);

        if(exceptions.size() == 0){
            System.out.println("User hợp lệ");
        }else{
            System.out.println("User không hợp lê");
            for(int i = 0; i < exceptions.size(); i++){
                System.out.println(exceptions.get(i).getMessage());
            }
        }


    }
}
