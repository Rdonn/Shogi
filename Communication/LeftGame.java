package Communication;

import java.io.Serializable;

public class LeftGame implements Serializable{
	private String message; 
	public LeftGame() {
		// TODO Auto-generated constructor stub
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
}
