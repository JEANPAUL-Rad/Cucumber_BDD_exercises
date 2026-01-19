package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.AccountPage;
import cucumber_askomdch.pages.HomePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class LoginSteps {
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

    @When("I login with following credentials:")
    public void iLoginWithFollowingCredentials(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMaps().get(0);
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username != null && !username.isEmpty()) {
            accountPage.enterLoginUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            accountPage.enterLoginPassword(password);
        }
        accountPage.clickLoginButton();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String username, String password) {
        if (username != null && !username.isEmpty()) {
            accountPage.enterLoginUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            accountPage.enterLoginPassword(password);
        }
        accountPage.clickLoginButton();
    }

    @When("I click on logout link")
    public void iClickOnLogoutLink() {
        accountPage.clickLogout();
    }

    @Then("I should see account dashboard")
    public void iShouldSeeAccountDashboard() {
        Assert.assertTrue("Account dashboard should be visible",
                accountPage.isDashboardVisible());
    }

    @Then("I should see logout link")
    public void iShouldSeeLogoutLink() {
        Assert.assertTrue("Logout link should be visible",
                accountPage.isLogoutLinkVisible());
    }

    @Then("I should see error message containing {string}")
    public void iShouldSeeErrorMessageContaining(String errorText) {
        String actualError = accountPage.getErrorMessage();
        Assert.assertTrue("Expected error containing '" + errorText + "' but got: " + actualError,
                actualError.toLowerCase().contains(errorText.toLowerCase()));
    }

    @Then("I should see login form")
    public void iShouldSeeLoginForm() {
        Assert.assertTrue("Login form should be visible",
                accountPage.isLoginFormVisible());
    }

    @Then("I should see {string} text on the page")
    public void iShouldSeeTextOnThePage(String text) {
        Assert.assertTrue("Expected text '" + text + "' not found on page",
                accountPage.pageContainsText(text));
    }
}