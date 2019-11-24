package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;

import javax.swing.JButton;
import javax.swing.JPanel;

import Game.Board;
import Game.Piece;

public class GameController implements ActionListener {
	private GamePanel referencePanel; 
	private Board board; 
	private String firstClick;
	private String secondClick; 
	public GameController(JPanel gamePanel) {
		// TODO Auto-generated constructor stub
		this.referencePanel = (GamePanel)gamePanel; 
		try {
			this.board = new Board();
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
				System.out.println("made it here");
				JButton buttonClicked = (JButton) arg0.getSource(); 
				if (buttonClicked.getName().equals("Take Turn")) {
					if (this.firstClick != null && this.secondClick != null) {
						System.out.println("moving");
						int[] firstClickedCoordinates = this.parseCoordinates(this.firstClick); 
						int[] secondClickedCoordinates = this.parseCoordinates(this.secondClick); 
						Piece temp = this.board.getGameBoard()[firstClickedCoordinates[0]][firstClickedCoordinates[1]]; 
						this.board.getGameBoard()[secondClickedCoordinates[0]][secondClickedCoordinates[1]] = temp; 
						this.board.getGameBoard()[firstClickedCoordinates[0]][firstClickedCoordinates[1]] = null; 
						
						
						this.killPosition(this.firstClick);
						this.killPosition(this.secondClick);
						this.firstClick = null; 
						this.secondClick = null; 
						this.reflect();
					}
				}
				else if(buttonClicked.equals("Take Back Turn")) {
					
					
				}
				else if(buttonClicked.equals("Forfeit")){
					
				}
			}
		}
		
		
	}
	
	private int[] parseCoordinates(String coordinates) {
		String[] holder = coordinates.replaceAll("[()]", "").split(","); 
		int[] intHolder = {Integer.parseInt(holder[0]), Integer.parseInt(holder[1])}; 
		return intHolder; 
	}
	
	private void killPosition(String click) {
		this.referencePanel.getButtonMap().get(click).setClicked(false);
		this.referencePanel.getButtonMap().get(click).repaint();
	}
	
	private boolean checkIfSpaceHasPiece(String space) {
		int[] coorHolder = parseCoordinates(space); 
		
		return this.board.getGameBoard()[coorHolder[0]][coorHolder[1]] != null; 
	}
	
	public void reflect(){
		for (int i = 0; i < this.board.getGameBoard().length; i++) {
			for (int j = 0; j < this.board.getGameBoard()[i].length; j++) {
				this.referencePanel
				.getButtonMap()
				.get(String.format("(%d,%d)", i, j))
				.setPiece(this.board.getGameBoard()[i][j]);
			}
		}
	}

}
