package stepdef;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.WebConnector;
import pageobject.GoogleHomePO;
import pageobject.GoogleSearchPO;

public class StepDefinition {

	private GoogleHomePO ghPO;
	private GoogleSearchPO gsPO;
	private WebConnector con;

	public StepDefinition(WebConnector con, GoogleHomePO ghPO, GoogleSearchPO gsPO) {
		this.ghPO = ghPO;
		this.gsPO = gsPO;
		this.con = con;
	}

	@Given("Go to google page")
	public void given() {
		con.navigate();
		Messages.getMessages().add(Thread.currentThread().getId() + " Launch the Pega CRCC application");
	}

	@When("Enter search {string}")
	public void when(String search) {

		System.out.println("INSIDE TRY ");
		Messages.getMessages().add(Thread.currentThread().getId() + " WHEN STEP");
		try {
			gsPO = ghPO.performSearch(search);
			System.out.format("\nCount results for %s search is %d.\n", search, gsPO.searchResultCount());
			
		} catch (Exception e) {
			System.out.println("INSIDE CATCH ");
			e.printStackTrace();
			Assert.fail("Error details : " + e.getStackTrace());
			// reportFailure("Element not found "+objectKey);
		}

	}

	public void step(String step, String scenario) {
		// Scenario scenario;
		System.out.format("Thread ID - %2d %s step from %s.\n", Thread.currentThread().getId(), step.toUpperCase(),
				scenario.toUpperCase());
		// System.out.format("Thread ID - %2d %s step from %s.\n",
		// Thread.currentThread().getId(), step.toUpperCase(),scenario.toUpperCase());
	}

}
