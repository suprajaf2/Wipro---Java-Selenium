package Day25;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Saucedemo {
    WebDriver driver;

    @Test(dataProvider="getdata", dataProviderClass = SauceExcelDataProvider.class)
    public void login(String userName, String password) throws InterruptedException {
        System.out.println("Launching the browser.");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        System.out.println("Logging with: " + userName + " / " + password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.id("user-name"))).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(2000);

        try {
            // Case 1: Successful login (Products page visible)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
            System.out.println("✅ Login successful for user: " + userName);

            // Add an item to cart (extra validation step)
            driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
            System.out.println("🛒 Item added to cart successfully for user: " + userName);

        } catch (Exception e) {
            // Case 2: Failed login (error message displayed)
            String errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']")).getText();
            System.out.println("❌ Login failed for user: " + userName + " | Message: " + errorMsg);
            Assert.fail("Login failed for user: " + userName);
        }

        driver.quit();
    }
}
