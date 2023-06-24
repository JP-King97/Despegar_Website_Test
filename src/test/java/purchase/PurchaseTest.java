package purchase;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.CheckoutPage;
import pages.FlightsHomePage;
import pages.FlightsResultPage;

import static org.testng.Assert.assertEquals;
public class PurchaseTest extends BaseTest {
    private FlightsHomePage flightsPg;
    private FlightsResultPage resultPage;
    private CheckoutPage checkoutPg;

    @Test(priority = 1)
    public void testFlightsPageVerification(){
        flightsPg = homePage.selectFlights();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String url = driver.getCurrentUrl();
        assertEquals(url,"https://www.despegar.com.co/vuelos/","The Flights page did not charge correctly");
    }

    @Test(priority = 2 , dependsOnMethods = "testFlightsPageVerification")
    public void testFlightDatesAndLocations(){
        flightsPg.closeLoginPopUp();
        flightsPg.selectRoundTrips();
        //Enter the flight dates and locations
        flightsPg.selectDepartureDate(4,10,2023);
        System.out.println("Date:"+flightsPg.getDepartureDate());
        flightsPg.selectArrivalDate(2,11,2023);
        flightsPg.enterDepartureLocation("Medellín, Antioquia, Colombia");
        flightsPg.enterArrivalLocation("Cali, Valle del Cauca, Colombia");
        //Summit the flights dates and locations
        resultPage = flightsPg.summitSearchInformation();
    }

    @Test(priority = 3, dependsOnMethods = "testFlightDatesAndLocations")
    public void testSelectFirstResult(){
        resultPage.closePopUpDiscount();
        //Select the first result
        checkoutPg = resultPage.clickFirstBuyButton();//Despues de esto puede aparecer un modal del equipaje
    }

    @Test(priority = 4, dependsOnMethods = "testSelectFirstResult")
    public void testSetPassengerInformation(){
        checkoutPg.enterFirstName("Juan Alberto");
        checkoutPg.enterLastName("Gonzalez Garcia");
        checkoutPg.selectCountry("Colombia");
        checkoutPg.enterNumberID("Cédula",1027342586);
        checkoutPg.selectDateOfBirth(3,6,1987);
        checkoutPg.selectSex("m");
        checkoutPg.enterEmail("despegar_Selenium_test@gmail.com");
        checkoutPg.enterEmailConfirmation("despegar_Selenium_test@gmail.com");
        checkoutPg.selectNumberType("Celular");
        checkoutPg.selectPhoneCountry("Colombia");
        checkoutPg.enterPhoneNumber("3196862442");
        checkoutPg.selectPaymentType("PSE");
        checkoutPg.selectBank("Bancolombia");
        checkoutPg.selectFiscalStatus("natural");
        checkoutPg.enterFirstNameOnBill("Juan Alberto");
        checkoutPg.enterLastNameOnBill("Gonzalez Garcia");
        checkoutPg.selectDocumentTypeOnBill("Cédula de ciudadanía");
        checkoutPg.enterDocumentNumberOnBill("1027342586");
        checkoutPg.enterDocumentStateOnBill("Antioquia");
        checkoutPg.enterPassengerCityOnBill("Envigado, Antioquia, Colombia");
        checkoutPg.enterPassengerAddressOnBill("Cra 34 #28-31");
        checkoutPg.checkTermsAndConditions();
        checkoutPg.pressNoAssistanceButton();
    }
}
