package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @When("I add a product to the cart")
    public void iAddAProductToTheCart() {
        homePage.goToStore();


        WebElement firstAddToCartButton = homePage.getDriver()
                .findElement(By.cssSelector("li.product .add_to_cart_button"));


        homePage.getWait().until(ExpectedConditions.elementToBeClickable(firstAddToCartButton));
        firstAddToCartButton.click();


        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Added first product to cart");
    }
}