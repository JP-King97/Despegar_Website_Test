package cucumber.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.*;

import java.time.Duration;

public class Steps {
    public WebDriver driver;
    protected HomePage homePage;
    private FlightsHomePage flightsPg;
    private FlightsResultPage resultPage;
    private CheckoutPage checkoutPg;
    private CheckoutPurchaseDetailsPage purchaseDetailsPg;
    private final int[] depDate={4,4,2024};
    private final int[] arrDate={5,5,2024};
    private final String desireDepartureCity = "Medellín";
    private final String desireArrivalCity = "Cali";
    private String[] passenger = {
            "Daniel",
            "Moreno",
            "masculino",
            "Colombia",
            "Antioquia",
            "Envigado",
            "Cédula de ciudadanía",
            "1028658686",
            "despegar_Selenium_test1_Java@gmail.com",
            "Celular",
            "3196997242",
            "Cra 34 #28-31",
            "3","6","1987"};

    private String[] paymentInformation = {
            "PSE",
            "Bancolombia",
            "natural"
    };

    //public void setUp(){
    //}

    public void waitPageLoad(String url){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlMatches(url));
    }


    @Given("The flights page on Despegar website is set")
    public void testFlightsPageVerification(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
      // chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--headless=new");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get("https://www.despegar.com.co"); //here goes the website URL
        homePage = new HomePage(driver);
        flightsPg = homePage.selectFlights();
        waitPageLoad("https://www.despegar.com.co/vuelos/");
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"https://www.despegar.com.co/vuelos/","The Flights page did not charge correctly");
    }

    @When("the dates and locations are set correctly and submit")
    public void testFlightDatesAndLocations(){
        //flightsPg.closeLoginPopUp();
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
        Assert.assertTrue((flightsPg.getSelectedDepartureLocation()).contains(desireDepartureCity),"Departure location is different");
        flightsPg.enterArrivalLocation(desireArrivalCity);
        Assert.assertTrue((flightsPg.getSelectedArrivalLocation()).contains(desireArrivalCity),"Arrival location is different");
        //Summit the flights dates and locations
        resultPage = flightsPg.summitSearchInformation();
        Assert.assertTrue(resultPage.urlCheck());
    }

    @Then("the passenger is able to select the first flight option from the results page")
    public void testSelectFirstResult(){
        //resultPage.closePopUpDiscount();
        //Select the first result
        checkoutPg = resultPage.clickFirstBuyButton();//Despues de esto puede aparecer un modal del equipaje
        Assert.assertTrue(checkoutPg.urlCheck());
    }

    @Given("the flight is selected to be purchase already")
    public void the_flight_is_selected_to_be_purchase_already(){
        testFlightsPageVerification();
        testFlightDatesAndLocations();
        testSelectFirstResult();
    }

    @When("the passenger and payment information is set and submit")
    public void testSetPassengerInformation(){
        checkoutPg.enterFirstName(passenger[0]);
        checkoutPg.enterLastName(passenger[1]);
        checkoutPg.selectCountry(passenger[3]);
        checkoutPg.enterNumberID(passenger[6], passenger[7]);
        checkoutPg.selectDateOfBirth(passenger[12],passenger[13],passenger[14]);
        checkoutPg.selectSex(passenger[2]);
        checkoutPg.enterEmail(passenger[8]);
        checkoutPg.enterEmailConfirmation(passenger[8]);
        checkoutPg.selectNumberType(passenger[9]);
        checkoutPg.selectPhoneCountry(passenger[3]);
        checkoutPg.enterPhoneNumber(passenger[10]);
        checkoutPg.selectPaymentType(paymentInformation[0]);
        checkoutPg.selectBank(paymentInformation[1]);
        checkoutPg.selectFiscalStatus(paymentInformation[2]);
        checkoutPg.enterFirstNameOnBill(passenger[0]);
        checkoutPg.enterLastNameOnBill(passenger[1]);
        checkoutPg.selectDocumentTypeOnBill(passenger[6]);
        checkoutPg.enterDocumentNumberOnBill(passenger[7]);
        checkoutPg.enterDocumentStateOnBill(passenger[4]);
        checkoutPg.enterPassengerCityOnBill(passenger[5]+", "+passenger[4]+", "+passenger[3]);
        checkoutPg.enterPassengerAddressOnBill(passenger[11]);
        checkoutPg.checkTermsAndConditions();
        purchaseDetailsPg = checkoutPg.pressNoAssistanceButton();
        ;

    }

    @Then ("the passenger should be able to review the information provided before")
    public void testPurchaseDetailsVerification() {
        Assert.assertTrue(purchaseDetailsPg.pageURLCheck());
        //Assert.assertTrue(purchaseDetailsPg.headerTextCheck());
       // Assert.assertTrue(purchaseDetailsPg.bankEntityCheck(paymentInformation[1]));
       // Assert.assertTrue(purchaseDetailsPg.paymentMethodCheck(paymentInformation[0]));
       // Assert.assertTrue(purchaseDetailsPg.emailCheck(passenger[8]));
       // Assert.assertTrue(purchaseDetailsPg.locationsCheck(desireDepartureCity,desireArrivalCity));
       // Assert.assertTrue(purchaseDetailsPg.flightTypeCheck());
       // Assert.assertTrue(purchaseDetailsPg.departureDateCheck(depDate));
       // Assert.assertTrue(purchaseDetailsPg.arrivalDateCheck(arrDate));

    }

    // @AfterTest
    // public void tearDown(){
    //     driver.quit();
    // }



}
