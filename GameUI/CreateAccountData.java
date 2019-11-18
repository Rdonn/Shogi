package GameUI;

import java.io.Serializable;

public class CreateAccountData implements Serializable{
	private String username; 
	private String password; 
	
	
	public CreateAccountData(String username, String password) {
		this.password = password; 
		this.username = username; 
	}
	public String getPassword() {
		return password;
	}
	public String getUsername() {
		return username;
	}

}
