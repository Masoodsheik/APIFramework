package Reportlisteners; // Adjust this to your actual package, e.g., com.mycompany.apitest.listeners

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays; // For logging test data if any

public class ExtentReportManagers implements ITestListener {

    private static ExtentReports extent;
    // ThreadLocal ensures that each thread has its own ExtentTest object,
    // which is crucial for parallel test execution and preventing concurrency issues.
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static String reportDirectory = System.getProperty("user.dir") + "/test-output/ExtentReports/";
    private static String reportFileName = "API_Automation_Report.html";

    // This method is called before any test class is run.
    @Override
    public void onStart(ITestContext context) {
        // Ensure the report directory exists
        Path path = Paths.get(reportDirectory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Failed to create report directory: " + e.getMessage());
                // Optionally, throw a runtime exception if report directory creation is critical
            }
        }

        // 1. Configure the Spark Reporter (HTML report)
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDirectory + reportFileName);
        sparkReporter.config().setDocumentTitle("BookStore API Automation Report"); // Title of the browser tab
        sparkReporter.config().setReportName("BookStore API Test Results"); // Name displayed on the report
        sparkReporter.config().setTheme(Theme.STANDARD); // Set report theme (STANDARD or DARK)
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'"); // Custom timestamp format

        // 2. Create an ExtentReports instance and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Optional: Add system information to the report
        extent.setSystemInfo("Host Name", "Localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        System.out.println("ExtentReports initialized. Report will be generated at: " + reportDirectory + reportFileName);
    }

    // This method is called after all test classes have been run.
    @Override
    public void onFinish(ITestContext context) {
        // 3. Flush the report to write all collected data to the HTML file
        if (extent != null) {
            extent.flush();
            System.out.println("ExtentReports flushed. Report generation completed.");
        }
    }

    // This method is called before each test method starts.
    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test entry in the report for each test method
        // result.getMethod().getMethodName() gets the test method's name
        // result.getMethod().getDescription() gets the 'description' attribute from @Test annotation (if used)
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        test.set(extentTest); // Store the ExtentTest object in ThreadLocal for current thread
        System.out.println("Test started: " + result.getMethod().getMethodName());
    }

    // This method is called upon successful execution of a test method.
    @Override
    public void onTestSuccess(ITestResult result) {
        String logText = "<b>TEST CASE: " + result.getMethod().getMethodName().toUpperCase() + " PASSED</b>";
        test.get().log(Status.PASS, logText); // Log test as Passed
        System.out.println("Test passed: " + result.getMethod().getMethodName());
    }

    // This method is called upon failure of a test method.
    @Override
    public void onTestFailure(ITestResult result) {
        String logText = "<b>TEST CASE: " + result.getMethod().getMethodName().toUpperCase() + " FAILED</b>";
        test.get().log(Status.FAIL, logText); // Log test as Failed
        test.get().log(Status.FAIL, result.getThrowable()); // Log the exception/error details
        System.out.println("Test FAILED: " + result.getMethod().getMethodName() + " - " + result.getThrowable().getMessage());

        // Optionally, log test data if the test method uses @DataProvider
        if (result.getParameters().length > 0) {
            test.get().log(Status.INFO, "Test Data: " + Arrays.toString(result.getParameters()));
        }
        // For API tests, you might want to log request/response details on failure.
        // This requires capturing them in a ThreadLocal in your test methods or using RestAssured filters
        // and then passing them here or accessing them from a central place.
    }

    // This method is called when a test method is skipped.
    @Override
    public void onTestSkipped(ITestResult result) {
        String logText = "<b>TEST CASE: " + result.getMethod().getMethodName().toUpperCase() + " SKIPPED</b>";
        test.get().log(Status.SKIP, logText); // Log test as Skipped
        System.out.println("Test SKIPPED: " + result.getMethod().getMethodName());
    }

    // Not commonly used for API tests:
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    // --- Helper methods to log messages from within your test methods ---
    // These methods allow you to add custom messages (info, pass, fail) to the current test in the Extent Report.
    public static void logInfo(String message) {
        if (test.get() != null) {
            test.get().log(Status.INFO, message);
        }
    }

    public static void logPass(String message) {
        if (test.get() != null) {
            test.get().log(Status.PASS, message);
        }
    }

    public static void logFail(String message) {
        if (test.get() != null) {
            test.get().log(Status.FAIL, message);
        }
    }
}