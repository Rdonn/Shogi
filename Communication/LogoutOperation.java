package Communication;

import java.io.Serializable;

import Game.PlayerData;

public class LogoutOperation implements Serializable{
	PlayerData playerData; 
	public LogoutOperation(PlayerData playerData) {
		this.playerData = playerData; 
	}
	public PlayerData getPlayerData() {
		return playerData;
	}
	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}
}
