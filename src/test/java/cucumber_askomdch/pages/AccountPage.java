package cucumber_askomdch.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {


    @FindBy(id = "reg_username")
    private WebElement regUsername;

    @FindBy(id = "reg_email")
    private WebElement regEmail;

    @FindBy(id = "reg_password")
    private WebElement regPassword;

    @FindBy(name = "register")
    private WebElement registerButton;


    @FindBy(css = ".woocommerce-MyAccount-navigation")
    private WebElement accountDashboard;

    @FindBy(css = ".woocommerce-error")
    private WebElement errorMessage;


    public void register(String username, String email, String password) {
        type(regUsername, username);
        type(regEmail, email);
        type(regPassword, password);
        click(registerButton);
    }

    public boolean isDashboardVisible() {
        return isDisplayed(accountDashboard);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorVisible() {
        return isDisplayed(errorMessage);
    }
}