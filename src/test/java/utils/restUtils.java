package utils;
import java.util.Map;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class restUtils {

    public static Response performPostRequest(String baseUrl, String requestPayload,String endPoint){

        return given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(requestPayload)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response performPostRequest(String baseUrl, Map<String, Object> requestPayload, String endPoint){

        return given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(requestPayload)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response performPostRequest(String baseUrl,Object requestPayload, String endPoint){

        return given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .body(requestPayload)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();
    }



    public static Response performPostRequest(String baseUrl,String authorizationHeader,Object requestPayload, String endPoint){

        return given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("Authorization",authorizationHeader)
                .body(requestPayload)
                .when()
                .post(endPoint)
                .then()
                .log().all()
                .extract().response();

//       return given()
//                .header("Authorization", authorizationHeader) // <-- USE THE CAPTURED TOKEN HERE
//               .header("Content-Type", "application/json")
//                .body(requestPayload)
//                .when()
//                .post(endPoint)
//                .then()
//                .log().all() // Log entire request/response for debugging
//                .extract()
//                .response();

    }

    public static Response performPostRequestWithAuthorization(String authorization, Object requestPayload, String endPoint ){
        return given()
                .header("Authorization", authorization) // <-- USE THE CAPTURED TOKEN HERE
                .header("Content-Type", "application/json")
                .body(requestPayload)
                .when()
                .post(endPoint)
                .then()
                .log().all() // Log entire request/response for debugging
                .extract()
                .response();
    }


    public static Response getReponse(String baseUrl,String endpoint,String authorization) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", authorization) // <-- USE THE CAPTURED TOKEN HERE
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response putResponse(String baseUrl,Object payload,String endpoint,String authorization) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", authorization) // <-- USE THE CAPTURED TOKEN HERE
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
    }


    public static Response deleteRequest(String baseUrl,String authorization,String endpoint) {
        return given()
                .baseUri(baseUrl)
                .header("Authorization", authorization) // <-- USE THE CAPTURED TOKEN HERE
                .header("Content-Type", "application/json")
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
