package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;
    private final By firstNameBox = By.cssSelector("input[class=\"input-tag traveler-first-name ng-untouched ng-pristine ng-invalid\"]");
    private final By lastNameBox = By.cssSelector("input[class=\"input-tag traveler-last-name ng-untouched ng-pristine ng-invalid\"]");
    private final By countryBox = By.cssSelector("select[class=\"select-tag traveler-nationality ng-untouched ng-pristine ng-valid\"]");
    private final By documentTypeDropdown = By.cssSelector("#traveler-identification-type-0");
    private final By documentNumberBox = By.cssSelector("input[class=\"input-tag traveler-identification-number ng-untouched ng-pristine ng-invalid\"]");
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
    private final By firstNameOnBillBox = By.cssSelector("#invoice-first-name-0");
    private final By lastNameOnBillBox = By.cssSelector("#invoice-last-name-0");
    private final By documentTypeOnBillDropdown = By.cssSelector("select[id=\"invoice-fiscal-identification-type-0\"]");
    private final By documentNumberOnBillBox = By.cssSelector("input[id=\"invoice-fiscal-identification-number-0\"]");
    private final By passengerStateOnBill = By.cssSelector("select[id=\"invoice-fiscal-address-state-0\"]");
    private final By passengerCityOnBill = By.cssSelector("input[id=\"invoice-fiscal-address-city-CASH_TRANSFER_GFCF-0\"]");
    private final By passengerAddressOnBill = By.cssSelector("input[class=\"input-tag invoice-fiscal-address-street ng-untouched ng-pristine ng-invalid\"]");
    private final By termAndConditionsCheckBox = By.cssSelector("i[class=\"checkbox-check eva-3-icon-checkmark -eva-3-mr-sm\"]");
    private final By noAssistanceButton = By.cssSelector("a[class=\"chk-button -lg -secondary eva-3-btn\"]");
    private final WebDriverWait wait;
    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
    }

    public void waitToBeClickable(By byElement){
        wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }

    public boolean elementExistCheck(By byElement){
        try {
            waitToBeClickable(byElement);
        }catch (Exception e){
            return false;
        }

        int birthDay = driver.findElements(byElement).size();
        return (birthDay != 0);
    }

    public void enterFirstName(String firstName){
        waitToBeClickable(firstNameBox);
        driver.findElement(firstNameBox).click();
        driver.findElement(firstNameBox).sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        waitToBeClickable(lastNameBox);
        driver.findElement(lastNameBox).click();
        driver.findElement(lastNameBox).sendKeys(lastName);
    }

    public void selectCountry(String country){
        waitToBeClickable(countryBox);
        driver.findElement(countryBox).sendKeys(country + Keys.ENTER);
    }

    public void enterNumberID(String documentType,String ID){
        waitToBeClickable(documentTypeDropdown);
        driver.findElement(documentTypeDropdown).sendKeys(documentType);
        driver.findElement(documentNumberBox).sendKeys(ID);
    }

    public void selectDateOfBirth(String day, String month, String year){
        if(elementExistCheck(dayOfBirth)){
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,400)","");
            driver.findElement(dayOfBirth).sendKeys(day);
            driver.findElement(monthOfBirth).sendKeys(month);
            driver.findElement(yearOfBirth).sendKeys(year);
        }
       }

    public void selectSex(String sex){
        if(elementExistCheck(maleCheckBox)){
            switch(sex){
                case "masculino", "Masculino" :
                    driver.findElement(maleCheckBox).click();
                    break;
                case "femenino", "Femenino" :
                    driver.findElement(femaleCheckBox).click();
                    break;
                default:
                    System.out.println("Sex option not founded");
                    break;
            }
        }
    }

    public void enterEmail(String email){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)","");
        waitToBeClickable(emailBox);
        driver.findElement(emailBox).click();
        driver.findElement(emailBox).sendKeys(email);
    }

    public void enterEmailConfirmation(String confirmationEmail){
        waitToBeClickable(emailConfirmationBox);
        driver.findElement(emailConfirmationBox).click();
        driver.findElement(emailConfirmationBox).sendKeys(confirmationEmail);
    }

    public void selectNumberType(String numberType){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");

        waitToBeClickable(phoneNumberType);
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
        waitToBeClickable(cellphoneCountryBox);
        driver.findElement(cellphoneCountryBox).sendKeys(cellphoneCountry+Keys.ENTER);
    }

    public void enterPhoneNumber(String phoneNumber){
        waitToBeClickable(cellphoneNumberBox);
        driver.findElement(cellphoneNumberBox).sendKeys(phoneNumber + Keys.ENTER);
    }

    public void selectPaymentType(String paymentType){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        String ID;
        waitToBeClickable(By.cssSelector("payment-method-selector-radio-button-option:nth-child(1) > li > p > label > i"));
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
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+selectPaymentMethodNumber(ID)+") > li > p > label > i")).click();
                break;
            case "PSE":
                ID = driver.findElement(By.cssSelector("input[value=\"CASH_TRANSFER_GFCF\"]")).getAttribute("id");
                driver.findElement(By.cssSelector("payment-method-selector-radio-button-option:nth-child("+(selectPaymentMethodNumber(ID))+") > li > p > label > i")).click();
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
            case "payment-method-6":
                return pm = 7;
            default:
                System.out.println("payment method not founded");
                return pm;
        }
    }

    public void selectBank(String bank){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        waitToBeClickable(bankChoicesDropdown);
        driver.findElement(bankChoicesDropdown).sendKeys(bank+Keys.ENTER);
    }

    public void selectFiscalStatus(String fiscalStatus){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)","");
        waitToBeClickable(By.cssSelector("div[id=\"formData.paymentData.cashPayments[0].invoice.fiscalStatus\"] > div > ul > li:nth-child(1) > label > span > i"));
        switch(fiscalStatus){
            case "natural","Natural":
                driver.findElement(By.cssSelector("div[id=\"formData.paymentData.cashPayments[0].invoice.fiscalStatus\"] > div > ul > li:nth-child(1) > label > span > i"));
                break;
            case "juridica","Juridica":
                driver.findElement(By.cssSelector("div[id=\"formData.paymentData.cashPayments[0].invoice.fiscalStatus\"] > div > ul > li:nth-child(2) > label > span > i"));
                break;
            default:
                System.out.println("fiscal status not found");
        }
    }

    public void enterFirstNameOnBill(String firstNameOnBill){
        waitToBeClickable(firstNameOnBillBox);
        driver.findElement(firstNameOnBillBox).sendKeys(firstNameOnBill);
    }

    public void enterLastNameOnBill(String lastNameOnBill){
        waitToBeClickable(lastNameOnBillBox);
        driver.findElement(lastNameOnBillBox).sendKeys(lastNameOnBill);
    }

    public void selectDocumentTypeOnBill(String documentType){
        waitToBeClickable(documentTypeOnBillDropdown);
        driver.findElement(documentTypeOnBillDropdown).sendKeys(documentType);
    }

    public void enterDocumentNumberOnBill(String ID){
        waitToBeClickable(documentNumberOnBillBox);
        driver.findElement(documentNumberOnBillBox).sendKeys(ID);
    }

    public void enterDocumentStateOnBill(String state){
        waitToBeClickable(passengerStateOnBill);
        driver.findElement(passengerStateOnBill).sendKeys(state);
    }

    public void enterPassengerCityOnBill(String city){
        waitToBeClickable(passengerCityOnBill);
        driver.findElement(passengerCityOnBill).sendKeys(city);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(passengerCityOnBill).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(passengerCityOnBill).sendKeys(Keys.ENTER);
        //driver.findElement(By.cssSelector("div[id=\"ui-id-15\"]")).click();
    }

    public void enterPassengerAddressOnBill(String address){
        waitToBeClickable(passengerAddressOnBill);
        driver.findElement(passengerAddressOnBill).sendKeys(address);
    }

    public void checkTermsAndConditions(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,800)","");
        waitToBeClickable(termAndConditionsCheckBox);
        driver.findElement(termAndConditionsCheckBox).click();
    }

    public CheckoutPurchaseDetailsPage pressNoAssistanceButton(){
        waitToBeClickable(noAssistanceButton);
        driver.findElement(noAssistanceButton).click();
        return new CheckoutPurchaseDetailsPage(driver);
    }
    public boolean urlCheck(){
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("https://www.despegar.com.co/checkout");
    }
}
