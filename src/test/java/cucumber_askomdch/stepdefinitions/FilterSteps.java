package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.factory.DriverFactory;
import cucumber_askomdch.pages.StorePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class FilterSteps {

    private final StorePage storePage = new StorePage();

    @When("I set minimum price to {string}")
    public void iSetMinimumPriceTo(String minPrice) {
        storePage.setMinPrice(minPrice);
    }

    @When("I set maximum price to {string}")
    public void iSetMaximumPriceTo(String maxPrice) {
        storePage.setMaxPrice(maxPrice);
    }

    @When("I click on Filter button")
    public void iClickOnFilterButton() {
        storePage.clickFilterButton();
    }

    @Then("URL should contain {string}")
    public void urlShouldContain(String expectedText) {
        String actualUrl = DriverFactory.getDriver().getCurrentUrl();

        Assert.assertTrue(
                "URL should contain '" + expectedText + "'. Actual URL: " + actualUrl,
                actualUrl.contains(expectedText)
        );
    }

    @Then("I should see at least {int} product")
    public void iShouldSeeAtLeastProduct(int expectedCount) {
        int actual = storePage.getProductCount();
        Assert.assertTrue(
                "Expected at least " + expectedCount + " products, found " + actual,
                actual >= expectedCount
        );
    }

    @Then("all displayed products should be within price range {string} to {string}")
    public void allDisplayedProductsShouldBeWithinPriceRange(String min, String max) {
        double minVal = Double.parseDouble(min);
        double maxVal = Double.parseDouble(max);
        boolean inRange = storePage.areAllPricesInRange(minVal, maxVal);
        Assert.assertTrue(
                "Price filter didn't work (site limitation) - not all products in " + min + " to " + max,
                inRange
        );
    }
}