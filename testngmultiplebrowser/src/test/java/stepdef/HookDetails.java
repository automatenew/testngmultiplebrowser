package stepdef;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;
import driver.DriverFactory;
import driver.FacebookDriverFactory;

public class HookDetails {
	private static Logger log = Logger.getLogger(HookDetails.class);
	
	@Before
	public void before(Scenario scenario) {

		System.out.println("HookDetails--->@Before"+"BEFORE HOOK------>"+"Starting -----> " + scenario.getName());
        log.info("BEFORE HOOK------>"+"Starting -----> " + scenario.getName());
		log.info("STARTING FEATURE FILE" + scenario.getUri());
		System.out.println("HookDetails.before()------>@BeforeClass--Thread ID = " + Thread.currentThread().getId());
		System.out.println("HookDetails.before()------>@BeforeClass--Thread NAME = "+Thread.currentThread().getName());


	}

	@BeforeStep
	public void beforeStep(Scenario scen) {
		Messages.setMessages(new ArrayList<>());
		System.out.println("HookDetails---->@BeforeStep---->");
		System.out.println("HookDetails---->@BeforeStep--Thread ID = " + Thread.currentThread().getId());
		System.out.println("HookDetails---->@BeforeStep--Thread NAME = "+Thread.currentThread().getName());
	}

	@AfterStep
	public static void addStepLog(Scenario scenario) {
		System.out.println("HookDetails---->@AfterStep---->AFTER-STEP");
		System.out.println("HookDetails---->@AfterStep--Thread ID = " + Thread.currentThread().getId());
		System.out.println("HookDetails---->@AfterStep--Thread NAME = "+Thread.currentThread().getName());
		Messages.getMessages().forEach(m -> scenario.write(m));
		if (DriverFactory.getDriver() != null) {
				System.out.println("HookDetails---->@AfterStep---->AFTER-STEP-GOOGLE");
				System.out.println("HookDetails---->@AfterStep--Thread ID = " + Thread.currentThread().getId());
				System.out.println("HookDetails---->@AfterStep--Thread NAME = "+Thread.currentThread().getName());
				log.info("AFTER-STEP");
				log.info("Scenario Status" + scenario.getStatus());

				try {
					final byte[] screenShot = ((TakesScreenshot) DriverFactory.getDriver())
							.getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenShot, "image/png");
					scenario.write("URL: " + DriverFactory.getDriver().getCurrentUrl());
				} catch (WebDriverException exception) {
					exception.printStackTrace();
				}
		}
		if(FacebookDriverFactory.getFacebookDriver()!=null) {
			System.out.println("AFTER-STEP");
			System.out.println("HookDetails---->@AfterStep---->AFTER-STEP-FACEBOOK");
			System.out.println("HookDetails---->@AfterStep--Thread ID = " + Thread.currentThread().getId());
			System.out.println("HookDetails---->@AfterStep--Thread NAME = "+Thread.currentThread().getName());
			log.info("AFTER-STEP");
			log.info("Scenario Status" + scenario.getStatus());

			try {
				final byte[] screenShotF = ((TakesScreenshot) FacebookDriverFactory.getFacebookDriver())
						.getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenShotF, "image/png");
				scenario.write("URL: "+FacebookDriverFactory.getFacebookDriver().getCurrentUrl()+ "TITLE"+ FacebookDriverFactory.getFacebookDriver().getTitle());
			} catch (WebDriverException exception) {
				exception.printStackTrace();
			}
		}

		// need to take screenshot and attach to each steps

	}

	@After
	public void after(Scenario scenario) {

		System.out.println("AFTER HOOK");
		System.out.println("HookDetails---->@After---->AFTER");
		System.out.println("HookDetails---->@After--Thread ID = " + Thread.currentThread().getId());
		System.out.println("HookDetails---->@After--Thread NAME = "+Thread.currentThread().getName());
/*		if (DriverFactory.getDriver() != null) {
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
		}*/
	}
}
