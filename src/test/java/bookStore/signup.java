package bookStore;
import Pojos.signUp;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class signup {

    //We can use Signup in different format
    static  Faker faker=new Faker();
    public static Map<String, Object> getSignUp(int id, String email, String password){
        Map<String,Object> payload=new HashMap<>();
        payload.put("id",id);
        payload.put("email",email);
        payload.put("password",password);
        return  payload;

    }

    public  static signUp getSignUp(){
        return  signUp
                .builder()
                .id(1)
                .email("msheik1226@gmail.com")
                .password("test")
                .build();
    }


    public  static signUp getSignUp1(){
        return  signUp
                .builder()
                .id(faker.random().nextInt(1))
                .email(faker.name()+"gmail.com".toString())
                .password(faker.name().toString())
                .build();
    }


}
