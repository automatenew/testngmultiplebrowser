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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Exceptions.NotFoundElement;
import net.bytebuddy.implementation.bytecode.Throw;

public class FacebookWebConnector {
	
	public Properties prop;
	public String fbWinHandle="";
	private static Logger log = Logger.getLogger(FacebookWebConnector.class);
	
	public FacebookWebConnector(FacebookSharedDriver gmaildriver) {
		
	}
	
	
	public void navigate() {
		FacebookDriverFactory.getFacebookDriver().get("https://www.facebook.com/");	
		fbWinHandle=FacebookDriverFactory.getFacebookDriver().getWindowHandle();
		System.out.println("FacebookWebConnector--->Thread ID = " + Thread.currentThread().getId());
		System.out.println("FacebookWebConnector---->Thread NAME = "+Thread.currentThread().getName());
		System.out.println("FacebookWebConnector---->Hashcode of webDriver instance = " + FacebookDriverFactory.getFacebookDriver().hashCode());
	}
	
	///Pega Methods
	public void clickElement(String fieldName,String objectKey) throws Exception {
		System.out.println("FacebookWebConnector---->Thread ID = " + Thread.currentThread().getId());
		System.out.println("FacebookWebConnector--->Thread NAME = "+Thread.currentThread().getName());
		System.out.println("Hashcode of webDriver instance = " + FacebookDriverFactory.getFacebookDriver().hashCode());
		try
		{
			System.out.println(fieldName);
			System.out.println(objectKey);
			try
			{
				if(getObj(fieldName,objectKey).isDisplayed())
				{
					getObj(fieldName,objectKey).click();
					System.out.println("Element found "+objectKey);
				}
				else
				{
					System.out.println("Element not found "+objectKey);
				}
			}
			catch(StaleElementReferenceException e)
			{
				if(getObj(fieldName,objectKey).isDisplayed())
				{
					getObj(fieldName,objectKey).click();
					System.out.println("Element found "+objectKey);
				}
				else
				{
					System.out.println("Element not found "+objectKey);
				}
			}
		}
		catch(Exception e)
		{
			log.log(Level.ERROR, e.getStackTrace(), e);
			e.printStackTrace();
			throw new NotFoundElement(e);
			//reportFailure("Element not found "+objectKey);
		}
			
	}
	
	public void appendStrToFile(String fileName, String str) 
	{ 
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
		System.out.println("FacebookWebConnector---->input()---Thread ID = " + Thread.currentThread().getId());
		System.out.println("FacebookWebConnector----->input()----Thread NAME = "+Thread.currentThread().getName());
		System.out.println("FacebookWebConnector------>Hashcode of webDriver instance = " + FacebookDriverFactory.getFacebookDriver().hashCode());
		try
		{
			if(getObj(fieldName,objectKey).isDisplayed())
			{
				log.info("Data to enter "+data);
				getObj(fieldName,objectKey).sendKeys(data);
			}
			else
			{
				
			}
		}
		catch(Exception e)
		{
			log.log(Level.ERROR, e.getMessage(), e);
			e.printStackTrace();
		}
	}
	public void select(String fieldName,String objectKey,String data) {
		if(getObj(fieldName,objectKey).isDisplayed())
		{
			Select s= new Select(getObj(fieldName,objectKey));
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
		if(getObj(fieldName,objectKey).isDisplayed())
		{
			getObj(fieldName,objectKey).clear();
		}
		else
		{
		}
	}
	
	public String getAttributeValue(String fieldName,String objectKey,String attributeName) {
		String value="";
		try
		{
			if(getObj(fieldName,objectKey).isDisplayed())
			{
				value=getObj(fieldName,objectKey).getAttribute(attributeName);
			}
		}
		catch(StaleElementReferenceException e)
		{
			if(getObj(fieldName,objectKey).isDisplayed())
			{
				value=getObj(fieldName,objectKey).getAttribute(attributeName);
			}
		}
		return value;
	}
	
	// central function to extract objects
			public WebElement getObj(String fieldName,String objectKey) {
				WebElement e = null;
				try {
					if(fieldName.endsWith("_xpath")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.xpath(objectKey));// present
						
					}else if(fieldName.endsWith("_id")) {
							e = FacebookDriverFactory.getFacebookDriver().findElement(By.id(objectKey));// present
					}
					else if(fieldName.endsWith("_name")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.name(objectKey));// present
					}
					else if(fieldName.endsWith("_css")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.cssSelector(objectKey));// present
					}
					else  if(fieldName.endsWith("_linkText")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.linkText(objectKey));// present
					}
					else if(fieldName.endsWith("_tagName")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.tagName(objectKey));// present
					}
					else if(fieldName.endsWith("_partialLinkText")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.partialLinkText(objectKey));// present
					}
				}
				catch(StaleElementReferenceException stale)
				{
					if(fieldName.endsWith("_xpath")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.xpath(objectKey));// present
					}else if(fieldName.endsWith("_id")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.id(objectKey));// present
					}
					else if(fieldName.endsWith("_name")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.name(objectKey));// present
					}
					else if(fieldName.endsWith("_css")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.cssSelector(objectKey));// present
					}
					else  if(fieldName.endsWith("_linkText")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.linkText(objectKey));// present
					}
					else if(fieldName.endsWith("_tagName")) {
						e = FacebookDriverFactory.getFacebookDriver().findElement(By.tagName(objectKey));// present
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
					if(getObj(fieldName,objectKey).isDisplayed())
					{
						getEleText=getObj(fieldName, objectKey).getText();
						log.info("Get Text: " + getEleText);
					}
				}
				catch(StaleElementReferenceException e) {
					if(getObj(fieldName,objectKey).isDisplayed())
					{
						getEleText=getObj(fieldName, objectKey).getText();
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

			public void switchToFbWindow()
			{
				FacebookDriverFactory.getFacebookDriver().switchTo().window(fbWinHandle);
			}
			
}
