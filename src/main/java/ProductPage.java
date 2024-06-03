import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private By addToCartButton = By.xpath("//button[@data-testid='add-to-cart']");
    private By checkoutButton = By.xpath("//div[@class='css-199m12q']//button[@data-testid='proceed-to-checkout']");
    private By adCross = By.xpath("//*[@id='justuno_form']//font[contains(@face, 'montserrat')]/parent::span/parent::span/parent::div");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }

    public void proceedToCheckout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        driver.findElement(checkoutButton).click();
    }

    public void switchFrame() {
        driver.switchTo().defaultContent();
    }
}
