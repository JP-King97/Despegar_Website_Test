package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPurchaseDetailsPage {
    WebDriver driver;
    private final By headerText = By.cssSelector("span[class=\"eva-3-h3 cash-title\"]");
    private final By bankEntity = By.cssSelector("div.cash.bg-white.cash-rebranding > div:nth-child(1) > div > span");
    private final By paymentMethod = By.cssSelector("span > a > strong");
    private final By email = By.cssSelector("div.eva-3-row.mail-section > p > b");
    private final By locations = By.cssSelector("div[class=\"eva-3-h3 -eva-3-tc-gray-0\"]");
    private final By flightType = By.cssSelector("div[class=\"dm-d-text eva-3-p -eva-3-tc-gray-2 -eva-3-mb-xsm\"]");
    private final By depDate = By.cssSelector("div:nth-child(1) > product-dates-v2 > div > div > div:nth-child(2)");
    private final By arrDate = By.cssSelector(" div:nth-child(2) > product-dates-v2 > div > div > div:nth-child(2)");
    private final WebDriverWait wait;
    public CheckoutPurchaseDetailsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    public void waitToBeClickable(By byElement){
        wait.until(ExpectedConditions.elementToBeClickable(byElement));
    }
    public boolean pageURLCheck(){
        String currentURL = driver.getCurrentUrl();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return currentURL.contains("https://www.despegar.com.co/checkout/purchase");
    }

    public boolean headerTextCheck(){
        waitToBeClickable(headerText);
        String text = driver.findElement(headerText).getAttribute("innerText");
        String desireText = "¡Genial! Ahora solo te falta realizar el pago.";
        return text.equals(desireText);
    }

    public boolean bankEntityCheck(String desireBank){
        waitToBeClickable(bankEntity);
        String bank = driver.findElement(bankEntity).getAttribute("innerText");
        return bank.equals(desireBank);
    }

    public boolean paymentMethodCheck(String desirePaymentMethod){
        waitToBeClickable(paymentMethod);
        String pm = driver.findElement(paymentMethod).getAttribute("innerText");
        return pm.contains(desirePaymentMethod);
    }

    public boolean emailCheck(String desireEmail){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)","");
        waitToBeClickable(email);
        String e_mail = driver.findElement(email).getAttribute("innerText");
        return e_mail.equals(desireEmail);
    }

    public boolean locationsCheck(String departureLocation, String arrivalLocation){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)","");
        waitToBeClickable(locations);
        String locationsNames = driver.findElement(locations).getAttribute("innerText");
        return locationsNames.contains(departureLocation) && locationsNames.contains(arrivalLocation);
    }

    public boolean flightTypeCheck(){
        waitToBeClickable(flightType);
        String type = driver.findElement(flightType).getAttribute("innerText");
        return type.contains("Ida y vuelta");
    }

    public boolean departureDateCheck(int[] departureDateArray){
        waitToBeClickable(depDate);
        String departureDate = driver.findElement(depDate).getAttribute("innerText");
        return departureDate.contains(departureDateArray[0]+" "+getMonthInfo(""+departureDateArray[1])[1]+". "+departureDateArray[2]);
    }
    public boolean arrivalDateCheck(int[] arrivalDateArray){
        waitToBeClickable(arrDate);
        String arrivalDate = driver.findElement(arrDate).getAttribute("innerText");
        return arrivalDate.contains(arrivalDateArray[0]+" "+getMonthInfo(""+arrivalDateArray[1])[1]+". "+arrivalDateArray[2]);
    }

    private String[] getMonthInfo(String month){

        String[] monthInfo = new String[3] ;
        switch(month){
            case "Enero","1":
                monthInfo = new String[]{"1", "Ene", "Enero"};
                break;
            case "Febrero","2":
                monthInfo = new String[]{"2", "Feb", "Febrero"};
                break;
            case "Marzo","3":
                monthInfo = new String[]{"3", "Mar", "Marzo"};
                break;
            case "Abril","4":
                monthInfo = new String[]{"4", "Abr", "Abril"};
                break;
            case "Mayo","5":
                monthInfo = new String[]{"5", "May", "Mayo"};
                break;
            case "Junio","6":
                monthInfo = new String[]{"6", "Jun", "Junio"};
                break;
            case "Julio","7":
                monthInfo = new String[]{"7", "Jul", "Julio"};
                break;
            case "Agosto","8":
                monthInfo = new String[]{"8", "Ago", "Agosto"};
                break;
            case "Septiembre","9":
                monthInfo = new String[]{"9", "Sep", "Septiembre"};
                break;
            case "Octubre","10":
                monthInfo = new String[]{"10", "Oct", "Octubre"};
                break;
            case "Noviembre","11":
                monthInfo = new String[]{"11", "Nov", "Noviembre"};
                break;
            case "Diciembre","12":
                monthInfo = new String[]{"12", "Dic", "Diciembre"};
                break;
            default:
                throw new IllegalStateException("Unexciting Month: " + month);
        }
        return monthInfo;
    }
}
