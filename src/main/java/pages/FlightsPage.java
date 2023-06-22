package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FlightsPage {
    private WebDriver driver;
    private final By roundTrip = By.cssSelector("button[class=\"sbox5-3-btn-ghost -lighten rt-sbox5 sbox5-button -white -active -md\"]");
    private final By departureLocation = By.cssSelector("input[placeholder=\"Ingresa desde dónde viajas\"]");
    private final By firstCityOutcome = By.cssSelector("//div/div/ul/li/span");

    public FlightsPage(WebDriver driver) {
        this.driver=driver;
    }

    private void getTypeOfFlights(By typeOfFlight){
        driver.findElement(typeOfFlight);
    }

    public void selectRoundTrips(){
        getTypeOfFlights(roundTrip);
    }

    public void enterDepartureLocation(String departureLocationName){
        driver.findElement(departureLocation).sendKeys(Keys.CONTROL+"a");
        driver.findElement(departureLocation).sendKeys(Keys.BACK_SPACE);
        driver.findElement(departureLocation).sendKeys(departureLocationName);
    }






  //  public void enterArrivalLocation(String arrivalLocationName){
  //      driver.findElemen
  //  }
}
