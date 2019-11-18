package Communication.ServerCommunication;

import java.io.Serializable;

public class PlayerLoginData implements Serializable{
	private String userName;
	private String password; 
	
	public PlayerLoginData(String userName, String password) {
		this.userName = userName; 
		this.password = password; 
		// TODO Auto-generated constructor stub
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
