package Communication.ServerCommunication;

import java.io.Serializable;

import Game.PlayerData;

//this will just be used for communication between the game room list
public class GameRoomData implements Serializable{
	String name;
	String playerName; 
	public GameRoomData(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		
	}
	
	public void setPlayerData(String playerName) {
		this.playerName = playerName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
