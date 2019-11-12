package GameUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

import Game.Piece;

public class PieceButton extends JButton{
	private Piece piece;
	private boolean clicked = false; 
	
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		
		if (this.clicked) {
			this.setBackground(Color.RED);
		}
		else {
			this.setBackground(Color.BLACK);
		}
		
		super.paint(arg0);
		if (this.piece != null) {
			arg0.drawImage(IconRetriever.getIcon(piece, this.getWidth(), this.getHeight()), 0, 0, this);
		}
		
		
		
		
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	private boolean getClicked() {
		return this.getClicked(); 

	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
}
