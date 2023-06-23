package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDate;
import java.util.Objects;

public class FlightsPage {
    private WebDriver driver;
    private final By roundTrip = By.cssSelector("button[class=\"sbox5-3-btn-ghost -lighten rt-sbox5 sbox5-button -white -active -md\"]");
    private final By departureLocation = By.cssSelector("input[placeholder=\"Ingresa desde dónde viajas\"]");
    private final By arrivalLocation = By.cssSelector("input[placeholder=\"Ingresa hacia dónde viajas\"]");
    private final By departureDateBox = By.cssSelector("input[placeholder=\"Ida\"]");
    //private final By departureDate = By.cssSelector()
    //div.sbox5-floating-tooltip.sbox5-floating-tooltip-opened


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
        driver.findElement(departureLocation).sendKeys(Keys.ENTER);
    }

    public void enterArrivalLocation(String arrivalLocationName){
        driver.findElement(arrivalLocation).sendKeys(Keys.CONTROL+"a");
        driver.findElement(arrivalLocation).sendKeys(Keys.BACK_SPACE);
        driver.findElement(arrivalLocation).sendKeys(arrivalLocationName);
        driver.findElement(departureLocation).sendKeys(Keys.ENTER);
    }

    /**
     * The time-lapse admitted by this method is 1 year, including the actual date until the next year in the same day
     *
     * @param day
     * @param month
     * @param year
     */
    public void selectDepartureDate(int day, int month, int year){
        driver.findElement(departureDateBox).click();
        if(!yearCheck(year)){
            while(!yearCheck(year)){
                driver.findElement(By.id("ico-arrow-right")).click();
            }
        }
        if(!monthCheck(month)){
            while(!monthCheck(month)){
                driver.findElement(By.cssSelector(" div > a.calendar-arrow-right")).click();
            }
        }
        driver.findElement(By.cssSelector("div.sbox5-monthgrid-dates.sbox5-monthgrid-dates-30 > div:nth-child("+(day+1)+") > div")).click();
    }


   // public void selectArrivalDate(int day, int month, int year){
   //     driver.findElement(departureDateBox).click();
   //     String monthName = (driver.findElement(By.cssSelector("div.sbox5-monthgrid-title > div:nth-child(1)")).getAttribute("innerText"));
   //     int monthNumber = getMonthNumber(monthName);
   //     System.out.println("Mes: "+monthNumber);
   //     if (month != monthNumber){
   //         if(month < monthNumber){
   //             driver.findElement(By.id("ico-arrow-left")).click();
   //         }
   //     }
//
   // }

    private boolean monthCheck(int month){
        String monthName = (driver.findElement(By.cssSelector("div.sbox5-monthgrid-title > div:nth-child(1)")).getAttribute("innerText"));
        int monthNumber = getMonthNumber(monthName);
        boolean check = false;
        if (monthNumber == month){
            check = true;
        }
        return check;
    }

    private boolean yearCheck(int year){
        int yr = Integer.parseInt((driver.findElement(By.cssSelector(" div.sbox5-monthgrid-title > div.sbox5-monthgrid-title-year")).getAttribute("innerText")));
        boolean check = false;
        if (yr == year){
            check = true;
        }
        return check;
    }

    private int getMonthNumber(String month){
        int monthNumber;
        switch(month){
            case "Enero":
                monthNumber = 1;
                break;
            case "Febrero":
            monthNumber = 2;
                break;
            case "Marzo":
                monthNumber = 3;
                break;
            case "Abril":
                monthNumber = 4;
                break;
            case "Mayo":
                monthNumber = 5;
                break;
            case "Junio":
                monthNumber = 6;
                break;
            case "Julio":
                monthNumber = 7;
                break;
            case "Agosto":
                monthNumber = 8;
                break;
            case "Septiembre":
                monthNumber = 9;
                break;
            case "Octubre":
                monthNumber = 10;
                break;
            case "Noviembre":
                monthNumber = 11;
                break;
            case "Diciembre":
                monthNumber = 12;
                break;
            default:
                throw new IllegalStateException("Unexciting Month: " + month);
        }
        return monthNumber;
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        HomePage homePage = new HomePage(driver);
    }
}
