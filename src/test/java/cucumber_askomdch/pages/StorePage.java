package cucumber_askomdch.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.stream.Collectors;

public class StorePage extends BasePage {

    @FindBy(id = "min_price")
    private WebElement minPriceInput;

    @FindBy(id = "max_price")
    private WebElement maxPriceInput;

    @FindBy(css = "button[type='submit']")
    private WebElement filterButton;

    @FindBy(css = ".woocommerce-loop-product__title")
    private List<WebElement> productTitles;

    @FindBy(css = ".price .woocommerce-Price-amount")
    private List<WebElement> productPrices;

    @FindBy(css = "li.product")
    private List<WebElement> products;

    @FindBy(css = ".woocommerce-result-count")
    private WebElement resultCount;


    public void navigateToStore() {
        driver.get(driver.getCurrentUrl() + "store");
    }


    public void setMinPrice(String minPrice) {
        clearAndType(minPriceInput, minPrice);
    }

    public void setMaxPrice(String maxPrice) {
        clearAndType(maxPriceInput, maxPrice);
    }

    public void clickFilterButton() {
        scrollToElement(filterButton);
        click(filterButton);
        waitForPageLoad();
    }


    public List<Double> getAllProductPrices() {
        waitForPageLoad();
        return productPrices.stream()
                .map(this::extractPrice)
                .collect(Collectors.toList());
    }


    private Double extractPrice(WebElement priceElement) {
        String priceText = priceElement.getText();

        String cleanPrice = priceText.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanPrice);
    }


    public boolean areAllPricesInRange(double minPrice, double maxPrice) {
        List<Double> prices = getAllProductPrices();
        return prices.stream()
                .allMatch(price -> price >= minPrice && price <= maxPrice);
    }


    public boolean areAllPricesAboveMin(double minPrice) {
        List<Double> prices = getAllProductPrices();
        return prices.stream()
                .allMatch(price -> price >= minPrice);
    }


    public int getProductCount() {
        waitForPageLoad();
        return products.size();
    }


    public boolean urlContains(String text) {
        return driver.getCurrentUrl().contains(text);
    }


    private void clearAndType(WebElement element, String text) {
        wait.until(driver -> element.isDisplayed());
        element.clear();
        element.sendKeys(text);
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500); // Small wait after scroll
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForPageLoad() {
        wait.until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
        );
    }
}