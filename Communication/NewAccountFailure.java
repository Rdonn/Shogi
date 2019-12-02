package Communication;

import java.io.Serializable;

public class NewAccountFailure implements Serializable{
	String message; 
	public NewAccountFailure(String message) {
		// TODO Auto-generated constructor stub
		this.message = message; 
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
