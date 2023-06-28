package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    private final By flights = By.cssSelector("a[href=\"/vuelos/\"]");
    private final WebDriverWait wait;
    public HomePage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }
    public void waitToBeClickable(By byElement){
        wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }


    private void selectTypeOfServices(By service){
        waitToBeClickable(service);
        driver.findElement(service).click();
    }

    public FlightsHomePage selectFlights(){
        selectTypeOfServices(flights);
        return new FlightsHomePage(driver);
    }

}
