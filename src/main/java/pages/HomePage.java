package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;
    private final By flightsFrame = By.linkText("Flights");
    private final By roundTripButton = By.linkText("#FlightSearchForm_ROUND_TRIP");
    private final By leavingFromBox = By.cssSelector("button[aria-label=\"Leaving from\"]");
    private final By goingToBox = By.cssSelector("button[aria-label=\"Going to\"]");
    private final By enterDepartureCity = By.cssSelector("input[placeholder=\"Leaving from\"]");
    private final By enterDestinationCity = By.cssSelector("input[placeholder=\"Going to\"]");

    private final By typeOfService = By.cssSelector("button[class=\"uitk-button uitk-button-medium uitk-button-has-text uitk-button-tertiary uitk-menu-trigger global-navigation-nav-button\"]");
    private final By typeOfService_Flights = By.cssSelector("a[href=\"/Flights\"]");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement selectTypeOfServices(By service){
        driver.findElement(typeOfService).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver.findElement(service);
    }

    public void getToFlightsPage(){
        selectTypeOfServices(typeOfService_Flights).click();
        new FlightsPage(driver);
    }












    public void clickFlightsFrame(){
        driver.findElement(flightsFrame).click();
    }

    public void selectTripType(){
        driver.findElement(roundTripButton).click();
    }

    public void selectDepartureLocation(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(leavingFromBox).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(enterDepartureCity).sendKeys("medellin"+Keys.ENTER);
    }

    public void selectDestinationLocation(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(goingToBox).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(enterDestinationCity).sendKeys("cali"+Keys.ENTER);
    }
}
