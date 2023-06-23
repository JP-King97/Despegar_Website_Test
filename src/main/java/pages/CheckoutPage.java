package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;
    private final By firstNameBox = By.cssSelector("input[class=\"input-tag traveler-first-name ng-untouched ng-pristine ng-invalid\"]");
    private final By lastNameBox = By.cssSelector("input[class=\"input-tag traveler-last-name ng-untouched ng-pristine ng-invalid\"]");
    private final By countryBox = By.cssSelector("select[class=\"select-tag traveler-nationality ng-untouched ng-pristine ng-valid\"]");
    private final By documentTypeDropdown = By.cssSelector("#traveler-identification-type-0");
    private final By documentNumber = By.cssSelector("input[class=\"input-tag traveler-identification-number ng-untouched ng-pristine ng-invalid\"]");
    private final By dayOfBirth = By.cssSelector("#traveler-birthday-day-0");
    private final By monthOfBirth = By.cssSelector("#traveler-birthday-month-0");
    private final By yearOfBirth = By.cssSelector("#traveler-birthday-year-0");
    private final By femaleCheckBox = By.cssSelector("#traveler-gender-0 > div > div > ul > li:nth-child(1)");
    private final By maleCheckBox = By.cssSelector("#traveler-gender-0 > div > div > ul > li:nth-child(2)");
    private final By emailBox = By.cssSelector("input[id=\"formData.contactData.mainEmailAddress\"]");
    private final By emailConfirmationBox = By.cssSelector("input[id=\"formData.contactData.repeatMainEmailAddress\"]");
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

    public void enterNumberID(String documentType,long ID){
        driver.findElement(documentTypeDropdown).sendKeys(documentType);
        driver.findElement(documentNumber).sendKeys(""+ID+"");
    }

    public void selectDateOfBirth(int day, int month, int year){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        driver.findElement(dayOfBirth).sendKeys(""+day+"");
        driver.findElement(monthOfBirth).sendKeys(""+month+"");
        driver.findElement(yearOfBirth).sendKeys(""+year+"");
    }

    public void selectSex(String sex){
        switch(sex){
            case "m", "M" :
                driver.findElement(maleCheckBox).click();
                break;
            case "f", "F" :
                driver.findElement(femaleCheckBox).click();
                break;
            default:
                System.out.println("Sex option not founded");
                break;
        }
    }

    public void enterEmail(String email){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        driver.findElement(emailBox).click();
        driver.findElement(emailBox).sendKeys(email);
    }

    public void enterEmailConfirmation(String confirmationEmail){
        driver.findElement(emailConfirmationBox).click();
        driver.findElement(emailConfirmationBox).sendKeys(confirmationEmail);
    }


}
