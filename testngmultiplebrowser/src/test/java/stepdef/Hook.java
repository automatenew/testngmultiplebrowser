package stepdef;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook {

	@Before("not @Api")
	public void before() {

		System.out.println("BEFORE HOOK");
		//DriverFactory.getDriver().get(propertyHelper.getValue("baseUrl")));
		//OperationsHelper.generateRequiredAmountOfSymbols(6);
	}
	
	@After("not @Api")
	public void after(Scenario scenario) {
		
		System.out.println("AFTER HOOK");
		//if (DriverFactory.getDriver() != null) {
        //   if (scenario.isFailed()) {
        //        try {
        //            final byte[] screenShot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
        //            scenario.embed(screenShot, "image/png");
        //            scenario.write("URL: " + DriverFactory.getDriver().getCurrentUrl());
        //        } catch (WebDriverException exception) {
        //           exception.printStackTrace();
        //        }
        //    }
        //}
	}
}
