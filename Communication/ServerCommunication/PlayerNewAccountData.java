package Communication.ServerCommunication;

import java.io.Serializable;

public class PlayerNewAccountData implements Serializable{
	String username; 
	String password; 
	
	public PlayerNewAccountData(String username, String password) {
		// TODO Auto-generated constructor stub
		this.username = username;  
		this.password = password; 
		
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}
	
}
