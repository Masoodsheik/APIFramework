package bookStore;

import Pojos.accessToken;
import Pojos.createBooks;
import Pojos.signUp;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.restUtils;

import java.util.Map;

public class bookStores {
    SoftAssert softAssert = new SoftAssert();
    accessToken token=new accessToken();
    @Test

    public void createUser(){
String baseUrl="http://127.0.0.1:8000";
String endPoints="/signup";

     String requestPayload="{\n" +
                        "  \"id\": 0,\n" +
                        "  \"email\": \"msheik1226@gmail.com\",\n" +
                        "  \"password\": \"test\"\n" +
                        "}";

    Response response= restUtils.performPostRequest(baseUrl,requestPayload,endPoints);
        System.out.println("Body --->"+response.asString());
        System.out.println(response.statusCode());

    }


    @Test

    public void createUser1(){
        String baseUrl="http://127.0.0.1:8000";
        String endPoints="/signup";
        Map<String,Object> payload=signup.getSignUp(1,"msheik1226@gmail.com","test");
        Response response=restUtils.performPostRequest(baseUrl,payload,endPoints);
        System.out.println("Body --->"+response.asString());
        System.out.println(response.statusCode());
    }


@Test
    public void createUser2(){
    String baseUrl="http://127.0.0.1:8000";
    String endPoints="/signup";
    signUp signObjects= signup.getSignUp();
    Response response=restUtils.performPostRequest(baseUrl,signObjects,endPoints);
    //softAssert.assertEquals( response.jsonPath().getString("detail"),"Email already registered");
  //  softAssert.assertEquals(response.statusCode(),400);


}


//    @Test
//    public void createUser3(){
//        String baseUrl="http://127.0.0.1:8000";
//        String endPoints="/signup";
//        signUp signObjects= signup.getSignUp1();
//
//
//    }


    @Test
    public void login(){
        String baseUrl="http://127.0.0.1:8000";
        String endPoints="/login";
        signUp signObjects= signup.getSignUp();
        Response response=restUtils.performPostRequest(baseUrl,signObjects,endPoints);
        System.out.println(response.jsonPath().getString("access_token"));
        System.out.println(response.jsonPath().getString("token_type"));
 //       softAssert.assertEquals( response.jsonPath().getString("access_token"),"Email already registered");
   //     softAssert.assertEquals(response.statusCode(),400);
    }

    @Test
    public void createBook(){
        login();
        String baseUrl="http://127.0.0.1:8000";
        String endPoints="/books/";
        String authenciate=token.getToken_type()+" "+token.getAccess_token();
        System.out.printf("authenciate--->"+authenciate);
        createBooks createBookObject=createBook.setBooks();
        Response response=restUtils.performPostRequest(baseUrl,authenciate,createBookObject,endPoints);

    }


}
