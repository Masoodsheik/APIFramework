package Pojos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class signUp {
    private int id;
    private  String email;
    private  String password;

}
