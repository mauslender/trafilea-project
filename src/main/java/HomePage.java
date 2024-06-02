package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By bestSellersSection = By.cssSelector("a[href*='/collections/our-best-sellers']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnBestSellers() {
        driver.findElement(bestSellersSection).click();
    }
}
