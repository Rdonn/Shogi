package Communication;

import java.io.Serializable;

public class NewGameRoomFailure implements Serializable {

	private String message; 
	public NewGameRoomFailure(String message) {
		// TODO Auto-generated constructor stub
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
}
