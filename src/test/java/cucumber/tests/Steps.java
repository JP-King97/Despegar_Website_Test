package cucumber.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import pages.*;

public class Steps {
    public WebDriver driver;
    protected HomePage homePage;
    private FlightsHomePage flightsPg;
    private FlightsResultPage resultPage;
    private CheckoutPage checkoutPg;
    private CheckoutPurchaseDetailsPage purchaseDetailsPg;
    private final int[] depDate={1,1,2024};
    private final int[] arrDate={1,2,2024};
    private final String desireDepartureCity = "Medellín, Antioquia, Colombia";
    private final String desireArrivalCity = "Cali, Valle del Cauca, Colombia";
    private String[] passenger = {
            "Dave",
            "Chapelle",
            "masculino",
            "Colombia",
            "Antioquia",
            "Envigado",
            "Cédula de ciudadanía",
            "1027667586",
            "despegar_Selenium_test2_Java@gmail.com",
            "Celular",
            "3196865242",
            "Cra 34 #28-31"};

    //public void setUp(){
    //}

    @Given("The flights page on Despegar website is set")
    public void testFlightsPageVerification(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.despegar.com.co"); //here goes the website URL
        homePage = new HomePage(driver);
        flightsPg = homePage.selectFlights();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"https://www.despegar.com.co/vuelos/","The Flights page did not charge correctly");
    }

    @When("the dates and locations are set correctly and summit")
    public void testFlightDatesAndLocations(){
        flightsPg.closeLoginPopUp();
        flightsPg.selectRoundTrips();
        //Enter the flight dates and locations
        flightsPg.selectDepartureDate(depDate[0],depDate[1],depDate[2]);
        String selectedDepDate = flightsPg.getDepartureDate();
        String desiredDepDate = flightsPg.getDesiredDepDate(depDate[0],depDate[1],depDate[2]);
        Assert.assertTrue(selectedDepDate.contains(desiredDepDate),"Departure date is different");
        flightsPg.selectArrivalDate(arrDate[0],arrDate[1],arrDate[2]);
        String selectedArrDate = flightsPg.getArrivalDate();
        String desiredArrDat = flightsPg.getDesiredArrDate(arrDate[0],arrDate[1],arrDate[2]);
        Assert.assertTrue(selectedArrDate.contains(desiredArrDat),"Arrival date is different");
        flightsPg.enterDepartureLocation(desireDepartureCity);
        Assert.assertEquals(desireDepartureCity,flightsPg.getSelectedDepartureLocation(),"Departure location is different");
        flightsPg.enterArrivalLocation(desireArrivalCity);
        Assert.assertEquals(desireArrivalCity,flightsPg.getSelectedArrivalLocation(),"Arrival location is different");
        //Summit the flights dates and locations
        resultPage = flightsPg.summitSearchInformation();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(resultPage.urlCheck());
    }

    @Then("the passenger is able to select the first flight option from the results page")
    public void testSelectFirstResult(){
        resultPage.closePopUpDiscount();
        //Select the first result
        checkoutPg = resultPage.clickFirstBuyButton();//Despues de esto puede aparecer un modal del equipaje
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(checkoutPg.urlCheck());
    }

    public void testSetPassengerInformation(){
        checkoutPg.enterFirstName(passenger[0]);
        checkoutPg.enterLastName(passenger[1]);
        checkoutPg.selectCountry(passenger[3]);
        checkoutPg.enterNumberID(passenger[6], passenger[7]);
        //checkoutPg.selectDateOfBirth(3,6,1987);
        //checkoutPg.selectSex(passenger[2]);
        checkoutPg.enterEmail(passenger[8]);
        checkoutPg.enterEmailConfirmation(passenger[8]);
        checkoutPg.selectNumberType(passenger[9]);
        checkoutPg.selectPhoneCountry(passenger[3]);
        checkoutPg.enterPhoneNumber(passenger[10]);
        checkoutPg.selectPaymentType("PSE");
        checkoutPg.selectBank("Bancolombia");
        checkoutPg.selectFiscalStatus("natural");
        checkoutPg.enterFirstNameOnBill(passenger[0]);
        checkoutPg.enterLastNameOnBill(passenger[1]);
        checkoutPg.selectDocumentTypeOnBill(passenger[6]);
        checkoutPg.enterDocumentNumberOnBill(passenger[7]);
        checkoutPg.enterDocumentStateOnBill(passenger[4]);
        checkoutPg.enterPassengerCityOnBill(passenger[5]+", "+passenger[4]+", "+passenger[3]);
        checkoutPg.enterPassengerAddressOnBill(passenger[11]);
        checkoutPg.checkTermsAndConditions();
        purchaseDetailsPg = checkoutPg.pressNoAssistanceButton();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(purchaseDetailsPg.pageURLCheck());

    }

    public void testPurchaseDetailsVerification() {
        Assert.assertTrue(purchaseDetailsPg.HeaderTextCheck());
    }

    // @AfterTest
    // public void tearDown(){
    //     driver.quit();
    // }



}
