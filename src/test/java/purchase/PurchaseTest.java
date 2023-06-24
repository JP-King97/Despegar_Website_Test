package purchase;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.FlightsHomePage;
import pages.FlightsResultPage;

import static org.testng.Assert.*;
public class PurchaseTest extends BaseTest {
    private FlightsHomePage flightsPg;
    private FlightsResultPage resultPage;
    private CheckoutPage checkoutPg;
    private final int[] depDate={8,11,2023};
    private final int[] arrDate={10,3,2024};
    private final String desireDepartureCity = "Medellín, Antioquia, Colombia";
    private final String desireArrivalCity = "Cali, Valle del Cauca, Colombia";
    String[] passenger = {
            "Juan Alberto",
            "Gonzalez Garcia",
            "masculino",
            "Colombia",
            "Antioquia",
            "Envigado",
            "Cédula de ciudadanía",
            "1027342586",
            "despegar_Selenium_test@gmail.com",
            "Celular",
            "3196862442",
            "Cra 34 #28-31"};

    @Test(priority = 1)
    public void testFlightsPageVerification(){
        flightsPg = homePage.selectFlights();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"https://www.despegar.com.co/vuelos/","The Flights page did not charge correctly");
    }

    @Test(priority = 2 , dependsOnMethods = "testFlightsPageVerification")
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
    }

    @Test(priority = 3, dependsOnMethods = "testFlightDatesAndLocations")
    public void testSelectFirstResult(){
        resultPage.closePopUpDiscount();
        //Select the first result
        checkoutPg = resultPage.clickFirstBuyButton();//Despues de esto puede aparecer un modal del equipaje
    }

    @Test(priority = 4, dependsOnMethods = "testSelectFirstResult")
    public void testSetPassengerInformation(){
        checkoutPg.enterFirstName(passenger[0]);
        checkoutPg.enterLastName(passenger[1]);
        checkoutPg.selectCountry(passenger[3]);
        checkoutPg.enterNumberID(passenger[6], passenger[7]);
        checkoutPg.selectDateOfBirth(3,6,1987);
        checkoutPg.selectSex(passenger[2]);
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
        checkoutPg.pressNoAssistanceButton();
    }
}
