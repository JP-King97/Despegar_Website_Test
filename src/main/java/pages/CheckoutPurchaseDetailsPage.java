package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPurchaseDetailsPage {
    WebDriver driver;
    private final By headerText = By.cssSelector("span[class=\"eva-3-h3 cash-title\"]");

    public CheckoutPurchaseDetailsPage(WebDriver driver){
        this.driver = driver;
    }
    public boolean pageURLCheck(){
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("https://www.despegar.com.co/checkout/purchase");
    }

    public boolean HeaderTextCheck(){
        String text = driver.findElement(headerText).getAttribute("innerText");
        String desireText = "¡Genial! Ahora solo te falta realizar el pago.";
        return text.equals(desireText);
    }
}
