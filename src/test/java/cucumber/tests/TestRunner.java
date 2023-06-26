package cucumber.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/PurchaseTest.feature",
        glue = {"cucumber.tests"},
        //plugin = {"pretty","html:target/cucumber-Report","jason;target/report.json"},
        tags = "@passengerInformation")
public class TestRunner extends AbstractTestNGCucumberTests {

}
