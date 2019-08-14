package stepdef;

import cucumber.api.java.en.Given;
import dataTableObjects.UserDetailsObject;
import driver.FacebookWebConnector;
import pageobject.FacebookDemoPage;

public class FBStepDef extends FacebookDemoPage{
	
	private FacebookWebConnector fbCon;
	
	public FBStepDef(FacebookWebConnector fbCon) {
		super(fbCon);
		this.fbCon=fbCon;
		// TODO Auto-generated constructor stub
	}

	
	@Given("Go to Facebook Site")
	public void launchDemoSite() {
		fbCon.switchToFbWindow();
		fbCon.navigate();
		Messages.getMessages().add(Thread.currentThread().getId() +" Launch the Facebook application");
	}
	
	@Given("Enter Email Address in Facebook with DataTable")
	public void userEmail(UserDetailsObject userDtls) throws InterruptedException {
		enterEmail(userDtls);
		Messages.getMessages().add(Thread.currentThread().getId() +" Enter User Name");
	}
	
    @Given("Enter Email Address with Example with UserName as {string} and Passwword as {string}")
    public void userEmail(String userName,String password) throws InterruptedException {
        enterEmail(userName,password);
        Messages.getMessages().add(Thread.currentThread().getId() +" Enter User Name");
       
    }
	
	@Given("Click on Forgot Password")
	public void forgotPassword() throws Exception {
		clickForgotPassword();
		Messages.getMessages().add(Thread.currentThread().getId() +" Click on Forgot Password");
	}
	
}
