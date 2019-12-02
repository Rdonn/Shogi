package Communication;

import java.io.Serializable;

import Game.Game;

public class GameData implements Serializable{
	Game game; 
	public GameData(Game game) {
		// TODO Auto-generated constructor stub
		this.game = game; 
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Game getGame() {
		return game;
	}
}
