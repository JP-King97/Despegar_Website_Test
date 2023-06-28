package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class FlightsResultPage {
    WebDriver driver ;
    private final By firstBuyButton = By.cssSelector("a[class=\"-md eva-3-btn -primary\"]");
    private final By popUpCloseButton = By.cssSelector("span[class=\"login-aggressive--button login-aggressive--button-close shifu-3-btn-ghost\"]");
    private final WebDriverWait wait;
    public FlightsResultPage(WebDriver driver){
        Set<String> allTabs = driver.getWindowHandles();
        Iterator<String> iterate = allTabs.iterator();
        String flightsResultPage = null;
        while (iterate.hasNext()){
            flightsResultPage = iterate.next();
        }
        this.driver=driver.switchTo().window(flightsResultPage);
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));

    }


    public void waitToBeClickable(By byElement){
        wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }

    public void closePopUpDiscount(){
        waitToBeClickable(popUpCloseButton);
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
        js.executeScript("window.scrollBy(0,500)","");
    }

    public boolean urlCheck(){
        waitToBeClickable(firstBuyButton);
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("https://www.despegar.com.co/shop/flights/results");
    }

}
