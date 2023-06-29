package pages;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.IOException;
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
        try {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs((OutputType.FILE));
            Files.move(screenshot,new File("Screenshots\\popUp2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        waitToBeClickable(popUpCloseButton);
        driver.findElement(popUpCloseButton).click();
    }

    public CheckoutPage clickFirstBuyButton(){
        waitToBeClickable(firstBuyButton);
        try {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs((OutputType.FILE));
            Files.move(screenshot,new File("Screenshots\\FirstBuyButton.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        waitToBeClickable(firstBuyButton);
        scrollDown(300);
        driver.findElement(firstBuyButton).click();
        return new CheckoutPage(driver);
    }

    private void scrollDown(int verticalScroll){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+verticalScroll+")","");
    }

    public boolean urlCheck(){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("https://www.despegar.com.co/shop/flights/results"));
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("https://www.despegar.com.co/shop/flights/results");
    }

}
