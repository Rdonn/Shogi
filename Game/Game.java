package Game;

import java.io.Serializable;
import java.util.ArrayList;

import GameUI.GameGUI;

public class Game implements Serializable {
	private ArrayList<Piece> pieces; 
	private Board board; 
	private String playerOne; 
	private String playerTwo; 
	private String playerOneAlias;
	private String playerTwoAlias; 
	private String whoIsMoving; 
	private boolean yourTurn; 
	private boolean initialResponse; 
	private boolean isPromoting; 
	private boolean isMoving; 
	private boolean isGettingOutOfJail; 
	private int[][] pieceMovedLocation; 
	private int[] promotionLocation; 
	private String errorMessage; 
	public Game() {
		// TODO Auto-generated constructor stub
		try {
			this.board = new Board();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void setPromotionLocation(int[] promotionLocation) {
		this.promotionLocation = promotionLocation;
	}
	public int[] getPromotionLocation() {
		return promotionLocation;
	}
	public String getWhoIsMoving() {
		return whoIsMoving;
	}
	public void setWhoIsMoving(String whoIsMoving) {
		this.whoIsMoving = whoIsMoving;
	}
	public void setMoveSet(int[][] moveSet) {
		this.pieceMovedLocation = moveSet;
	}
	public int[][] getMoveSet() {
		return pieceMovedLocation;
	}
	public Board getBoard() {
		return board;
	}
	
	public boolean isMoving() {
		return isMoving;
	}
	
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public boolean isPromoting() {
		return isPromoting;
	}
	
	public void setPromoting(boolean isPromoting) {
		this.isPromoting = isPromoting;
	}
	
	//probably should never be called
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setInitialResponse(boolean initialResponse) {
		this.initialResponse = initialResponse;
	}
	public boolean isInitialResponse() {
		return initialResponse;
	}
	//probably will never be called
	public boolean isYourTurn() {
		return yourTurn;
	}
	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}
	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}
	public String getPlayerOne() {
		return playerOne;
	}
	public String getPlayerTwo() {
		return playerTwo;
	}
	
	public void setYourTurn(boolean yourTurn) {
		this.yourTurn = yourTurn;
	}
	
	public void setPlayerOneAlias(String playerOneAlias) {
		this.playerOneAlias = playerOneAlias;
	}
	public String getPlayerOneAlias() {
		return playerOneAlias;
	}
	public void setPlayerTwoAlias(String playerTwoAlias) {
		this.playerTwoAlias = playerTwoAlias;
	}
	public String getPlayerTwoAlias() {
		return playerTwoAlias;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
}
