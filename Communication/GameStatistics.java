package Communication;

import java.io.Serializable;


public class GameStatistics implements Serializable{
	String username; 
	String gamesWon; 
	String gamesLost; 
	public GameStatistics(String username, String gamesWon, String gamesLost) {
		this.username = username;
		this.gamesWon = gamesWon;
		this.gamesLost = gamesLost;
	}
	
	public GameStatistics() {
		// TODO Auto-generated constructor stub
		
	}
	public String getUsername() {
		return username;
	}
	public String getGamesWon() {
		return gamesWon;
	}
	public String getGamesLost() {
		return gamesLost;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setGamesLost(String gamesLost) {
		this.gamesLost = gamesLost;
	}
	public void setGamesWon(String gamesWon) {
		this.gamesWon = gamesWon;
	}
	
}
