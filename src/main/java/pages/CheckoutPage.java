package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;
    private final By firstNameBox = By.cssSelector("input[class=\"input-tag traveler-first-name ng-untouched ng-pristine ng-invalid\"]");
    private final By lastNameBox = By.cssSelector("input[class=\"input-tag traveler-last-name ng-untouched ng-pristine ng-invalid\"]");
    private final By countryBox = By.cssSelector("select[class=\"select-tag traveler-nationality ng-untouched ng-pristine ng-valid\"]");
    private final By documentTypeDropdown = By.cssSelector("#traveler-identification-type-0 > option:nth-child(1)");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
    }

    public void enterFirstName(String firstName){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(firstNameBox).click();
        driver.findElement(firstNameBox).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(lastNameBox).click();
        driver.findElement(lastNameBox).sendKeys(lastName);
    }

    public void selectCountry(String country){
        driver.findElement(countryBox).sendKeys(country + Keys.ENTER);
    }


}
