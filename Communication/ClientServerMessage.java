package Communication;

import java.io.Serializable;

import Communication.ServerCommunication.PlayerLoginData;

/***
 * Used for sending messages from client to server and vice versa.
 * @author James
 *
 */
public class ClientServerMessage implements Serializable {
	
	private String message; 
	private PlayerLoginData playerLoginData;
	
	public void setPlayerLoginData(PlayerLoginData data) {
		this.playerLoginData = data;
	}
	public String getMessage() {
		return message;
	}
	public Object getPlayerLogin() {
		return this.playerLoginData;
	}
	
	
	//set the data [these are helper functions so that the intellisense can go ahead and take care of autocomplete for you]
	
	public void setMessageNEW_ACCOUNT() {
		this.message = "NEW_ACCOUNT"; 
	}
	public void setMessageNEW_ACCOUNT_SUCCESS() {
		this.message = "NEW_ACCOUNT_SUCCESS"; 
	}
	public void setMessageNEW_ACCOUNT_FAILURE() {
		this.message = "NEW_ACCOUNT_FAILURE"; 
	}
	public void setMessageNEW_GAME_ROOM() {
		this.message = "NEW_GAME_ROOM"; 
	}
	public void setMessageREQUEST_GAME_DATA() {
		this.message = "REQUEST_GAME_DATA"; 
	}
	public void setMessageREQUEST_GAME_ROOMS() {
		this.message = "REQUEST_GAME_ROOMS"; 
	}
	public void setMessageLEFT_GAME() {
		this.message = "LEFT_GAME"; 
	}
	public void setMessageLOGIN_SUCCESS() {
		this.message = "LOGIN_SUCCESS"; 
	}
	public void setMessageLOGIN_FAILURE() {
		this.message = "LOGIN_FAILURE"; 
	}
	public void setMessageGAME_DATA() {
		this.message = "GAME_DATA"; 
	}
	public void setMessageLOGIN() {
		this.message = "LOGIN"; 
	}
	
	
	
}
