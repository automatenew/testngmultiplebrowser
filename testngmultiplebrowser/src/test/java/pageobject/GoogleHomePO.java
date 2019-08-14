package pageobject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.LoadableComponent;

import driver.DriverFactory;
import driver.WebConnector;

public class GoogleHomePO extends LoadableComponent<GoogleHomePO>{
	
	public String searchTextBox_css="input[name='q']";
	public String searchButton_css="div[class='FPdoLc VlcLAe'] input[name='btnK']";
	public String googleImage="img[alt='Google']";
	
	WebConnector con;
	public GoogleHomePO(WebConnector con) {
		this.con=con;
	}

	public GoogleSearchPO performSearch(String search) {
		con.input("searchTextBox_css", searchTextBox_css, search);
		System.out.println("GoogleHomePO---->--Thread ID = " + Thread.currentThread().getId());
		System.out.println("GoogleHomePO---->--Thread NAME = "+Thread.currentThread().getName());
		//Intermittent error - To get rid of suggestion dropdown so search button can be found.
		//googleImage.click();
		Actions action = new Actions(DriverFactory.getDriver());
		action.moveByOffset(10, 10).click().perform();
		action.sendKeys(Keys.ESCAPE).perform();;
		
		con.clickElement("searchButton_css", searchButton_css);
		return new GoogleSearchPO();
	}

	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		
	}
}
