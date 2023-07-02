package services.flights;


import com.google.common.annotations.VisibleForTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

@VisibleForTesting
public class CB_PurchaseFlightTestRunner {


    @Test
    public void runOnChromeTest(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOpt = new ChromeOptions();
        chromeOpt.addArguments("--no-sandbox");
        chromeOpt.addArguments("--disable-dev-shm-usage");
        chromeOpt.addArguments("--window-size=1920,1080");
        chromeOpt.addArguments("--headless");
        WebDriver chromeDriver = new ChromeDriver(chromeOpt);
        PurchaseFlightTest purchaseFlightTest = new PurchaseFlightTest(chromeDriver);

        purchaseFlightTest.testHomePageVerification();
        purchaseFlightTest.testFlightsPageVerification();
        purchaseFlightTest.testSetFlightDatesAndLocations();
        purchaseFlightTest.testSelectFirstFlightResult();
        purchaseFlightTest.testSetPurchaseInformation();
        purchaseFlightTest.testPurchaseDetailsVerification();

        tearDown(chromeDriver);
    }

    @Test
    public void runOnFirefoxTest(){
        //"firefox","Firefox":
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOpt = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        firefoxOpt.addArguments("--no-sandbox");
        firefoxOpt.addArguments("--disable-dev-shm-usage");
        firefoxOpt.addArguments("--window-size=1920,1080");
        firefoxOpt.setProfile(profile);
        firefoxOpt.addArguments("--headless");
        WebDriver firefoxDriver = new FirefoxDriver(firefoxOpt);
        PurchaseFlightTest purchaseFlightTest = new PurchaseFlightTest(firefoxDriver);

        purchaseFlightTest.testHomePageVerification();
        purchaseFlightTest.testFlightsPageVerification();
        purchaseFlightTest.testSetFlightDatesAndLocations();
        purchaseFlightTest.testSelectFirstFlightResult();
        purchaseFlightTest.testSetPurchaseInformation();
        purchaseFlightTest.testPurchaseDetailsVerification();

        tearDown(firefoxDriver);
    }

   // @Test
   // public void runOnEdgeTest(){
   //     //"edge","Edge":
   //     WebDriverManager.edgedriver().setup();
   //     EdgeOptions edgeOpt = new EdgeOptions();
   //     edgeOpt.addArguments("--no-sandbox");
   //     edgeOpt.addArguments("--disable-dev-shm-usage");
   //     edgeOpt.addArguments("--window-size=1920,1080");
   //     edgeOpt.addArguments("--headless");
   //     WebDriver edgeDriver = new EdgeDriver(edgeOpt);
   //     PurchaseFlightTest purchaseFlightTest = new PurchaseFlightTest(edgeDriver);
//
   //     purchaseFlightTest.testHomePageVerification();
   //     purchaseFlightTest.testFlightsPageVerification();
   //     purchaseFlightTest.testSetFlightDatesAndLocations();
   //     purchaseFlightTest.testSelectFirstFlightResult();
   //     purchaseFlightTest.testSetPurchaseInformation();
   //     purchaseFlightTest.testPurchaseDetailsVerification();
//
   //     tearDown(edgeDriver);
   // }

    private void tearDown(WebDriver driver){
        driver.quit();
    }

}

