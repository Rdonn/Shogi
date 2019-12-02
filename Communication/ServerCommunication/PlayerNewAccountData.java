package Communication.ServerCommunication;

import java.io.Serializable;

public class PlayerNewAccountData implements Serializable{
	private String username; 
	private String password; 
	
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
