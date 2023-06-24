package pages;

import org.openqa.selenium.WebDriver;

public class CheckoutPurchaseDetailsPage {
    WebDriver driver;

    public CheckoutPurchaseDetailsPage(WebDriver driver){
        this.driver = driver;
    }
    public boolean pageURLCheck(){
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("https://www.despegar.com.co/checkout/purchase");
    }
}
