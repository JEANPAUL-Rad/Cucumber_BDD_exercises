
package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.AccountPage;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class LoginSteps {
    AccountPage accountPage = new AccountPage();

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
        String actualError = accountPage.getErrorMessage().toLowerCase();
        String expectedError = errorText.toLowerCase();

        boolean errorFound = switch (expectedError) {
            case "incorrect" -> actualError.contains("incorrect") ||
                    actualError.contains("not registered");
            case "required" -> actualError.contains("required") ||
                    actualError.contains("is empty") ||
                    actualError.contains("field is empty");
            case "not registered" -> actualError.contains("not registered");
            case "password field is empty" -> actualError.contains("password") && actualError.contains("empty");
            case "username is required" -> actualError.contains("username") &&
                    (actualError.contains("required") || actualError.contains("empty"));
            default -> actualError.contains(expectedError);
        };

        Assert.assertTrue(
                "Expected error containing '" + errorText + "' but got: " + accountPage.getErrorMessage(),
                errorFound
        );
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