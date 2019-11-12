package Game;

import java.io.Serializable;

public class PlayerData implements Serializable {
	private String playerName; 
	private String playerId; 
	
	
	public PlayerData(String playerName, String playerId) {
		// TODO Auto-generated constructor stub
		
		this.playerId = playerId; 
		this.playerName = playerName; 
		
	}
	
	public String getPlayerId() {
		return playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
}
