package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonSteps {
    HomePage homePage = new HomePage();

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.open();
    }

    @When("I click on Account link")
    public void iClickOnAccountLink() {
        homePage.goToAccount();
    }

    @When("I navigate to Store page")
    public void iNavigateToStorePage() {
        homePage.goToStore();
    }
}