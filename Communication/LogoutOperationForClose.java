package Communication;

import java.io.Serializable;

import Game.PlayerData;

public class LogoutOperationForClose implements Serializable{

	private PlayerData playerData; 
	public LogoutOperationForClose(PlayerData playerData) {
		// TODO Auto-generated constructor stub
		this.playerData = playerData; 
	}
	public PlayerData getPlayerData() {
		return playerData;
	}
	public void setPlayerData(PlayerData playerData) {
		this.playerData = playerData;
	}
}
