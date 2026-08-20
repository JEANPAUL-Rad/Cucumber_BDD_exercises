package cucumber_askomdch.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class StorePage extends BasePage {

    @FindBy(name = "min_price")
    private WebElement minPriceInput;

    @FindBy(name = "max_price")
    private WebElement maxPriceInput;


    @FindBy(css = "button[type='submit'].button")
    private WebElement filterButton;


    @FindBy(css = "li.product")
    private List<WebElement> products;


    @FindBy(css = "li.product .price .woocommerce-Price-amount")
    private List<WebElement> productPrices;


    @FindBy(css = ".woocommerce-result-count")
    private WebElement resultCount;



    public void setMinPrice(String minPrice) {
        setHiddenInputValue(minPriceInput, minPrice);
    }

    public void setMaxPrice(String maxPrice) {
        setHiddenInputValue(maxPriceInput, maxPrice);
    }

    private void setHiddenInputValue(WebElement element, String value) {
        By locator = By.id(element.getAttribute("id"));


        wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, value);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', {bubbles: true}));", element);

        System.out.println("Set hidden input " + element.getAttribute("name") + " to: " + value);
    }

    public void clickFilterButton() {
        String originalUrl = driver.getCurrentUrl();

        wait.until(ExpectedConditions.elementToBeClickable(filterButton));
        scrollToElement(filterButton);
        filterButton.click();

        waitForPageLoad();
        wait.until(d -> !d.getCurrentUrl().equals(originalUrl) ||
                d.getCurrentUrl().contains("min_price") ||
                d.getCurrentUrl().contains("max_price"));

        System.out.println("After filter click - Current URL: " + driver.getCurrentUrl());
    }

    public List<Double> getAllProductPrices() {
        waitForPageLoad();
        wait.until(ExpectedConditions.visibilityOfAllElements(products));

        List<Double> prices = productPrices.stream()
                .map(this::extractPrice)
                .filter(p -> p > 0)
                .collect(Collectors.toList());

        System.out.println("Extracted " + prices.size() + " prices: " + prices);
        return prices;
    }

    private Double extractPrice(WebElement priceElement) {
        String priceText = priceElement.getText().trim();
        System.out.println("Raw price text: " + priceText);


        String clean = priceText.replaceAll("[^0-9.]", "");
        if (clean.isEmpty()) return 0.0;

        try {
            double price = Double.parseDouble(clean);
            return price;
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse price: " + clean);
            return 0.0;
        }
    }

    public boolean areAllPricesInRange(double min, double max) {
        List<Double> prices = getAllProductPrices();
        return prices.stream().allMatch(p -> p >= min && p <= max);
    }

    public int getProductCount() {
        return products.size();
    }

    public boolean urlContains(String text) {
        String url = driver.getCurrentUrl();
        boolean contains = url.contains(text);
        System.out.println("URL check: '" + text + "' in '" + url + "' → " + contains);
        return contains;
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }
}