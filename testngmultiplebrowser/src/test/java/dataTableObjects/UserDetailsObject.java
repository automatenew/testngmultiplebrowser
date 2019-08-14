package dataTableObjects;

import java.util.Map;

import org.apache.log4j.Logger;

public class UserDetailsObject {
	private static Logger log = Logger.getLogger(UserDetailsObject.class);
	
	private String emailId;
	private String password;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public UserDetailsObject userInfo(Map<String, String> entry) {
		UserDetailsObject userObjects = new UserDetailsObject();
		userObjects.setEmailId(entry.get("emailId"));
		userObjects.setPassword(entry.get("password"));
	    log.info("Setting UserDetailsObject ---->userInfo()"+getEmailId()+getPassword()+userObjects);
		return userObjects;
	}
	@Override
	public String toString() {
		return "UserDetailsObject [emailId=" +emailId+ ", password=" + password + "]";
	}
}
