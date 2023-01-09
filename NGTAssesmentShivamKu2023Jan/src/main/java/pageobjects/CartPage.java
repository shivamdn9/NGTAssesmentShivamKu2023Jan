package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

    private WebDriver driver;

    private final By cartItem = By.linkText("Men Navy Blue Analogue Watch BQ2403");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCartItem() {
        return driver.findElement(cartItem);
    }
}
