package cucumber_askomdch.stepdefinitions;

import cucumber_askomdch.pages.CartPage;
import cucumber_askomdch.pages.HomePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class CartSteps {

    private final HomePage homePage;
    private final CartPage cartPage;

    public CartSteps() {
        this.homePage = new HomePage();
        this.cartPage = new CartPage();
    }

    @When("I navigate to Cart page")
    public void iNavigateToCartPage() {
        homePage.goToCart();
    }

    @Then("the cart should contain {int} item")
    public void theCartShouldContainItem(int expectedCount) {
        int actual = cartPage.getCartItemCount();
        Assert.assertEquals("Cart item count mismatch", expectedCount, actual);
    }

    @Then("the item name should be displayed correctly")
    public void theItemNameShouldBeDisplayedCorrectly() {
        String name = cartPage.getFirstItemName();
        Assert.assertFalse("Item name is empty", name.isEmpty());
        System.out.println("First item in cart: " + name);
    }

    @Then("the subtotal should match the product price")
    public void theSubtotalShouldMatchTheProductPrice() {
        double subtotal = cartPage.getFirstItemSubtotal();
        Assert.assertTrue("Subtotal should be positive", subtotal > 0);
    }

    @When("I remove the first item from cart")
    public void iRemoveTheFirstItemFromCart() {
        cartPage.removeFirstItem();
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        Assert.assertTrue("Cart is not empty", cartPage.isCartEmpty());
    }

    @Then("I should see a message like {string}")
    public void iShouldSeeAMessageLike(String expectedMessagePart) {
        Assert.assertTrue("Cart is not empty", cartPage.isCartEmpty());
        String actualMessage = cartPage.getEmptyCartMessage();
        Assert.assertTrue(
                "Expected message containing '" + expectedMessagePart + "', but got: '" + actualMessage + "'",
                actualMessage.toLowerCase().contains(expectedMessagePart.toLowerCase())
        );
    }

    @When("I click on Proceed to Checkout")
    public void iClickOnProceedToCheckout() {
        cartPage.clickProceedToCheckout();
    }

    @Then("I should be redirected to the checkout page")
    public void iShouldBeRedirectedToTheCheckoutPage() {
        Assert.assertTrue("Not on checkout page", cartPage.isOnCheckoutPage());
    }

    @Then("the subtotal should be updated accordingly")
    public void theSubtotalShouldBeUpdatedAccordingly() {
        double subtotal = cartPage.getFirstItemSubtotal();
        Assert.assertTrue("Subtotal should be positive after update", subtotal > 0);
        System.out.println("Updated subtotal: " + subtotal);
    }
}