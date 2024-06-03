import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By bestSellersSection = By.cssSelector("a[href*='/collections/best-sellers']");
    private By adCross = By.xpath("//span[@class='_xxsoyodj_popup-lead-trafilea-close _xxsoyodj_popup-lead-trafilea-close-cross']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to wait for the adCross element and click on it once it is displayed
    public void waitForAndCloseAd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait for the adCross element to be visible
            WebElement adElement = wait.until(ExpectedConditions.visibilityOfElementLocated(adCross));
            // Click on the adCross element to close the ad
            adElement.click();
        } catch (Exception e) {
            System.out.println("Ad element not found or not clickable: " + e.getMessage());
        }
    }

    public void clickOnBestSellers() {
        driver.findElement(bestSellersSection).click();
    }
}
