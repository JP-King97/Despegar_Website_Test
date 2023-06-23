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
    private final By phoneNumberType = By.cssSelector("select[id=\"formData.contactData.phones[0].type\"]");
    private final By cellphoneCountryBox = By.cssSelector("input[id=\"formData.contactData.phones[0].countryCode\"]");
    private final By cellphoneNumberBox = By.cssSelector("input[id=\"formData.contactData.phones[0].number\"]");
    private final By bankChoicesDropdown = By.cssSelector("#card-selector-0");
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

    public void selectNumberType(String numberType){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");

        switch(numberType){
            case "Celular","celular":
                driver.findElement(phoneNumberType).sendKeys("celular");
                break;
            case "Particular","particular":
                driver.findElement(phoneNumberType).sendKeys("particular");
                break;
            default:
                System.out.println("cellphone number type not found");
        }
    }

    public void selectPhoneCountry(String cellphoneCountry){
        driver.findElement(cellphoneCountryBox).sendKeys(cellphoneCountry+Keys.ENTER);
    }

    public void enterPhoneNumber(String phoneNumber){
        driver.findElement(cellphoneNumberBox).sendKeys(phoneNumber + Keys.ENTER);
    }

    public void selectPaymentType(String paymentType){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        String ID;
        switch(paymentType){
            case "Tarjeta de credito":
                ID = driver.findElement(By.cssSelector("input[value=\"ONE_CARD_CREDIT\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+ selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            case "2 tarjetas de credito":
                ID = driver.findElement(By.cssSelector("input[value=\"MULTIPLE_CARD\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+ selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            case "Su Red":
                ID = driver.findElement(By.cssSelector("input[value=\"CASH_DEPOSIT_GANAC\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+ selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            case "Efecty":
                ID = driver.findElement(By.cssSelector("input[value=\"CASH_DEPOSIT_EFECTY\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+ selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            case "Criptomonedas":
                ID = driver.findElement(By.cssSelector("input[value=\"CASH_TRANSFER_BNB\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+ selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            case "PSE":
                ID = driver.findElement(By.cssSelector("input[value=\"CASH_TRANSFER_GFCF\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+ selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            default:
                System.out.println("Payment method not found");
        }
    }

    private int selectPaymentMethodNumber(String ID){
        int pm=0;
        switch(ID){
            case "payment-method-0":
                return pm = 1;
            case "payment-method-1":
                return pm = 2;
            case "payment-method-2":
                return pm = 3;
            case "payment-method-3":
                return pm = 4;
            case "payment-method-4":
                return pm = 5;
            case "payment-method-5":
                return pm = 6;
            default:
                System.out.println("payment method not founded");
                return pm;
        }
    }

    public void selectBank(String bank){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        driver.findElement(bankChoicesDropdown).sendKeys(bank+Keys.ENTER);
    }
}
