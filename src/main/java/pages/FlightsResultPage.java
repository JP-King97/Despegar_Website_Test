package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlightsResultPage {
    WebDriver driver ;
    private final By popUpCloseButton = By.cssSelector("span[class=\"login-aggressive--button login-aggressive--button-close shifu-3-btn-ghost\"]");
    public FlightsResultPage(WebDriver driver){
        this.driver=driver;
    }

    public void closePopUpDiscount(){
        driver.findElement(popUpCloseButton).click();
    }

}
