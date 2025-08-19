package Listener;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

public class TestListener extends OrangeHRMLoginTest implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((OrangeHRMLoginTest) testClass).driver;

        try {
            String screenshotPath = captureScreenshot(driver, result.getName());
            ExtentReportBase.test.get().log(Status.FAIL,
                    " Test Failed: " + result.getName())
                    .addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportBase.test.get().log(Status.PASS, " Test Passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportBase.test.get().log(Status.SKIP, " Test Skipped: " + result.getName());
    }

    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}
