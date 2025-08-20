package Day27;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class HandleAlert {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        System.out.println(" Browser launched and site opened");
    }

    @Test
    public void handleAlert() throws InterruptedException {
        System.out.println("\n--- Handling JS Alert ---");

        System.out.println("Clicking JS Alert button...");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert Text: " + alert.getText());
        Thread.sleep(2000);

        System.out.println("Accepting alert...");
        alert.accept();
        Thread.sleep(2000);

        System.out.println("JS Alert handled successfully!");
    }

    @Test
    public void handleConfirm() throws InterruptedException {
        System.out.println("\n--- Handling JS Confirm ---");

        System.out.println("Clicking JS Confirm button...");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Thread.sleep(2000);

        Alert confirm = driver.switchTo().alert();
        System.out.println("Confirm Text: " + confirm.getText());
        Thread.sleep(2000);

        System.out.println("Dismissing confirm...");
        confirm.dismiss();
        Thread.sleep(2000);

        System.out.println("JS Confirm handled successfully!");
    }

    @Test
    public void handlePrompt() throws InterruptedException {
        System.out.println("\n--- Handling JS Prompt ---");

        System.out.println("Clicking JS Prompt button...");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Thread.sleep(2000);

        Alert prompt = driver.switchTo().alert();
        System.out.println("Prompt Text: " + prompt.getText());
        Thread.sleep(2000);

        System.out.println("Sending input to prompt...");
        prompt.sendKeys("Supraja Test");
        Thread.sleep(2000);

        System.out.println("Accepting prompt...");
        prompt.accept();
        Thread.sleep(2000);

        System.out.println("JS Prompt handled successfully!");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        System.out.println("\nClosing browser in 3 seconds...");
        Thread.sleep(3000);
        driver.quit();
        System.out.println("Browser closed.");
    }
}
