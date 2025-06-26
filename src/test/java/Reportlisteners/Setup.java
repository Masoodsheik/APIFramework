package Reportlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Setup implements ITestListener {

    private  static ExtentReports extentReports;
    private static String reportFilePath = System.getProperty("user.dir") + "\\Reports\\";
    public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();

    public void onStart(ITestContext context) {
//
//        String fileName=ExtentReportManagers.getReportNameWithTimeStamp();
//        System.out.println(reportFilePath+fileName);
//        extentReports=ExtentReportManagers.createInstance(reportFilePath+fileName,"API Test Automation Report","Test ExecutionReport");

    }



    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    public void onTestStart(ITestResult result) {
       ExtentTest test=extentReports.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
        extentTest.set(test);
    }

    public static void logInfo(String message) {
        Setup.extentTest.get().pass(MarkupHelper.createLabel(message, ExtentColor.GREY));
    }

    public static void logPass(String message) {
      Setup.extentTest.get().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void logFail(String message) {
        Setup.extentTest.get().pass(MarkupHelper.createLabel(message, ExtentColor.RED));
    }

    public static void logWarning(String message) {
        Setup.extentTest.get().pass(MarkupHelper.createLabel(message, ExtentColor.ORANGE));
    }

}
