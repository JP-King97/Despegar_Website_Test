package purchase;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.FlightsHomePage;
import pages.FlightsResultPage;

import java.util.Iterator;
import java.util.Set;

public class PurchaseTest extends BaseTest {
    @Test
    public void testSuccessfulPurchase(){
       // homePage.clickFlightsFrame();
       // homePage.selectDepartureLocation();
       // homePage.selectDestinationLocation();

        FlightsHomePage flightsPg = homePage.selectFlights();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flightsPg.closeLoginPopUp();
        flightsPg.selectRoundTrips();
        flightsPg.selectDepartureDate(4,10,2023);
        flightsPg.selectArrivalDate(2,11,2023);
        flightsPg.enterDepartureLocation("Medellín, Antioquia, Colombia");
        flightsPg.enterArrivalLocation("Cali, Valle del Cauca, Colombia");
        FlightsResultPage resultPage = flightsPg.summitSearchInformation();
        /////////////////////////////
        resultPage.closePopUpDiscount();
        CheckoutPage checkoutPg = resultPage.clickFirstBuyButton();
        ////////////////////////
        checkoutPg.enterFirstName("Juan Alberto");
        checkoutPg.enterLastName("Gonzalez Garcia");

    }
}
