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

    // Login Elements
    @FindBy(id = "username")
    private WebElement loginUsername;

    @FindBy(id = "password")
    private WebElement loginPassword;

    @FindBy(name = "login")
    private WebElement loginButton;

    // Dashboard & Logout
    @FindBy(css = ".woocommerce-MyAccount-navigation")
    private WebElement accountDashboard;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    // Error Messages
    @FindBy(css = ".woocommerce-error")
    private WebElement errorMessage;

    @FindBy(css = ".woocommerce-error li")
    private WebElement errorMessageItem;

    // Login Form Check
    @FindBy(css = "form.woocommerce-form-login")
    private WebElement loginForm;

    // --- Registration Methods ---
    public void register(String username, String email, String password) {
        type(regUsername, username);
        type(regEmail, email);
        type(regPassword, password);
        click(registerButton);
    }

    // --- Login Methods ---
    public void login(String username, String password) {
        type(loginUsername, username);
        type(loginPassword, password);
        click(loginButton);
    }

    public void enterLoginUsername(String username) {
        type(loginUsername, username);
    }

    public void enterLoginPassword(String password) {
        type(loginPassword, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickLogout() {
        click(logoutLink);
    }

    // --- Verification Methods ---
    public boolean isDashboardVisible() {
        return isDisplayed(accountDashboard);
    }

    public boolean isLogoutLinkVisible() {
        return isDisplayed(logoutLink);
    }

    public boolean isLoginFormVisible() {
        return isDisplayed(loginForm);
    }

    public String getErrorMessage() {
        if (isDisplayed(errorMessageItem)) {
            return getText(errorMessageItem);
        }
        return getText(errorMessage);
    }

    public boolean isErrorVisible() {
        return isDisplayed(errorMessage);
    }

    public boolean pageContainsText(String text) {
        return driver.getPageSource().contains(text);
    }
}