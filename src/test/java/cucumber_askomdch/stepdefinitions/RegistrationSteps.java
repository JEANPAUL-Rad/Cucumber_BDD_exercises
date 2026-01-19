package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.AccountPage;
import cucumber_askomdch.pages.HomePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class RegistrationSteps {
    HomePage homePage = new HomePage();
    AccountPage accountPage = new AccountPage();

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        homePage.open();
    }

    @When("I click on Account link")
    public void iClickOnAccountLink() {
        homePage.goToAccount();
    }

    @When("I register with following details:")
    public void iRegisterWithFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        accountPage.register(
                data.get("username"),
                data.get("email"),
                data.get("password")
        );
    }

    @Then("I should see account dashboard")
    public void iShouldSeeAccountDashboard() {
        Assert.assertTrue("Dashboard not visible", accountPage.isDashboardVisible());
    }

    @Then("I should not see any error message")
    public void iShouldNotSeeAnyErrorMessage() {
        Assert.assertFalse("Error should not appear", accountPage.isErrorVisible());
    }

    @Then("I should see error message containing {string}")
    public void iShouldSeeErrorMessageContaining(String errorText) {
        String actualError = accountPage.getErrorMessage();
        Assert.assertTrue("Expected error not found",
                actualError.toLowerCase().contains(errorText.toLowerCase()));
    }
}