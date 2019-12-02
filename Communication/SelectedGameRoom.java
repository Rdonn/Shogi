package Communication;

import java.io.Serializable;

import Communication.ServerCommunication.GameRoomData;
import Game.PlayerData;

public class SelectedGameRoom implements Serializable{
	GameRoomData gameRoomData; 
	public SelectedGameRoom(GameRoomData gameRoomData) {
		// TODO Auto-generated constructor stub
		this.gameRoomData = gameRoomData; 
	}
	public void setGameRoomData(GameRoomData gameRoomData) {
		this.gameRoomData = gameRoomData;
	}
	public GameRoomData getGameRoomData() {
		return gameRoomData;
	}
	
}
