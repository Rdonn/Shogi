package GameUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Board;
import Game.Game;
import Game.Piece;
import Game.PlayerData;

public class GameController implements ActionListener {
	private GamePanel referencePanel; 
	private Game game; 
	private String firstClick;
	private String secondClick; 
	private GameGUI view; 
	public GameController(JPanel gamePanel, GameGUI view) {
		// TODO Auto-generated constructor stub
		this.view = view; 
		this.referencePanel = (GamePanel)gamePanel; 
		GameGUI.getClientConnection().setGameController(this);
		try {
			this.game = new Game();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() instanceof PieceButton) {
			
			PieceButton pieceButton = (PieceButton) arg0.getSource(); 
			//prevent players from touching other players piece. 
			int[] coordinatesCheckForNull = this.parseCoordinates(pieceButton.getName()); 
			if(this.game.getBoard().getGameBoard()[coordinatesCheckForNull[0]][coordinatesCheckForNull[1]] != null && this.firstClick == null) {
				if(this.playerOneOrTwo(this.game) == 1) {
					
				String playerOneAlias = this.game.getPlayerOneAlias(); 
				int[] coordinates = this.parseCoordinates(pieceButton.getName()); 
				if (!this.game.getBoard().getGameBoard()[coordinates[0]][coordinates[1]].getDirection().equals(playerOneAlias)) {
					return; 
				}
			}
			else if(this.playerOneOrTwo(this.game) == 2) {
				String playerTwoAlias = this.game.getPlayerTwoAlias(); 
				int[] coordinates = this.parseCoordinates(pieceButton.getName());
				if(!this.game.getBoard().getGameBoard()[coordinates[0]][coordinates[1]].getDirection().contentEquals(playerTwoAlias)) {
					return; 
				}
			}
			}
			
			//end piece logic
			
			
			if (this.firstClick == null && !this.checkIfSpaceHasPiece(pieceButton.getName())) {
				this.referencePanel.setError("Must Select a Piece");
				return; 
			}
			else {
				this.referencePanel.clearError();
			}
			if (this.firstClick != null && this.secondClick != null) {
				PieceButton firstButton = this.referencePanel.getButtonMap().get(this.firstClick); 
				PieceButton secondButton = this.referencePanel.getButtonMap().get(this.secondClick);
				this.firstClick = null; 
				this.secondClick = null; 
				firstButton.setClicked(false);
				secondButton.setClicked(false);
				
				firstButton.repaint();
				secondButton.repaint();
				
			}
			else if(this.firstClick == null) {
				this.firstClick = pieceButton.getName(); 
				pieceButton.setClicked(true);
				pieceButton.repaint();
			}
			else if(this.firstClick.equals(pieceButton.getName())) {
				this.firstClick = null; 
				pieceButton.setClicked(false);
				pieceButton.repaint(); 
			}
			else if(this.secondClick == null) {
				this.secondClick = pieceButton.getName(); 
				pieceButton.setClicked(true);
				pieceButton.repaint();
			}
			else if(this.secondClick.equals(pieceButton.getName())) {
				this.secondClick = null; 
				pieceButton.setClicked(false); 
				pieceButton.repaint();
			}
		}
		else {
			
			if (arg0.getSource() instanceof JButton) {
				JButton buttonClicked = (JButton) arg0.getSource(); 
				if (buttonClicked.getName().equals("Take Turn")) {
					if (this.firstClick != null && this.secondClick != null) {
						System.out.println("moving");
						int[] firstClickedCoordinates = this.parseCoordinates(this.firstClick); 
						int[] secondClickedCoordinates = this.parseCoordinates(this.secondClick); 
						Piece temp = this.game.getBoard().getGameBoard()[firstClickedCoordinates[0]][firstClickedCoordinates[1]]; 
						this.game.getBoard().getGameBoard()[secondClickedCoordinates[0]][secondClickedCoordinates[1]] = temp; 
						this.game.getBoard().getGameBoard()[firstClickedCoordinates[0]][firstClickedCoordinates[1]] = null; 
						
						
						this.killPosition(this.firstClick);
						this.killPosition(this.secondClick);
						this.firstClick = null; 
						this.secondClick = null; 
						this.game.setMoving(true);
						this.game.setPromoting(false);
						//build the coordinates
						int[][] coordinates = {firstClickedCoordinates, secondClickedCoordinates}; 
						this.game.setMoveSet(coordinates);
						GameGUI.getClientConnection().sendGameForVerification(this.game);
						this.reflect();
					}
				}
				else if(buttonClicked.getName().equals("Promote")) {
					if(this.firstClick != null && this.secondClick == null) {
						int[] coor = this.parseCoordinates(this.firstClick); 
						this.game.setPromoting(true);
						this.game.setMoving(false);
						this.game.setPromotionLocation(coor);
						this.killPosition(this.firstClick);
						this.firstClick=null; 
						GameGUI.getClientConnection().sendGameForVerification(this.game);
						
					}
				}
				else if(buttonClicked.getName().equals("Take Back Turn")) {
					this.killPosition(this.firstClick);
					this.killPosition(this.secondClick);
					this.firstClick = null; 
					this.secondClick = null;
					
				}
				else if(buttonClicked.getName().equals("Forfeit")){
					GameGUI.getClientConnection().sendForfeit(); 
				}
				else if(buttonClicked.getName().equals("Leave")) {
					this.view.shuffleSelectCreateGameOrSelectGamePanel();
				}
				else if(buttonClicked.getName().equals("Surrender")) {
					this.view.shuffleSelectCreateGameOrSelectGamePanel();
				}
			}
		}
		
		
	}
	//so... if it is not someones turn kill functionality (besides forfeit)
	public void disable() {
		this.referencePanel.getTakeBackTurn().setEnabled(false);
		this.referencePanel.getTakeTurn().setEnabled(false);
		this.referencePanel.getPromoteButton().setEnabled(false);
		for (String key : this.referencePanel.getButtonMap().keySet()) {
			this.referencePanel.getButtonMap().get(key).setEnabled(false);
		}
	}
	
	public void enable() {
		this.referencePanel.getTakeBackTurn().setEnabled(true);
		this.referencePanel.getTakeTurn().setEnabled(true);
		this.referencePanel.getPromoteButton().setEnabled(true);
		for (String key : this.referencePanel.getButtonMap().keySet()) {
			this.referencePanel.getButtonMap().get(key).setEnabled(true);
		}
	}
	
	public void setTitle(String playerData, String playerData2) {
		this.referencePanel.getTitle().setText(String.format("you are player %d: %s vs. %s",this.playerOneOrTwo(this.game), playerData, playerData2)); 
	}
	
	public void thisUserForfeited() {
		this.referencePanel.getTitle().setForeground(Color.red);
		this.referencePanel.getTitle().setText("You quit... loser");
		this.referencePanel.getForfeit().setText("To Game Selection");
		this.referencePanel.getForfeit().setName("Surrender");
	}
	
	public void setTitle(String title) {
		this.referencePanel.getTitle().setForeground(Color.green);
		this.referencePanel.getTitle().setText(title);
	}
	
	public void otherUserForfeiter() {
		this.referencePanel.getForfeit().setText("Leave");
		this.referencePanel.getForfeit().setName("Leave");
	}
	public void setGame(Game game) {
		this.game = game;
		this.reflect();
	}
	
	private int[] parseCoordinates(String coordinates) {
		String[] holder = coordinates.replaceAll("[()]", "").split(","); 
		int[] intHolder = {Integer.parseInt(holder[0]), Integer.parseInt(holder[1])}; 
		return intHolder; 
	}
	
	private void killPosition(String click) {
		if (this.referencePanel.getButtonMap().containsKey(click)) {
			this.referencePanel.getButtonMap().get(click).setClicked(false);
			this.referencePanel.getButtonMap().get(click).repaint();
		}
		
	}
	
	private int playerOneOrTwo(Game game) {
		if (GameGUI.getPlayerData().getPlayerName().contentEquals(game.getPlayerOne())) {
			return 1; 
		}
		else {
			return 2; 
		}
	}
	
	private boolean checkIfSpaceHasPiece(String space) {
		int[] coorHolder = parseCoordinates(space); 
		
		return this.game.getBoard().getGameBoard()[coorHolder[0]][coorHolder[1]] != null; 
	}
	
	public void reflect(){
		for (int i = 0; i < this.game.getBoard().getGameBoard().length; i++) {
			for (int j = 0; j < this.game.getBoard().getGameBoard()[i].length; j++) {
				this.referencePanel
				.getButtonMap()
				.get(String.format("(%d,%d)", i, j))
				.setPiece(this.game.getBoard().getGameBoard()[i][j]);
			}
		}
	}

}
