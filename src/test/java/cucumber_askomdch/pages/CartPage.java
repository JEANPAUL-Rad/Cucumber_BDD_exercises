package cucumber_askomdch.pages;

import cucumber_askomdch.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = "h1.entry-title, .cart-title")
    private WebElement cartTitle;

    @FindBy(css = "tr.woocommerce-cart-form__cart-item")
    private List<WebElement> cartItems;

    @FindBy(css = "tr.woocommerce-cart-form__cart-item:first-child .product-name")
    private WebElement firstItemName;


    @FindBy(css = "tr.woocommerce-cart-form__cart-item:first-child .product-subtotal .woocommerce-Price-amount")
    private WebElement firstItemSubtotal;

    @FindBy(css = "button[name='update_cart']")
    private WebElement updateCartButton;

    @FindBy(css = "a.checkout-button.button.alt.wc-forward")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".cart-empty.woocommerce-info")
    private WebElement emptyCartMessage;

    @FindBy(css = "tr.woocommerce-cart-form__cart-item:first-child .product-remove a.remove")
    private WebElement removeFirstItemLink;

    public void navigateToCart() {
        String baseUrl = ConfigReader.get("baseUrl");

        String cartUrl = baseUrl.endsWith("/") ? baseUrl + "cart/" : baseUrl + "/cart/";

        driver.get(cartUrl);
        waitForPageLoad();

    }

    public int getCartItemCount() {
        return cartItems.size();
    }


    public String getFirstItemName() {
        return getText(firstItemName);
    }


    public double getFirstItemSubtotal() {
        String text = getText(firstItemSubtotal);
        return extractPrice(text);
    }

    private double extractPrice(String text) {
        String clean = text.replaceAll("[^0-9.]", "");
        return clean.isEmpty() ? 0.0 : Double.parseDouble(clean);
    }

    public void removeFirstItem() {
        click(removeFirstItemLink);
        waitForPageLoad();
    }

    public boolean isCartEmpty() {
        return isDisplayed(emptyCartMessage);
    }

    public String getEmptyCartMessage() {
        return getText(emptyCartMessage);
    }

    public void clickProceedToCheckout() {
        click(proceedToCheckoutButton);
        waitForPageLoad();
    }

    public boolean isOnCheckoutPage() {
        return driver.getCurrentUrl().contains("/checkout/");
    }
}