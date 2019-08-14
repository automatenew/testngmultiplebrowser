package driver;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebConnector {
	
	public Properties prop;
	private static Logger log = Logger.getLogger(WebConnector.class);
	
	public WebConnector(SharedDriver driver) {
		//name="A";
		if(prop==null) {
			System.out.println("WebConnector---->Constructor--Thread ID = " + Thread.currentThread().getId());
			System.out.println("WebConnector---->Constructor--Thread NAME = "+Thread.currentThread().getName());
			try {
				prop= new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\setUp.properties");
				prop.load(fs);
				log.info("Loading the Set up Propeties File:"+prop.getProperty("environment"));
				if(prop.getProperty("environment").equalsIgnoreCase("dev"))
				{
					FileInputStream fs1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\env_dev.properties");
					prop.load(fs1);
				}
				else if(prop.getProperty("environment").equalsIgnoreCase("qa")){
					FileInputStream fs1 = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\env_qa.properties");
					prop.load(fs1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				// report
				log.error("Unexpected error while loading Properties Files",e);
			}
		}
	}
	
	
	public void navigate() {
		DriverFactory.getDriver().get(prop.getProperty("url"));	
		System.out.println("WebConnector---navigate()----Thread ID = " + Thread.currentThread().getId());
		System.out.println("WebConnector---navigate()Thread NAME = "+Thread.currentThread().getName());
		System.out.println("WebConnector---navigate()--Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
	}
	
	///Pega Methods
	public void clickElement(String fieldName,String objectKey) {
		System.out.println("WebConnector---navigate()Thread ID = " + Thread.currentThread().getId());
		System.out.println("WebConnector---navigate()Thread NAME = "+Thread.currentThread().getName());
		System.out.println("WebConnector---navigate()-->Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
		try
		{
			try
			{
				if(getObject(fieldName,objectKey).isDisplayed())
				{
					getObject(fieldName,objectKey).click();
				}
				else
				{
					log.error("Element not found "+objectKey);
				}
			}
			catch(StaleElementReferenceException e)
			{
				if(getObject(fieldName,objectKey).isDisplayed())
				{
					getObject(fieldName,objectKey).click();
				}
				else
				{
					log.error("Element not found "+objectKey);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//reportFailure("Element not found "+objectKey);
		}
			
	}
	
	public void appendStrToFile(String fileName, String str) 
	{ 
		System.out.println("Thread ID = " + Thread.currentThread().getId());
		System.out.println("Thread NAME = "+Thread.currentThread().getName());
		System.out.println("Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
		try { 
		
			// Open given file in append mode. 
			BufferedWriter out = new BufferedWriter( 
			new FileWriter(fileName, true)); 
			out.write(str); 
			out.close(); 
		} 
		catch (IOException e) { 
		System.out.println("exception occoured" + e); 
		} 
	} 
	
	public void input(String fieldName,String objectKey,String data) {
		try
		{
			if(getObject(fieldName,objectKey).isDisplayed())
			{
				log.info("Data to enter "+data);
				getObject(fieldName,objectKey).sendKeys(data);
			}
			else
			{
				
			}
		}
		catch(Exception e)
		{
			if(getObject(fieldName,objectKey).isDisplayed())
			{
				log.info("Data to enter "+data);
				getObject(fieldName,objectKey).sendKeys(data);
			}
			else
			{
			}
		}
	}
	public void select(String fieldName,String objectKey,String data) {
		System.out.println("Thread ID = " + Thread.currentThread().getId());
		System.out.println("Thread NAME = "+Thread.currentThread().getName());
		System.out.println("Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
		if(getObject(fieldName,objectKey).isDisplayed())
		{
			Select s= new Select(getObject(fieldName,objectKey));
			if(data.length()>1)
			{
				s.selectByVisibleText(data);
			}
		}
		else
		{
		}
	}
	
	public void clear(String fieldName,String objectKey) {
		System.out.println("Thread ID = " + Thread.currentThread().getId());
		System.out.println("Thread NAME = "+Thread.currentThread().getName());
		System.out.println("Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
		if(getObject(fieldName,objectKey).isDisplayed())
		{
			getObject(fieldName,objectKey).clear();
		}
		else
		{
		}
	}
	
	public String getAttributeValue(String fieldName,String objectKey,String attributeName) {
		System.out.println("Thread ID = " + Thread.currentThread().getId());
		System.out.println("Thread NAME = "+Thread.currentThread().getName());
		System.out.println("Hashcode of webDriver instance = " + DriverFactory.getDriver().hashCode());
		String value="";
		try
		{
			if(getObject(fieldName,objectKey).isDisplayed())
			{
				value=getObject(fieldName,objectKey).getAttribute(attributeName);
			}
		}
		catch(StaleElementReferenceException e)
		{
			if(getObject(fieldName,objectKey).isDisplayed())
			{
				value=getObject(fieldName,objectKey).getAttribute(attributeName);
			}
		}
		return value;
	}
	
	// central function to extract objects
			public WebElement getObject(String fieldName,String objectKey) {
				WebElement e = null;
				try {
					if(fieldName.endsWith("_xpath")) {
						e = DriverFactory.getDriver().findElement(By.xpath(objectKey));// present
						
					}else if(fieldName.endsWith("_id")) {
							e = DriverFactory.getDriver().findElement(By.id(objectKey));// present
					}
					else if(fieldName.endsWith("_name")) {
						e = DriverFactory.getDriver().findElement(By.name(objectKey));// present
					}
					else if(fieldName.endsWith("_css")) {
						e = DriverFactory.getDriver().findElement(By.cssSelector(objectKey));// present
					}
					else  if(fieldName.endsWith("_linkText")) {
						e = DriverFactory.getDriver().findElement(By.linkText(objectKey));// present
					}
					else if(fieldName.endsWith("_tagName")) {
						e = DriverFactory.getDriver().findElement(By.tagName(objectKey));// present
					}
					else if(fieldName.endsWith("_partialLinkText")) {
						e = DriverFactory.getDriver().findElement(By.partialLinkText(objectKey));// present
					}
				}
				catch(StaleElementReferenceException stale)
				{
					if(fieldName.endsWith("_xpath")) {
						e = DriverFactory.getDriver().findElement(By.xpath(objectKey));// present
					}else if(fieldName.endsWith("_id")) {
						e = DriverFactory.getDriver().findElement(By.id(objectKey));// present
					}
					else if(fieldName.endsWith("_name")) {
						e = DriverFactory.getDriver().findElement(By.name(objectKey));// present
					}
					else if(fieldName.endsWith("_css")) {
						e = DriverFactory.getDriver().findElement(By.cssSelector(objectKey));// present
					}
					else  if(fieldName.endsWith("_linkText")) {
						e = DriverFactory.getDriver().findElement(By.linkText(objectKey));// present
					}
					else if(fieldName.endsWith("_tagName")) {
						e = DriverFactory.getDriver().findElement(By.tagName(objectKey));// present
					}
				}
				catch(Exception ex) 
				{
					ex.printStackTrace();
					//reportFailure("Unable to extract Object "+objectKey);
				}
				return e;
			}
				
			public String getText(String fieldName,String objectKey) {
				String getEleText = "";
				try {
					if(getObject(fieldName,objectKey).isDisplayed())
					{
						getEleText=getObject(fieldName, objectKey).getText();
						log.info("Get Text: " + getEleText);
					}
				}
				catch(StaleElementReferenceException e) {
					if(getObject(fieldName,objectKey).isDisplayed())
					{
						getEleText=getObject(fieldName, objectKey).getText();
						log.info("Get Text: " + getEleText);
					}
				}
				catch(NullPointerException e)
				{
					getEleText="";
				}
				catch(Exception ex) {
					ex.printStackTrace();
					//reportFailure("Not able to get the text of element "+fieldName);
				}
				return getEleText;
			}

}
