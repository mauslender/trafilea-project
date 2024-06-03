import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    private WebDriver driver;

    private By emailField = By.xpath("//input[@name='email']");
    private By firstNameField = By.xpath("//input[@name='delivery.buyer_name']");
    private By lastNameField = By.xpath("//input[@name='delivery.buyer_lastname']");
    private By addressField = By.xpath("//input[@name='delivery.address1']");
    private By aptSuiteField = By.xpath("//input[@name='delivery.address2']");
    private By cityField = By.xpath("//input[@name='delivery.city']");
    private By stateField = By.id("headlessui-listbox-button-3");
    private By postalCodeField = By.xpath("//input[@name='delivery.zip']");
    private By phoneField = By.xpath("//input[@name='delivery.phone']");

    private By creditCardNumberIframe = By.xpath("//iframe[@title='Secure card number input frame']");
    private By cardNumberField = By.xpath("//input[@name='cardnumber']");

    private By nameOnCardField = By.xpath("//input[@placeholder='Name on card']");

    private By expiryDateNumberIframe = By.xpath("//iframe[@title='Secure expiration date input frame']");
    private By expiryDateField = By.xpath("//input[@name='exp-date']");

    private By cvvNumberIframe = By.xpath("//iframe[@title='Secure CVC input frame']");
    private By cvvField = By.xpath("//input[@name='cvc']");

    private By shippingMethod = By.xpath("//label//p[contains(text(), 'Standard Delivery (4-8 business days)')]");
    private By invalidCardMessage = By.xpath("//div/span[contains(text(), 'Your card number is invalid.')]");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForCheckoutPageToBeLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Checkout - Shapermint"));
    }

    public void enterShippingDetails(String email, String firstName, String lastName, String address, String aptSuite, String city, String state, String postalCode, String phone) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(aptSuiteField).sendKeys(aptSuite);
        driver.findElement(cityField).sendKeys(city);

        //Select State from the dropdown
        driver.findElement(stateField).click();
        WebElement stateToBeSelected = driver.findElement(By.xpath("//li[contains(text(), '" + state + "')]"));
        stateToBeSelected.click();

        driver.findElement(postalCodeField).sendKeys(postalCode);
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void enterCardDetails(String number, String name, String expiry, String cvv) {
        //Switch to Credit Card iframe
        driver.switchTo().frame(driver.findElement(creditCardNumberIframe));
        driver.findElement(cardNumberField).sendKeys(number);

        //Switch to default content
        driver.switchTo().defaultContent();

        driver.findElement(nameOnCardField).sendKeys(name);

        //Switch to Expiry Date iframe
        driver.switchTo().frame(driver.findElement(expiryDateNumberIframe));
        driver.findElement(expiryDateField).sendKeys(expiry);

        //Switch to default content
        driver.switchTo().defaultContent();

        //Switch to CVV iframe
        driver.switchTo().frame(driver.findElement(cvvNumberIframe));
        driver.findElement(cvvField).sendKeys(cvv);

        //Switch to default content
        driver.switchTo().defaultContent();
    }

    public boolean isShippingMethodDisplayed() {
        return driver.findElement(shippingMethod).isDisplayed();
    }

    public boolean isInvalidCardMessageDisplayed() {
        return driver.findElement(invalidCardMessage).isDisplayed();
    }
}
