

package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.StorePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class FilterSteps {
    StorePage storePage = new StorePage();

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

    @Then("all displayed products should be within price range {string} to {string}")
    public void allDisplayedProductsShouldBeWithinPriceRange(String minPrice, String maxPrice) {
        double min = Double.parseDouble(minPrice);
        double max = Double.parseDouble(maxPrice);

        boolean allInRange = storePage.areAllPricesInRange(min, max);
        Assert.assertTrue(
                "Not all products are within price range " + minPrice + " to " + maxPrice,
                allInRange
        );
    }

    @Then("all displayed products should have price greater than or equal to {string}")
    public void allDisplayedProductsShouldHavePriceGreaterThanOrEqualTo(String minPrice) {
        double min = Double.parseDouble(minPrice);

        boolean allAboveMin = storePage.areAllPricesAboveMin(min);
        Assert.assertTrue(
                "Not all products have price >= " + minPrice,
                allAboveMin
        );
    }

    @Then("I should see at least {int} product")
    public void iShouldSeeAtLeastProduct(int expectedCount) {
        int actualCount = storePage.getProductCount();
        Assert.assertTrue(
                "Expected at least " + expectedCount + " product(s), but found " + actualCount,
                actualCount >= expectedCount
        );
    }

    @Then("URL should contain {string}")
    public void urlShouldContain(String expectedText) {
        Assert.assertTrue(
                "URL should contain '" + expectedText + "'",
                storePage.urlContains(expectedText)
        );
    }
}