import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.CartPage;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ResultsPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class MyntraTest {
    private WebDriver driver;
    private Properties props;
    private Actions actions;

    private LoginPage loginPage;
    private ResultsPage resultsPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setup() {
        // set driver path
        String root = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", root + "/src/test/resources/chromedriver.exe");

        // init driver & actions
        driver = new ChromeDriver();
        actions = new Actions(driver);

        // driver settings
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.manage().window().maximize();

        // read props file
        try {
            props = new Properties();
            props.load(Files.newBufferedReader(Paths.get(root + "/src/test/resources/application.properties")));
        } catch (IOException exception) {
            System.err.println("Error loading properties file: " + exception.getMessage());
        }

        // navigate to app
        driver.get(props.getProperty("appUrl"));


        loginPage = new LoginPage(driver);
        resultsPage = new ResultsPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void loginAndValidate() throws InterruptedException {
        // login using credentials
        loginPage.getEmailInput().sendKeys(props.getProperty("email"));
        loginPage.getPasswordInput().sendKeys(props.getProperty("password"));
        loginPage.getSubmitButton().click();

        // workaround to avoid logout
        Thread.sleep(32000);
        loginPage.getSubmitButton().click();
        Thread.sleep(5000);

        // assert page title
        String expectedTitle = "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);

        // assert username
        actions.moveToElement(loginPage.getUserProfile()).perform();
        String actualMessage = loginPage.getUsername().getText();
        String expectedMessage = "Hello Shivam" + props.getProperty("username");
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void addItemsToCart () {
        // search item
        loginPage.getSearchBar().sendKeys(props.getProperty("item"));
        loginPage.getSearchButton().click();

        // click search button
        resultsPage.getItemCard().click();

        // switch to newly opened tab
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(handle);
            }
        }

        // click add to cart button
        productPage.getAddToCartButton().click();

        // click go to bag button
        productPage.getGoToBagButton().click();

        // assert product name
        String actualItemName = cartPage.getCartItem().getText();
        String expectedItemName = props.getProperty("item");
        Assert.assertEquals(actualItemName, expectedItemName);
    }

    @AfterMethod
    public void teardown() {
         driver.quit();
    }
}
