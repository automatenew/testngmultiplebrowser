package runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import cucumber.api.CucumberOptions;


@CucumberOptions(glue = "stepdef", monochrome = true, plugin = {
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"json:target/json-cucumber-reports/cukejson.json", "testng:target/testng-cucumber-reports/cuketestng.xml",
		"rerun:rerunTC/rerun.txt" },
		tags= {"@tagTest1"},
		features = "src/test/resources/features/")
// Rerun the feature file by placing "@rerunTC/rerun.txt" or
// "src/test/resources/features/"
public class RunnerIT extends AbstractTestNGCucumberParallelTests {
	
	private static long duration;

	@BeforeClass
	public static void before() {
		duration = System.currentTimeMillis();
		System.out.println("Starting Runner");
		System.out.println("RunnerIT.before()------>@BeforeClass--Thread ID = " + Thread.currentThread().getId());
		System.out.println("RunnerIT.before()------>@BeforeClass--Thread NAME = "+Thread.currentThread().getName());
	}

	@AfterClass
	public static void after() {
		duration = System.currentTimeMillis() - duration;
		System.out.println("DURATION - " + duration);
		System.out.println("RunnerIT.before()------>@AfterClass--Thread ID = " + Thread.currentThread().getId());
		System.out.println("RunnerIT.before()------>@AfterClass--Thread NAME = "+Thread.currentThread().getName());
	}

}
