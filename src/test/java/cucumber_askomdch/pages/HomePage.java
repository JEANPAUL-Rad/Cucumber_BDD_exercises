package cucumber_askomdch.pages;

import cucumber_askomdch.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(linkText = "Account")
    private WebElement accountLink;

    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void goToAccount() {
        click(accountLink);
    }
}