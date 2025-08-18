package Day24;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Multiplegroups {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/login");
    }

    @Test(groups = {"sanity", "regression"})
    public void validLoginTest() throws InterruptedException {
        driver.findElement(By.id("userName")).sendKeys("testuser");
        driver.findElement(By.id("password")).sendKeys("Test@1234");
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);

        WebElement logoutBtn = driver.findElement(By.id("submit"));
        if (logoutBtn.isDisplayed()) {
            System.out.println("✅ Valid Login Passed");
        }
        driver.findElement(By.id("submit")).click(); // logout
    }

    @Test(groups = {"regression"})
    public void invalidLoginTest() throws InterruptedException {
        driver.findElement(By.id("userName")).sendKeys("wronguser");
        driver.findElement(By.id("password")).sendKeys("wrongpass");
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);

        WebElement errorMsg = driver.findElement(By.id("name"));
        if (errorMsg.isDisplayed()) {
            System.out.println("✅ Invalid Login Test Passed");
        }
    }

    @Test(groups = {"smoke"})
    public void loginPageUITest() {
        boolean isUserNameDisplayed = driver.findElement(By.id("userName")).isDisplayed();
        boolean isPasswordDisplayed = driver.findElement(By.id("password")).isDisplayed();
        boolean isLoginBtnDisplayed = driver.findElement(By.id("login")).isDisplayed();

        if (isUserNameDisplayed && isPasswordDisplayed && isLoginBtnDisplayed) {
            System.out.println("✅ Login Page UI elements are displayed");
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
