package GameUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Board;
import Game.Piece;

//buttons will be held in a map with their corresponding coordinates attached
public class GamePanel extends JPanel{
	HashMap<String, PieceButton> buttonMap; 
	Board board; 
	JButton takeTurn; 
	JButton takeBackTurn; 
	JButton getFromJail; 
	JButton forfeit; 
	JLabel errorTitle; 
	JButton promoteButton; 
	JComboBox<String> jailBox; 
	
	private JLabel title; 
	public GamePanel(GameGUI gameGUI) {
		try {
			this.board = new Board();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.setPreferredSize(new Dimension(700,550));
		JPanel board = new JPanel(new GridLayout(9,9)); 
		GameController controller = new GameController(this, gameGUI); 
		board.setPreferredSize(new Dimension(500,500));
		buttonMap = new HashMap<String, PieceButton>(); 
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				PieceButton tempReferenceToButton = new PieceButton(); 
				tempReferenceToButton.addActionListener(controller);
				tempReferenceToButton.setName(String.format("(%d,%d)", i, j));
				tempReferenceToButton.setBackground(Color.BLACK);
				board.add(tempReferenceToButton); 
				buttonMap.put(String.format("(%d,%d)", i, j), tempReferenceToButton); 
			}
		}
		this.jailBox = new JComboBox<String>(); 
		//now set up the board
		controller.reflect();
		
		
		
		//now set up the other shit
		//NEED TO FIGURE OUT HOW WE ARE GOING TO PASS AROUND PLAYER DATA
		
		this.title = new JLabel(" ");
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(this.title); 
		
		//need to add some stuff to the board
	   this.takeTurn = new JButton("Take Turn"); 
	   this.takeBackTurn = new JButton("Take Back Turn"); 
	   this.forfeit = new JButton("Forfeit");
	   this.promoteButton = new JButton("Promote"); 
	   this.getFromJail = new JButton("Unjail"); 
	   this.getFromJail.setName("Unjail");
	   this.getFromJail.addActionListener(controller);
	   this.promoteButton.addActionListener(controller);
	   this.takeTurn.addActionListener(controller);
	   this.takeBackTurn.addActionListener(controller);
	   this.forfeit.addActionListener(controller);
	   
	   this.promoteButton.setName("Promote");
	   this.takeTurn.setName("Take Turn");
	   this.takeBackTurn.setName("Take Back Turn");
	   this.forfeit.setName("Forfeit");
	   
	   
	   JPanel holders[] = new JPanel[6]; 
	   for (int i = 0; i < holders.length; i++) {
		holders[i] = new JPanel(); 
		}
	   holders[0].add(this.takeTurn); 
	   holders[1].add(this.promoteButton); 
	   holders[2].add(this.takeBackTurn); 
	   holders[3].add(this.getFromJail); 
	   holders[4].add(this.forfeit);
	   holders[5].add(this.jailBox); 
	   
	   
	   
	   
	   JPanel mainPanel = new JPanel(new FlowLayout());
	   
	   //need to add an error title
	   this.errorTitle = new JLabel(); 
	   this.errorTitle.setForeground(Color.RED);
	   JPanel buttonPanel = new JPanel(new GridLayout(7,1)); 
	   buttonPanel.add(this.errorTitle); 
	   for (JPanel jPanel : holders) {
		buttonPanel.add(jPanel); }
	  
		
		mainPanel.add(buttonPanel); 
		mainPanel.add(board); 
		
		this.add(mainPanel); 
		this.disable();
		
		
		
	}
	
	public void disable() {
		this.takeBackTurn.setEnabled(false);
		this.takeTurn.setEnabled(false);
		this.promoteButton.setEnabled(false);
		this.getFromJail.setEnabled(false);
		for (String pieceButton : this.buttonMap.keySet()) {
			this.buttonMap.get(pieceButton).setEnabled(false);
		}
	}
	
	
	public JButton getPromoteButton() {
		return promoteButton;
	}
	public JLabel getTitle() {
		return title;
	}
	public JButton getForfeit() {
		return forfeit;
	}
	public JButton getTakeBackTurn() {
		return takeBackTurn;
	}
	public JButton getTakeTurn() {
		return takeTurn;
	}
	
	public void setError(String error) {
		this.errorTitle.setText(error);
	}
	
	public JComboBox<String> getJailBox() {
		return jailBox;
	}
	
	public void clearError() {
		this.errorTitle.setText("");
	}
	
	//any modification can be done 
	public HashMap<String, PieceButton> getButtonMap() {
		return buttonMap;
	}
}
