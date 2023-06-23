package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class FlightsResultPage {
    WebDriver driver ;
    private final By firstBuyButton = By.cssSelector(" div > buy-button > a[class=\"-md eva-3-btn -primary\"]");
    private final By popUpCloseButton = By.cssSelector("span[class=\"login-aggressive--button login-aggressive--button-close shifu-3-btn-ghost\"]");
    public FlightsResultPage(WebDriver driver){
        Set<String> allTabs = driver.getWindowHandles();
        Iterator<String> iterate = allTabs.iterator();
        String flightsResultPage = null;
        while (iterate.hasNext()){
            flightsResultPage = iterate.next();
        }
        this.driver=driver.switchTo().window(flightsResultPage);

    }

    public void closePopUpDiscount(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(popUpCloseButton).click();
    }

    public CheckoutPage clickFirstBuyButton(){
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scrollDown();
        driver.findElement(firstBuyButton).click();
        return new CheckoutPage(driver);
    }

    private void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)","");
    }

}
