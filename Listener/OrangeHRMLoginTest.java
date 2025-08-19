package Listener;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Listeners(Listener.TestListener.class)   // ✅ Attach Listener
public class OrangeHRMLoginTest extends ExtentReportBase {

    WebDriver driver;

    @Test(dataProvider = "getData", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(String username, String password) throws IOException {
        ExtentTest localTest = extent.createTest("Login Test with username: " + username);
        test.set(localTest);

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
            test.get().log(Status.PASS, "Login successful with: " + username);
        } catch (Exception e) {
            // Fail handled by Listener (no need to duplicate here)
            Assert.fail("Login failed for: " + username);
        }

        driver.quit();
    }

    // 📷 Screenshot method
    public String captureScreenshot(WebDriver driver, String fileName) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dir = System.getProperty("user.dir") + "/screenshots/";
        File screenshotDir = new File(dir);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdirs();
        }
        String path = dir + fileName + ".png";
        FileHandler.copy(src, new File(path));
        return path;
    }
}
