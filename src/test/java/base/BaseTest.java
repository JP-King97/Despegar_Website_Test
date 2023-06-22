package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    protected HomePage homePage;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
       // chromeOptions.addArguments("--disable-features=AutomationControlled");
       // chromeOptions.addExtensions(new File("C:\\Users\\User\\IdeaProjects\\Travelocity_Website_Test\\uBlock-Origin.crx"));
       // chromeOptions.setAcceptInsecureCerts(true);


       // chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.despegar.com.co"); //here goes the website URL
        homePage = new HomePage(driver);
    }

   // @AfterTest
   // public void tearDown(){
   //     driver.quit();
   // }
}
