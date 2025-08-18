package Day23;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        System.out.println("Navigated to SauceDemo");
    }

    /**
     * Utility method to wait for and close a popup if it appears.
     * If popup is not present, it just continues execution.
     */
    public void waitAndClosePopup() {
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement popup = shortWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("popup-id"))
            );
            popup.findElement(By.cssSelector(".close-button")).click();
            System.out.println("Popup closed successfully");
        } catch (Exception e) {
            System.out.println("Popup did not appear, continuing...");
        }
    }

    @Test
    public void validLoginAddToCartCheckoutAndLogout() throws InterruptedException {
        // Login
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        System.out.println(" Logged in successfully");

        // Check & close popup if it appears
        waitAndClosePopup();
        Thread.sleep(2000); // Pause to observe login

        // Add product to cart
        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
        System.out.println(" Added product to cart");
        Thread.sleep(2000);

        // Go to cart
        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();
        Thread.sleep(2000);

        // Checkout
        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();
        Thread.sleep(2000);

        // Fill checkout info
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("John");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).sendKeys("Doe");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))).sendKeys("12345");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();
        Thread.sleep(2000);

        // Finish order
        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();

        // Verify order confirmation
        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        System.out.println(" Order Completed: " + confirmation.getText());
        Thread.sleep(2000);

        // Back to Home
        wait.until(ExpectedConditions.elementToBeClickable(By.id("back-to-products"))).click();
        System.out.println(" Navigated back to home page");
        Thread.sleep(2000);

        // Logout
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
        System.out.println(" Logged out successfully");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
