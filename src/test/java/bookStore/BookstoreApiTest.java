package bookStore;

import Pojos.createBooks;
import Pojos.signUp;
import io.restassured.response.Response;
import listeners.ExtentReportManagers;
import org.testng.annotations.Test;
import utils.restUtils;
import static org.hamcrest.Matchers.*;

public class BookstoreApiTest extends baseTest {


    private void performLoginAndCaptureToken() {
        String loginEndpoint = "/login"; // Adjust to your actual login endpoint
        signUp signObjects= signup.getSignUp();
        Response loginResponse= restUtils.performPostRequest(baseTest.BASE_URL,signObjects,loginEndpoint);
        loginResponse.then()
                .statusCode(200) // Assuming 200 OK for successful login
                .body("access_token", notNullValue())
                .body("token_type", equalTo("bearer"));

        // CAPTURE THE TOKEN AND TOKEN TYPE FROM THE RESPONSE
        String accessToken = loginResponse.jsonPath().getString("access_token");
        String tokenType = loginResponse.jsonPath().getString("token_type");

        // STORE THEM IN THE TEST CONTEXT
        testContext.setAccessToken(accessToken);
        testContext.setTokenType(tokenType);

        System.out.println("Login Successful. Token captured and stored in TestContext.");
        System.out.println("Captured Access Token: " + testContext.getAccessToken());
    }

    @Test
    void testCreateBook() {

        performLoginAndCaptureToken();
        String authorizationHeader = testContext.getAuthorizationHeader();
        System.out.println("\n--- Using Token for Create Book API ---");
        System.out.println("Authorization Header: " + authorizationHeader);
        if (authorizationHeader == null) {
            throw new IllegalStateException("Authentication token is null. Login likely failed.");
        }

        createBooks newBook= createBook.setBooks();
        String createBookEndpoint = "/books/"; // Adjust to your actual endpoint
        Response createBookResponse=restUtils.performPostRequestWithAuthorization(authorizationHeader,newBook,createBookEndpoint);
        createBookResponse.then()
                .statusCode(200)
                .log().all();

    }


    @Test
    void getBookDetails(){
        String bookEndpoint = "/books/";
        performLoginAndCaptureToken();
        String authorizationHeader = testContext.getAuthorizationHeader();
        Response getBookdDetailsResponse=restUtils.getReponse(baseTest.BASE_URL,bookEndpoint,authorizationHeader);
        getBookdDetailsResponse.then()
                .log().all()
                .statusCode(200);
    }

    @Test
    void updateBookDetaila(){
        int dummyBookId=1;
        String putEndpoint = "/books/" + dummyBookId;
        performLoginAndCaptureToken();
        String authorizationHeader = testContext.getAuthorizationHeader();
        createBooks updateCreateBooks=createBook.createBookObject(1,"RestAssured","ShaikMasoodAPI",2022,"Selenium with JavaAPI");


        Response getBookdDetailsResponse=restUtils.putResponse(baseTest.BASE_URL,updateCreateBooks,putEndpoint,authorizationHeader);
        getBookdDetailsResponse.then()
                   .statusCode(200);
        softAssert.assertEquals("[0].book_summary",getBookdDetailsResponse.jsonPath().getString("bookSummary"));
        softAssert.assertEquals("[0].published_year",getBookdDetailsResponse.jsonPath().getString("publishedYear"));
        softAssert.assertEquals("[0].author",getBookdDetailsResponse.jsonPath().getString("author"));

    }

     @Test

    void deleteBook(){
         int dummyBookId=1;
         String deleteEndpoint = "/books/" + dummyBookId;
         performLoginAndCaptureToken();
         String authorizationHeader = testContext.getAuthorizationHeader();
        Response deleteResponse=restUtils.deleteRequest(baseTest.BASE_URL,authorizationHeader,deleteEndpoint);
        softAssert.assertEquals(deleteResponse.jsonPath().getString("message"),"Book deleted successfully");

    }


}
