package dtt.lukuvinkkikirjasto.demo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefs {

    @Given("command new is selected")
    public void commandNewIsSelected() {
    }

    @When("title is {string}, author is {string}, ISBN is {string}")
    public void titlesIsAuthorIsISBNIs(String title, String author, String isbn) {
    }

    @Then("system will respond with {string}")
    public void systemWillRespondWith(String string) {
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
    }

    @When("database is empty")
    public void databaseIsEmpty() {
    }

    @When("book with title {string}, author {string} and ISBN {string} is added to reading tips")
    public void bookWithTitleAuthorAndISBNIsAddedToReadingTips(String title, String author, String isbn) {
    }

    @Then("system will respond with book info: title {string}, author {string} and ISBN {string}")
    public void systemWillRespondWithBookInfo(String title, String author, String isbn) {
    }
}
