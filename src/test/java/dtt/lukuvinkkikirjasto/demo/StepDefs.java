package dtt.lukuvinkkikirjasto.demo;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class StepDefs extends BaseTest {

    WebDriver driver;
    String baseUrl;

    @Before
    public void setUp() throws SQLException {
        driver = new HtmlUnitDriver();
        baseUrl = "http://localhost:8080";
        initialize();
    }

    @Given("command new is selected")
    public void commandNewIsSelected() {
        driver.get(baseUrl);
    }

    @When("title is {string}, author is {string}, ISBN is {string}")
    public void titlesIsAuthorIsISBNIs(String title, String author, String isbn) {
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(title);
        element = driver.findElement(By.id("author"));
        element.sendKeys(author);
        element = driver.findElement(By.id("isbn"));
        element.sendKeys(isbn);
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String string) throws SQLException, IOException {
        // System.out.println(driver.getPageSource());
        assertTrue(driver.getPageSource().contains(string));
        removeTestData();
    }

    @When("title, author and ISBN is empty")
    public void titleAuthorAndISBNIsEmpty() {
        WebElement element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @When("title is empty, author is {string}, ISBN is {string}")
    public void titleIsEmptyAuthorIsISBNIs(String author, String isbn) {
        WebElement element = driver.findElement(By.id("author"));
        element.sendKeys(author);
        element = driver.findElement(By.id("isbn"));
        element.sendKeys(isbn);
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @When("title is empty, author is empty, ISBN is {string}")
    public void titleIsEmptyAuthorIsEmptyISBNIs(String isbn) {
        WebElement element = driver.findElement(By.id("isbn"));
        element.sendKeys(isbn);
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @When("title is {string}, author is empty, ISBN is {string}")
    public void titleIsAuthorIsEmptyISBNIs(String title, String isbn) {
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(title);
        element = driver.findElement(By.id("isbn"));
        element.sendKeys(isbn);
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @When("title is {string}, author is {string}, ISBN is empty")
    public void titleIsAuthorIsISBNIsEmpty(String title, String author) {
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(title);
        element = driver.findElement(By.id("author"));
        element.sendKeys(author);
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @Given("command list all is selected")
    public void commandListAllIsSelected() {
        driver.get(baseUrl);
    }

    @When("database is empty")
    public void databaseIsEmpty() throws IOException, SQLException {
    }

    @When("book with title {string}, author {string} and ISBN {string} is added to reading tips")
    public void bookWithTitleAuthorAndISBNIsAddedToReadingTips(String title, String author, String isbn) {
        WebElement element = driver.findElement(By.id("title"));
        element.sendKeys(title);
        element = driver.findElement(By.id("author"));
        element.sendKeys(author);
        element = driver.findElement(By.id("isbn"));
        element.sendKeys(isbn);
        element = driver.findElement(By.id("submit"));
        element.submit();
    }

    @Then("system will respond with book info: title {string}, author {string} and ISBN {string}")
    public void systemWillRespondWithBookInfo(String title, String author, String isbn)
            throws SQLException, IOException {
        assertTrue(driver.getPageSource().contains(title));
        assertTrue(driver.getPageSource().contains(author));
        assertTrue(driver.getPageSource().contains(isbn));
        removeTestData();
    }

    @Then("system will show book info: title {string} and author {string}")
    public void systemWillRespondWithBookInfoNarrow(String title, String author) throws SQLException, IOException {
        // System.out.println(driver.getPageSource());
        assertTrue(driver.getPageSource().contains(title));
        assertTrue(driver.getPageSource().contains(author));
        removeTestData();
    }

    @When("book with title {string} is deleted")
    public void bookWithTitleIsDeleted(String title) {
        WebElement element = driver.findElement(By.id("deleteBook_" + title));
        element.submit();
    }

    @Then("system does not respond with book title {string}")
    public void systemDoesNotRespondWithBookTitle(String title) throws SQLException, IOException {
        assertFalse(driver.getPageSource().contains(title));
        removeTestData();
    }

    @Given("command mark as read is selected")
    public void commandMarkAsReadIsSelected() {
        WebElement element = driver.findElement(By.linkText("mark read"));
        throw new cucumber.api.PendingException();
    }

    @When("book exists")
    public void bookExists() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("book is marked as read")
    public void bookIsMarkedAsRead() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
        }
    }
}
