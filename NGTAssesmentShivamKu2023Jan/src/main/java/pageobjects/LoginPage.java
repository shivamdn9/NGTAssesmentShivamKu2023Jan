package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private final By emailInput = By.id("mobileNumberPass");
    private final By passwordInput = By.cssSelector("input[type='password']");
    private final By submitButton = By.xpath("//button");
    private final By userProfile= By.cssSelector("div.desktop-user");
    private final By searchBar = By.cssSelector("input.desktop-input");
    private final By searchButton = By.cssSelector("div.desktop-submit");
    private final By username = By.cssSelector("div.desktop-infoTitle");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getEmailInput() {
        return driver.findElement(emailInput);
    }

    public WebElement getPasswordInput() {
        return driver.findElement(passwordInput);
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submitButton);
    }

    public WebElement getUserProfile() {
        return driver.findElement(userProfile);
    }

    public WebElement getUsername() {
        return driver.findElement(username);
    }

    public WebElement getSearchBar() {
        return driver.findElement(searchBar);
    }

    public WebElement getSearchButton() {
        return driver.findElement(searchButton);
    }
}
