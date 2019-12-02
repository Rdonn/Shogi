package Communication;

import java.io.Serializable;

public class LoginFailure implements Serializable{
	String message; 
	
	public LoginFailure(String message) {
		// TODO Auto-generated constructor stub
		this.message = message; 
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
