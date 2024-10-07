package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // Mulai laporan saat test case dimulai
        ExtentReport.startReport(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Tandai test case sebagai passed
        ExtentReport.statusPassed("Test Case "+result.getMethod().getMethodName());
        System.out.println(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Tandai test case sebagai failed
        ExtentReport.statusFailed("Test Case "+result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Tandai test case sebagai skipped
        ExtentReport.statusSkip("Test Case "+result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.endReporting();
    }
}
