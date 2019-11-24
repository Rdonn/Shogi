package Game;

import java.io.Serializable;

import GameUI.GameGUI;

public class Game implements Serializable {

	private Board board; 
	private PlayerData playerOne; 
	private PlayerData playerTwo; 
	private boolean yourTurn; 
	public Game() {
		// TODO Auto-generated constructor stub
		try {
			this.board = new Board();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public Board getBoard() {
		return board;
	}
	
	//probably should never be called
	public void setBoard(Board board) {
		this.board = board;
	}
	
	//probably will never be called
	public void setPlayerData(PlayerData playerData) {
	}
	public void setYourTurn(boolean yourTurn) {
		this.yourTurn = yourTurn;
	}
}
