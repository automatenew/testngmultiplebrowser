package stepdef;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import driver.DriverFactory;

public class Hook {
	@Before("not @Api")
	public void before(Scenario scenario) {

		System.out.println("BEFORE HOOK");

	}

	@AfterStep("not @Api")
	public static void addStepLog(Scenario scenario) {
		if (DriverFactory.getDriver() != null) {
			System.out.println("AFTER-STEP");
			System.out.println("scenario.getUri" + scenario.getUri());
			System.out.println("scenario.getUri" + scenario.getName());
			try {
				final byte[] screenShot = ((TakesScreenshot) DriverFactory.getDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenShot, "image/png");
				scenario.write("URL: " + DriverFactory.getDriver().getCurrentUrl());
			} catch (WebDriverException exception) {
				exception.printStackTrace();
			}
		}

		// need to take screenshot and attach to each steps

	}

	@After("not @Api")
	public void after(Scenario scenario) {

		System.out.println("AFTER HOOK");
		if (DriverFactory.getDriver() != null) {
			if (scenario.isFailed()) {
				try {
					final byte[] screenShot = ((TakesScreenshot) DriverFactory.getDriver())
							.getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenShot, "image/png");
					scenario.write("URL: " + DriverFactory.getDriver().getCurrentUrl());
				} catch (WebDriverException exception) {
					exception.printStackTrace();
				}

			}
		}
	}
}
