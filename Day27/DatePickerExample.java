package Day27;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class DatePickerExample {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/datepicker/");
        System.out.println(" Browser launched and DatePicker site opened");
    }

    @Test
    public void selectDate() throws InterruptedException {
        System.out.println("\n--- Handling DatePicker ---");

        // Switch to iframe (datepicker is inside)
        driver.switchTo().frame(0);
        System.out.println("Switched to iframe containing datepicker.");
        Thread.sleep(2000);

        // Click on the input field
        WebElement dateInput = driver.findElement(By.id("datepicker"));
        System.out.println("Clicking on date input field...");
        dateInput.click();
        Thread.sleep(2000);

        // Example: select a specific date (e.g., 22 of current month)
        WebElement date = driver.findElement(By.xpath("//a[text()='22']"));
        System.out.println("Selecting date 22...");
        date.click();
        Thread.sleep(2000);

        // Print selected date
        System.out.println(" Selected Date: " + dateInput.getAttribute("value"));

        // Switch back to main content
        driver.switchTo().defaultContent();
        System.out.println("Switched back to main page.");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        System.out.println("\nClosing browser in 3 seconds...");
        Thread.sleep(3000);
        driver.quit();
        System.out.println("🔹 Browser closed.");
    }
}
