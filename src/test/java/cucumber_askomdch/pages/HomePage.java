package cucumber_askomdch.pages;

import cucumber_askomdch.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "Account")
    private WebElement accountLink;

    @FindBy(linkText = "Store")
    private WebElement storeLink;

    @FindBy(css = "a[href*='store']")
    private WebElement storeMenu;

    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void goToAccount() {
        click(accountLink);
    }
    public void goToStore() {
        click(storeLink);
    }
}