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
import java.util.Scanner;

public class BaseTest {
    public WebDriver driver;
    protected HomePage homePage;

    @BeforeTest
    public void setUp(){
       // Scanner scanner = new Scanner(System.in);
       // String choice = scanner.nextLine();
       // switch(choice){
       //     case "chrome","Chrome":
       //         WebDriverManager.chromedriver().setup();
       //         driver = new ChromeDriver();
       //         break;
       //     case "edge","Edge":
       //         WebDriverManager.edgedriver()
       // }
        WebDriverManager.chromedriver().setup();
        //  ChromeOptions chromeOptions = new ChromeOptions();
        //  chromeOptions.addArguments("disable-infobars"); // disabling infobars
        //  chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        //  chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.despegar.com.co"); //here goes the website URL
        homePage = new HomePage(driver);
    }

   // @AfterTest
   // public void tearDown(){
   //     driver.quit();
   // }
}
