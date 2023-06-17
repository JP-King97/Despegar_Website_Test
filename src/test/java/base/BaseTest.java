package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

public class BaseTest {
    public WebDriver driver;
    protected HomePage homePage;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chrOpt = new ChromeOptions();
        chrOpt.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64)"
                +"AppleWebKit/537.36 (KHTML, like Gecko)"
                +"Chrome/114.0.5735.134 Safari/537.36");
        driver = new ChromeDriver(chrOpt);
        driver.get("https://www.travelocity.com"); //here goes the website URL

        homePage = new HomePage(driver);
    }

   // @AfterTest
   // public void tearDown(){
   //     driver.quit();
   // }
}
