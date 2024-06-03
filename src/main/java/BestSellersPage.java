import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BestSellersPage {
    private WebDriver driver;

    public BestSellersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectFirstProduct() {
        // Find products by their 'data-testid' attribute
        List<WebElement> elements = driver.findElements(By.xpath("//div[@data-testid='product-card-container-div']"));

        // Check if the elements list is not empty and select the first one
        if (!elements.isEmpty()) {
            WebElement firstElement = elements.get(0);
            // Click on the first element
            firstElement.click();
        }
    }
}
