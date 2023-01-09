package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {

    private WebDriver driver;

    private final By addToCartButton = By.cssSelector("div.pdp-add-to-bag");
    private final By goToBagButton = By.cssSelector("a.pdp-goToCart");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(addToCartButton);
    }

    public WebElement getGoToBagButton() {
        return driver.findElement(goToBagButton);
    }
}
