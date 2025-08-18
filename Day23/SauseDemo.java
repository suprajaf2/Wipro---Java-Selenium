package Day23;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SauseDemo {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.out.println("Launching Chrome browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Maximized browser window.");
        driver.get("https://www.saucedemo.com/");
        System.out.println("Navigated to https://www.saucedemo.com/");
    }

    @Test
    public void validLoginAndAddToCart() {
        // Enter username
        WebElement usernameField = driver.findElement(By.id("user-name"));
        System.out.println("Entering username: standard_user");
        usernameField.sendKeys("standard_user");

        // Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        System.out.println("Entering password: secret_sauce");
        passwordField.sendKeys("secret_sauce");

        // Click login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        System.out.println("Clicking login button...");
        loginButton.click();

        // Verify login success
        WebElement productsTitle = driver.findElement(By.className("title"));
        if (productsTitle.isDisplayed()) {
            System.out.println(" Login successful and on Products page.");
        } else {
            System.out.println(" Login failed.");
            return; // Stop test if login fails
        }

        // Add first product to cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        System.out.println("Clicking 'Add to cart' for Sauce Labs Backpack...");
        addToCartButton.click();

        // Verify cart icon count
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        if (cartBadge.isDisplayed()) {
            System.out.println("Item successfully added to cart. Cart count: " + cartBadge.getText());
        } else {
            System.out.println(" Failed to add item to cart.");
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        System.out.println("Test completed. Waiting 8 seconds to observe result...");
        Thread.sleep(8000);
        System.out.println("Closing browser...");
        driver.quit();
    }
}



