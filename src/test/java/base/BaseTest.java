package base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.time.Duration;
import java.util.Scanner;

public class BaseTest {
    public WebDriver driver;
    protected HomePage homePage;


    public WebDriver webDriverSelector(String browser){
        switch (browser){
            case "chrome","Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOpt = new ChromeOptions();
                chromeOpt.addArguments("--no-sandbox");
                chromeOpt.addArguments("--disable-dev-shm-usage");
                chromeOpt.addArguments("--window-size=1920,1080");
                chromeOpt.addArguments("--headless");
                return driver = new ChromeDriver(chromeOpt);

            case "edge","Edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOpt = new EdgeOptions();
                edgeOpt.addArguments("--no-sandbox");
                edgeOpt.addArguments("--disable-dev-shm-usage");
                edgeOpt.addArguments("--window-size=1600,900");
                edgeOpt.addArguments("--headless");
                return driver = new EdgeDriver(edgeOpt);

            case "firefox","Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOpt = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                firefoxOpt.addArguments("--no-sandbox");
                firefoxOpt.addArguments("--disable-dev-shm-usage");
                firefoxOpt.addArguments("--window-size=1600,900");
                firefoxOpt.setProfile(profile);
                firefoxOpt.addArguments("--headless");
                return driver = new FirefoxDriver(firefoxOpt);
            default:
                return driver;
        }
    }
    @BeforeClass
    public void setUp(){
        driver = webDriverSelector("chrome");
        //driver.manage().window().maximize();
        driver.get("https://www.despegar.com.co/"); //here goes the website URL
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


}