package Listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportBase {
    public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void setupExtent() {
        String reportName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        ExtentSparkReporter reporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/reports/OrangeHRM_Report_" + reportName + ".html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @AfterSuite
    public void tearDownExtent() {
        extent.flush();
    }
}
