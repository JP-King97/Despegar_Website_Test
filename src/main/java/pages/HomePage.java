package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    private final By flights = By.cssSelector("a[href=\"/vuelos/\"]");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private void selectTypeOfServices(By service){
        driver.findElement(service).click();
    }

    public FlightsPage selectFlights(){
        selectTypeOfServices(flights);
        return new FlightsPage(driver);
    }

    ////////////flights frame














   // public void clickFlightsFrame(){
   //     driver.findElement(flightsFrame).click();
   // }
   //
   // public void selectTripType(){
   //     driver.findElement(roundTripButton).click();
   // }
   //
   // public void selectDepartureLocation(){
   //     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
   //     driver.findElement(leavingFromBox).sendKeys(Keys.ENTER);
   //     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
   //     driver.findElement(enterDepartureCity).sendKeys("medellin"+Keys.ENTER);
   // }
   //
   // public void selectDestinationLocation(){
   //     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
   //     driver.findElement(goingToBox).sendKeys(Keys.ENTER);
   //     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
   //     driver.findElement(enterDestinationCity).sendKeys("cali"+Keys.ENTER);
   // }
}
