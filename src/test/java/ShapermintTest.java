import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShapermintTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        // Clear WebDriverManager cache
        WebDriverManager.chromedriver().clearDriverCache();

        // Use WebDriverManager to setup the correct ChromeDriver version
        WebDriverManager.chromedriver().browserVersion("125.0.6422.142").setup();
        
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");  // Example option if needed
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testShapermintCheckout() {
        // Step 1: Go to www.shapermint.com
        driver.get("https://www.shapermint.com");

        // Step 2: Enter the first product within the section "Our Best Sellers"
        HomePage homePage = new HomePage(driver);
        homePage.waitForAndCloseAd();
        homePage.clickOnBestSellers();

        BestSellersPage bestSellersPage = new BestSellersPage(driver);
        bestSellersPage.selectFirstProduct();

        // Step 3: Click on the “add to cart” button and proceed to checkout from the cart
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.switchFrame();
        productPage.proceedToCheckout();

        // Step 4: Complete email data and shipping Address data
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.waitForCheckoutPageToBeLoaded();
        checkoutPage.enterShippingDetails(
                "qa.mail@gmail.com",
                "My Name",
                "My Lastname",
                "123 William Street",
                "apt 1",
                "New York",
                "New York",
                "10038",
                "1234567890"
        );

        // Step 5: Complete credit card data
        checkoutPage.enterCardDetails("1234 1234 1234 1234", "Name Lastname", "01 / 25", "999");

        // Assertions
        // Assert Shipping method "Standard Delivery (4-8 business days)" must be displayed
        assertTrue(checkoutPage.isShippingMethodDisplayed());

        // Assert message "Your card number is invalid." within Payment Information section must be displayed
        assertTrue(checkoutPage.isInvalidCardMessageDisplayed());

        // Assert URL must contain "/hc/checkout/"
        String currentURL = driver.getCurrentUrl();
        assertTrue(currentURL.contains("/hc/checkout/"));
    }
}
