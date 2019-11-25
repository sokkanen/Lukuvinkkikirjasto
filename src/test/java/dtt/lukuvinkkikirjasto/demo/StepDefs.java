package dtt.lukuvinkkikirjasto.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.assertTrue;

@SpringBootTest
public class StepDefs extends BaseTest {

    WebDriver driver;
    String baseUrl;

    public StepDefs() {
        driver = new FirefoxDriver();
        baseUrl =  "http://localhost:8080";
    }

    @After
    public void tearDownDriver() {
        driver.quit();
    }

    @Given("command new is selected")
    public void commandNewIsSelected() {
        driver.get(baseUrl);
    }

    @When("title is {string}, author is {string}, ISBN is {string}")
    public void titlesIsAuthorIsISBNIs(String title, String author, String isbn) {
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String string) {
        sleep(1);
        assertTrue(driver.getPageSource().contains(string));
    }

    @When("title, author and ISBN is empty")
    public void titleAuthorAndISBNIsEmpty() {
    }

    @When("title is empty, author is {string}, ISBN is {string}")
    public void titleIsEmptyAuthorIsISBNIs(String author, String isbn) {
    }

    @When("title is {string}, author is empty, ISBN is {string}")
    public void titleIsAuthorIsEmptyISBNIs(String title, String isbn) {
    }

    @When("title is {string}, author is {string}, ISBN is empty")
    public void titleIsAuthorIsISBNIsEmpty(String title, String author) {
    }

    @Given("command list all is selected")
    public void commandListAllIsSelected() {
        driver.get(baseUrl);
    }

    @When("database is empty")
    public void databaseIsEmpty() {
    }

    @When("book with title {string}, author {string} and ISBN {string} is added to reading tips")
    public void bookWithTitleAuthorAndISBNIsAddedToReadingTips(String title, String author, String isbn) {
    }

    @Then("system will respond with book info: title {string}, author {string} and ISBN {string}")
    public void systemWillRespondWithBookInfo(String title, String author, String isbn) {
        sleep(1);
        assertTrue(driver.getPageSource().contains(title));
        assertTrue(driver.getPageSource().contains(author));
        assertTrue(driver.getPageSource().contains(isbn));
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
        }
    }
}