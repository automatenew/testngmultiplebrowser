package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(glue = "stepdef", 
                 monochrome = true,
                 plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
                		 "json:target/json-cucumber-reports/cukejson.json",
                		 "testng:target/testng-cucumber-reports/cuketestng.xml",
                		 "pretty:target/STDOUT.out",
                		 "rerun:rerunTC/rerun.txt"},
                 features = "src/test/resources/features/")
                 //Rerun the feature file by placing "@rerunTC/rerun.txt" or "src/test/resources/features/"
public class RunnerIT extends AbstractTestNGCucumberParallelTests {

}
