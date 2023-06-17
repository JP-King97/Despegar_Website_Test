package purchase;

import base.BaseTest;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {
    @Test
    public void testSuccessfulPurchase(){
       // homePage.clickFlightsFrame();
       // homePage.selectDepartureLocation();
       // homePage.selectDestinationLocation();

        homePage.getToFlightsPage();
    }
}
