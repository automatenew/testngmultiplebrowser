package dataTableObjects;

import java.util.Collections;
import java.util.List;

public class UserDtls {


	private List<UserDetailsObject> userDtls;
	
	public UserDtls() {}

	public UserDtls(List<UserDetailsObject> userDtls) {
		this.userDtls = userDtls;
	}
	

	public List<UserDetailsObject> getLectures() {
		return Collections.unmodifiableList(userDtls);
	}
	
	public boolean addLecture(UserDetailsObject users) {
		return userDtls.add(users);
	}
	
	@Override
	public String toString() {
		return "UserDtls [userDtls=" + userDtls + "]";
	}
}
