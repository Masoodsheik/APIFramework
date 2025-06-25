package bookStore;

import context.TestContext;
import io.restassured.RestAssured;
import listeners.ExtentReportManagers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class baseTest {
    public static final String BASE_URL = "http://127.0.0.1:8000";
    public TestContext testContext; // This instance will be unique per test method
    public  SoftAssert softAssert=new SoftAssert();
    ExtentReportManagers extentReportManagers=new ExtentReportManagers();
    @BeforeClass
    static void setupBaseUri() {

        RestAssured.baseURI = BASE_URL; //ExtentReporterListener.logInfo("API Test setup completed. Base URI: " + BASE_URL);

    }

    @BeforeMethod
    void setupTestContext() {
        testContext = new TestContext();
    }
    @AfterMethod
    void cleanup(){

    }
}
