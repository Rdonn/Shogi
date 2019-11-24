package Communication;

public class GetMessagesInstance {

	//retrieve the strings for comparison!
	
	public static String REQUEST_GAME_DATA() { //The client requests data for a Game
		return "REQUEST_GAME_DATA"; 
	}
	public static String REQUEST_GAME_ROOMS() { //The client asks the server for the list of current games
		return "REQUEST_GAME_ROOMS"; 
	}
	public static String LEFT_GAME() {//The client informs the server that a player left the game
		return "LEFT_GAME"; 
	}
	public static String LOGIN_SUCCESS() {
		return "LOGIN_SUCCESS"; 
	}
	public static String LOGIN_FAILURE() {
		return "LOGIN_FAILURE";
	}
	public static String GAME_DATA() {
		return "GAME_DATA"; 
				
	}
	public static String LOGIN() {
		return "LOGIN"; 
	}
	public static String NEW_ACCOUNT()
	{
		return "NEW_ACCOUNT"; 
	}
	public static String NEW_ACCOUNT_SUCCESS() {
		return "NEW_ACCOUNT_SUCCESS"; 
	}
	public static String NEW_ACCOUNT_FAILURE() {
		return "NEW_ACCOUNT_FAILURE"; 
	}
	public static String NEW_GAME_ROOM() {
		return "NEW_GAME_ROOM"; 
	}
	
	
}
