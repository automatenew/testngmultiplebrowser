package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(glue = "stepdef", plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "json:target/json-cucumber-reports/cukejson.json","testng:target/testng-cucumber-reports/cuketestng.xml" },features = "src/test/resources/features/")
public class RunnerIT extends AbstractTestNGCucumberParallelTests {

}
