package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.AccountPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class RegistrationSteps {
    AccountPage accountPage = new AccountPage();

    @When("I register with following details:")
    public void iRegisterWithFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        accountPage.register(
                data.get("username"),
                data.get("email"),
                data.get("password")
        );
    }

    @Then("I should not see any error message")
    public void iShouldNotSeeAnyErrorMessage() {
        Assert.assertFalse("Error should not appear", accountPage.isErrorVisible());
    }
}