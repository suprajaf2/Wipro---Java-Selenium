package Day24;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

public class DemoWebShopTests {

    WebDriver driver;

    // Runs before all tests in this class
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
    }

    // This test will run normally
    @Test
    public void testHomePageTitle() {
        String title = driver.getTitle();
        System.out.println("Home Page Title: " + title);
    }

    // This test is disabled at compile time
    // It will NOT show as skipped in the report, just ignored
    @Test(enabled = false)
    public void testLoginPage() {
        driver.findElement(By.className("ico-login")).click();
        System.out.println("This test is disabled (won’t execute)");
    }

    //  This test is skipped at runtime
    // It WILL show as SKIPPED in the report
    @Test
    public void testRegisterPage() {
        throw new SkipException("Skipping testRegisterPage (feature under development)");
    }

    // ✅ This test will run normally
    @Test
    public void testSearchBooks() {
        driver.findElement(By.name("q")).sendKeys("books");
        driver.findElement(By.cssSelector("input[value='Search']")).click();
        System.out.println("Search for books executed");
    }

    // Runs after all tests in this class
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


// Entire class is skipped by throwing SkipException in @BeforeClass
class SkippedTestClass {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Instead of running setup, we skip the whole class
        throw new SkipException("Skipping entire SkippedTestClass");
    }

    @Test
    public void testSearch() {
        driver.findElement(By.name("q")).sendKeys("books");
        System.out.println("This will never run because class is skipped");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
