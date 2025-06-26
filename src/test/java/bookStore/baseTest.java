package bookStore;

import context.TestContext;
import endPoints.endPoints;
import io.restassured.RestAssured;
import Reportlisteners.ExtentReportManagers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class baseTest {
    public static final String BASE_URL = endPoints.baseURL;
    public TestContext testContext; // This instance will be unique per test method
    public  SoftAssert softAssert=new SoftAssert();
    ExtentReportManagers extentReportManagers=new ExtentReportManagers();
    
    @BeforeClass
    static void setupBaseUri() {

        RestAssured.baseURI = BASE_URL;

    }

    @BeforeMethod
    void setupTestContext() {
        testContext = new TestContext();
    }

}
