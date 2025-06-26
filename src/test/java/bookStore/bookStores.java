package bookStore;

import Pojos.accessToken;
import Pojos.createBooks;
import Pojos.signUp;
import endPoints.endPoints;
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
    public void createUser2(){
    String baseUrl=endPoints.baseURL;
    String signUpEndPoints=endPoints.signUpEndPoints;
    signUp signObjects= signup.getSignUp();
    Response response=restUtils.performPostRequest(baseUrl,signObjects,signUpEndPoints);

}

    @Test
    public void login(){
        String baseUrl= endPoints.baseURL;
        String loginendPoint=endPoints.loginEndpoint;
        signUp signObjects= signup.getSignUp();
        Response response=restUtils.performPostRequest(baseUrl,signObjects,loginendPoint);

    }




}
