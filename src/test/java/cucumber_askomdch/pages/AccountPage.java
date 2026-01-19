package cucumber_askomdch.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

    // Registration Elements
    @FindBy(id = "reg_username")
    private WebElement regUsername;

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement regPassword;

    @FindBy(name = "register")
    private WebElement registerButton;

    // Success/Error Elements
    @FindBy(css = ".woocommerce-MyAccount-navigation")
    private WebElement accountDashboard;

    @FindBy(css = ".woocommerce-error")
    private WebElement errorMessage;

    // Registration Methods (All use explicit waits from BasePage)
    public void register(String username, String email, String password) {
        type(regUsername, username);      // Explicit wait inside
        type(regEmail, email);            // Explicit wait inside
        type(regPassword, password);      // Explicit wait inside
        click(registerButton);            // Explicit wait inside
    }

    public boolean isDashboardVisible() {
        return isDisplayed(accountDashboard); // Explicit wait inside
    }

    public String getErrorMessage() {
        return getText(errorMessage);         // Explicit wait inside
    }

    public boolean isErrorVisible() {
        return isDisplayed(errorMessage);     // Explicit wait inside
    }
}