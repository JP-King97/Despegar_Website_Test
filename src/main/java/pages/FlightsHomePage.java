package pages;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class FlightsHomePage {
    private WebDriver driver;
    private final By roundTrip = By.cssSelector("button[class=\"sbox5-3-btn-ghost -lighten rt-sbox5 sbox5-button -white -active -md\"]");
    private final By departureLocation = By.cssSelector("input[placeholder=\"Ingresa desde dónde viajas\"]");
    private final By arrivalLocation = By.cssSelector("input[placeholder=\"Ingresa hacia dónde viajas\"]");
    private final By departureDateBox = By.cssSelector("input[placeholder=\"Ida\"]");
    private final By arrivalDateBox = By.cssSelector("input[placeholder=\"Vuelta\"]");
    private final By searchButton = By.cssSelector("div.sbox5-button-container--1X4O8 > button");
    private final WebDriverWait wait;

    public FlightsHomePage(WebDriver driver) {
        this.driver=driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    public void waitToBeClickable(By byElement){
        wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }

    private void getTypeOfFlights(By typeOfFlight){
        waitToBeClickable(typeOfFlight);
        driver.findElement(typeOfFlight);
    }

    public void selectRoundTrips(){
        getTypeOfFlights(roundTrip);
    }

    public void closeLoginPopUp(){
        try {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            Files.move(screenshot,new File("C:\\Users\\User\\IdeaProjects\\Despegar_Website_Test\\Screenshots\\popUp1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        waitToBeClickable(By.cssSelector(" div.login-incentive--content > span"));
        driver.findElement(By.cssSelector(" div.login-incentive--content > span")).click();
    }
    public FlightsResultPage summitSearchInformation(){
        waitToBeClickable(searchButton);
        driver.findElement(searchButton).click();
        return new  FlightsResultPage(driver);
    }

    public void enterDepartureLocation(String departureLocationName){
        waitToBeClickable(departureLocation);
        driver.findElement(departureLocation).click();
        driver.findElement(departureLocation).sendKeys(Keys.CONTROL+"a");
        driver.findElement(departureLocation).sendKeys(Keys.BACK_SPACE);
        driver.findElement(departureLocation).sendKeys(departureLocationName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(departureLocation).sendKeys(Keys.ENTER);
    }

    public String getSelectedDepartureLocation(){
        waitToBeClickable(departureLocation);
        return driver.findElement(departureLocation).getAttribute("value");
    }

    public void enterArrivalLocation(String arrivalLocationName){
        waitToBeClickable(arrivalLocation);
        driver.findElement(arrivalLocation).click();
        driver.findElement(arrivalLocation).sendKeys(Keys.CONTROL+"a");
        driver.findElement(arrivalLocation).sendKeys(Keys.BACK_SPACE);
        driver.findElement(arrivalLocation).sendKeys(arrivalLocationName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(arrivalLocation).sendKeys(Keys.ENTER);
    }

    public String getSelectedArrivalLocation(){
        waitToBeClickable(arrivalLocation);
        return driver.findElement(arrivalLocation).getAttribute("value");
    }

    /**
     * The time-lapse admitted by this method is 1 year, including the actual date until the next year in the same day
     *
     * @param day
     * @param month
     * @param year
     */
    public void selectDepartureDate(int day, int month, int year){
        waitToBeClickable(departureDateBox);
        driver.findElement(departureDateBox).click();
        if(!yearCheck(year)){
            while(!yearCheck(year)){
                driver.findElement(By.cssSelector(" a.calendar-arrow-right > svg")).click();
            }
        }
        if(!monthCheck(month)){
            while(!monthCheck(month)){
                driver.findElement(By.cssSelector(" a.calendar-arrow-right > svg")).click();
            }
        }
        driver.findElement(By.cssSelector("div[class=\"sbox5-monthgrid\"] > div > div:nth-child("+(day+1)+") > div")).click();

    }

    public String getDepartureDate(){
        waitToBeClickable(departureDateBox);
        return driver.findElement(departureDateBox).getAttribute("value");
    }

    public String getDesiredDepDate(int day, int month, int year){
        return (""+day+" "+getMonthInfo(""+month+"")[1]+" "+year);
    }

    public void selectArrivalDate(int day, int month, int year){
        waitToBeClickable(arrivalDateBox);
        driver.findElement(arrivalDateBox).click();
        if(!yearCheck(year)){
            while(!yearCheck(year)){
                driver.findElement(By.cssSelector(" a.calendar-arrow-right > svg")).click();
            }
        }
        if(!monthCheck(month)){
            while(!monthCheck(month)){
                driver.findElement(By.cssSelector(" a.calendar-arrow-right > svg")).click();
            }
        }
        driver.findElement(By.cssSelector("div[class=\"sbox5-monthgrid\"] > div > div:nth-child("+(day+1)+") > div")).click();
    }

    public String getArrivalDate(){
        waitToBeClickable(arrivalDateBox);
        return driver.findElement(arrivalDateBox).getAttribute("value");
    }

    public String getDesiredArrDate(int day, int month, int year){
        return (""+day+" "+getMonthInfo(""+month+"")[1]+" "+year);
    }

    private boolean monthCheck(int month){
        waitToBeClickable(By.cssSelector("div.sbox5-monthgrid-title > div:nth-child(1)"));
        String monthName = (driver.findElement(By.cssSelector("div.sbox5-monthgrid-title > div:nth-child(1)")).getAttribute("innerText"));
        int monthNumber = Integer.parseInt(getMonthInfo(monthName)[0]);
        boolean check = false;
        if (monthNumber == month){
            check = true;
        }
        return check;
    }

    private boolean yearCheck(int year){
        waitToBeClickable(By.cssSelector(" div.sbox5-monthgrid-title > div.sbox5-monthgrid-title-year"));
        int yr = Integer.parseInt((driver.findElement(By.cssSelector(" div.sbox5-monthgrid-title > div.sbox5-monthgrid-title-year")).getAttribute("innerText")));
        boolean check = false;
        if (yr == year){
            check = true;
        }
        return check;
    }


    private String[] getMonthInfo(String month){

        String[] monthInfo = new String[3] ;
        switch(month){
            case "Enero","1":
                monthInfo = new String[]{"1", "Ene", "Enero"};
                break;
            case "Febrero","2":
                monthInfo = new String[]{"2", "Feb", "Febrero"};
                break;
            case "Marzo","3":
                monthInfo = new String[]{"3", "Mar", "Marzo"};
                break;
            case "Abril","4":
                monthInfo = new String[]{"4", "Abr", "Abril"};
                break;
            case "Mayo","5":
                monthInfo = new String[]{"5", "May", "Mayo"};
                break;
            case "Junio","6":
                monthInfo = new String[]{"6", "Jun", "Junio"};
                break;
            case "Julio","7":
                monthInfo = new String[]{"7", "Jul", "Julio"};
                break;
            case "Agosto","8":
                monthInfo = new String[]{"8", "Ago", "Agosto"};
                break;
            case "Septiembre","9":
                monthInfo = new String[]{"9", "Sep", "Septiembre"};
                break;
            case "Octubre","10":
                monthInfo = new String[]{"10", "Oct", "Octubre"};
                break;
            case "Noviembre","11":
                monthInfo = new String[]{"11", "Nov", "Noviembre"};
                break;
            case "Diciembre","12":
                monthInfo = new String[]{"12", "Dic", "Diciembre"};
                break;
            default:
                throw new IllegalStateException("Unexciting Month: " + month);
        }
        return monthInfo;
    }
}
