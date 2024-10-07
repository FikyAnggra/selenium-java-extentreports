package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private static ExtentReports extent = new ExtentReports();
    private static ExtentTest parentTest;

    public static void setup() {
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output" +
                "/extentReportHTML.html");
        spark.config().setDocumentTitle("Automation Report");
        spark.config().setReportName("Automation Report");
        spark.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
    }


    public static void startReport(String FeatureName) {
        setup();
        parentTest = extent.createTest(FeatureName);
    }


    public static void statusPassed(String TestName) {
        parentTest.pass("Success " + TestName);
    }

    public static void statusFailed(String TestName) {
        parentTest.fail("Failed " + TestName);
    }

    public static void statusSkip(String TestName) {
        parentTest.skip("Skipped " + TestName);
    }

    public static void endReporting() {
        extent.flush();
    }

}