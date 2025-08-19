package Excel_Integration;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrangeHRMLoginTest {

    WebDriver driver;

    @Test(dataProvider = "getLoginData", dataProviderClass = OrangeHRMExcelDataProvider.class)
    public void loginTest(String username, String password) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Trying login with: " + username + " / " + password);
        wait.until(ExpectedConditions.elementToBeClickable(By.name("username"))).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        Thread.sleep(2000);

        try {
            // Case 1: Successful login (Dashboard page visible)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
            System.out.println("✅ Login successful for: " + username);
            driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
            driver.findElement(By.xpath("//a[text()='Logout']")).click();

        } catch (Exception e) {
            // Case 2: Failed login
            String errorMsg = driver.findElement(By.cssSelector(".oxd-alert-content-text")).getText();
            System.out.println("❌ Login failed for: " + username + " | Error: " + errorMsg);
            Assert.fail("Login failed for user: " + username);
        }

        driver.quit();
    }
}
