package stepDefinition;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlipkartSteps {
    WebDriver driver;

    // Hooks
    @Before(order = 0) //Order with low  →Before each scenario
    public void setUp() {
        System.out.println("Launching Browser Before Scenario");
        driver = new ChromeDriver();
    }
    @Before(order = 1) //Order with high
    public void setUp1() {
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         driver.manage().window().maximize();
    }

    @After  // After each scenario
    public void tearDown() {
        System.out.println("Closing Browser After Scenario");
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeStep   //Before each step
    public void beforeStep() {
        System.out.println("I will Excute Before Each Step");
    }

    @AfterStep    // After each step
    public void afterStep() {
        System.out.println("I will Excute Before Each Step");
    }

    //  Step Definitions
    @Given("launch Flipkart website")
    public void launch_flipkart_website() {
        driver.get("https://www.flipkart.com");
    }

    @When("search for product {string}")
    public void search_for_product(String product) {
    	 driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"))
         .sendKeys(product);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("should see the search results")
    public void should_see_the_search_results() {
        System.out.println("search results displayed successfully");
    }

    @Then("should see no results message")
    public void should_see_no_results_message() {
        System.out.println("No results found for invalid product");
    }
}