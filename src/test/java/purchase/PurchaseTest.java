package purchase;

import base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.FlightsPage;

import java.time.Duration;

public class PurchaseTest extends BaseTest {
    @Test
    public void testSuccessfulPurchase(){
       // homePage.clickFlightsFrame();
       // homePage.selectDepartureLocation();
       // homePage.selectDestinationLocation();

        FlightsPage flightsPg = homePage.selectFlights();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flightsPg.selectRoundTrips();
        flightsPg.enterDepartureLocation("Medellín, Antioquia, Colombia");
    }
}
