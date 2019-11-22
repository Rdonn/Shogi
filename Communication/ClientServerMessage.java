package Communication;

import java.io.Serializable;

/***
 * Used for sending messages from client to server and vice versa.
 * @author James
 *
 */
public enum ClientServerMessage implements Serializable {
	REQUEST_GAME_DATA, //The client requests data for a Game
	REQUEST_GAME_ROOMS, //The client asks the server for the list of current games
	LEFT_GAME, //The client informs the server that a player left the game
	LOGIN_SUCCESS,
	LOGIN_FAILURE,
	GAME_DATA,
	LOGIN,
	NEW_ACCOUNT,
	NEW_ACCOUNT_SUCCESS,
	NEW_ACCOUNT_FAILURE,
	NEW_GAME_ROOM;
	
	
	private Serializable data;
	
	public void setData(Serializable data) {
		this.data = data;
	}
	
	public Serializable getData() {
		return data;
	}
	
}
