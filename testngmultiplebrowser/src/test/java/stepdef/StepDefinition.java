package stepdef;


import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import driver.SharedDriver;
import pageobject.GoogleHomePO;
import pageobject.GoogleSearchPO;

public class StepDefinition {
	
	private GoogleHomePO ghPO;
	private GoogleSearchPO gsPO;
	
	public StepDefinition(SharedDriver driver, GoogleHomePO ghPO, GoogleSearchPO gsPO) {
		this.ghPO = ghPO;
		this.gsPO = gsPO;
	}
	
	@Given("Go to google page")
	public void given() {
		ghPO.get();
	}
	
	@When("Enter search {string}")
	public void when(String search) {
		gsPO = ghPO.performSearch(search);
		System.out.format("\nCount results for %s search is %d.\n", search, gsPO.searchResultCount());
			
		
	}
	public void step(String step, String scenario) {
		//Scenario scenario;
		System.out.format("Thread ID - %2d %s step from %s.\n", Thread.currentThread().getId(), step.toUpperCase(),scenario.toUpperCase());
		//System.out.format("Thread ID - %2d %s step from %s.\n", Thread.currentThread().getId(), step.toUpperCase(),scenario.toUpperCase());
	}
}
