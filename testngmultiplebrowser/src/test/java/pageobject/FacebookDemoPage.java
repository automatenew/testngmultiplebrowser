package pageobject;

import dataTableObjects.UserDetailsObject;
import driver.FacebookWebConnector;

public class FacebookDemoPage {

	public String email_id="email";
	public String password_id="pass";
	public String forgotPassword_xpath="//a[text()='Forgot account?']";
	
	private FacebookWebConnector fbCon;
	
	protected FacebookDemoPage(FacebookWebConnector fbCon)
	{
		this.fbCon=fbCon;
	}
	
	public void enterEmail(UserDetailsObject userDtls) throws InterruptedException
	{
		fbCon.switchToFbWindow();
		fbCon.input("email_id", email_id, userDtls.getEmailId());
		fbCon.input("password_id", password_id, userDtls.getPassword());
	}
	
	public void enterEmail(String userName,String password) throws InterruptedException
	{
		fbCon.switchToFbWindow();
		fbCon.input("email_id", email_id, userName);
		fbCon.input("password_id", password_id, password);
		
	}
	
	public void clickForgotPassword() throws Exception
	{
		fbCon.clickElement("forgotPassword_xpath", forgotPassword_xpath);
	}
}
