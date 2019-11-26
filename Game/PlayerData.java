package Game;

import java.io.Serializable;

public class PlayerData implements Serializable {
	private String playerName; 
	private Long playerId; 
	
	
	public PlayerData(String playerName, Long playerId) {
		// TODO Auto-generated constructor stub
		
		this.playerId = playerId; 
		this.playerName = playerName; 
		
	}
	
	public Long getPlayerId() {
		return playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
}
