package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import driver.DriverFactory;

public class GoogleSearchPO extends LoadableComponent<GoogleSearchPO>{
	
	public String searchResult_css="div[class='srg'] a > h3[class='LC20lb']";
	
	public GoogleSearchPO() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}
	
	public int searchResultCount() {
		
		int count=DriverFactory.getDriver().findElements(By.cssSelector(searchResult_css)).size();
		return count + 1;
	}
	
	@Override
	protected void load() {
	}

	@Override
	protected void isLoaded() throws Error {
		
	}
}
