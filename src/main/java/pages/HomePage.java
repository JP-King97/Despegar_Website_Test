package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    private final By flights = By.cssSelector("a[href=\"/vuelos/\"]");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    private void selectTypeOfServices(By service){
        driver.findElement(service).click();
    }

    public FlightsHomePage selectFlights(){
        selectTypeOfServices(flights);
        return new FlightsHomePage(driver);
    }

}
