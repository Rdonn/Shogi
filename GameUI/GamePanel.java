package GameUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	JButton forfeit; 
	JLabel errorTitle; 
	private JLabel title; 
	public GamePanel() {
		try {
			this.board = new Board();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.setPreferredSize(new Dimension(700,550));
		JPanel board = new JPanel(new GridLayout(9,9)); 
		GameController controller = new GameController(this); 
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
		
		//now set up the board
		controller.reflect();
		
		
		
		//now set up the other shit
		//NEED TO FIGURE OUT HOW WE ARE GOING TO PASS AROUND PLAYER DATA
		
		this.title = new JLabel(String.format("%s vs %s", "XXxshogi_monsterxXX", "69Japan_man420"));
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(this.title); 
		
		//need to add some stuff to the board
	   this.takeTurn = new JButton("Take Turn"); 
	   this.takeBackTurn = new JButton("Take Back Turn"); 
	   this.forfeit = new JButton("Forfeit");
	   
	   this.takeTurn.addActionListener(controller);
	   this.takeBackTurn.addActionListener(controller);
	   this.forfeit.addActionListener(controller);
	   
	   this.takeTurn.setName("Take Turn");
	   this.takeBackTurn.setName("Take Back Turn");
	   this.forfeit.setName("Forfeit");
	   
	   
	   JPanel holders[] = new JPanel[3]; 
	   for (int i = 0; i < holders.length; i++) {
		holders[i] = new JPanel(); 
		}
	   holders[0].add(this.takeTurn); 
	   holders[1].add(this.takeBackTurn); 
	   holders[2].add(this.forfeit);
	   
	   JPanel mainPanel = new JPanel(new FlowLayout());
	   
	   //need to add an error title
	   this.errorTitle = new JLabel(); 
	   this.errorTitle.setForeground(Color.RED);
	   JPanel buttonPanel = new JPanel(new GridLayout(4,1)); 
	   buttonPanel.add(this.errorTitle); 
	   for (JPanel jPanel : holders) {
		buttonPanel.add(jPanel); }
	  
		
		mainPanel.add(buttonPanel); 
		mainPanel.add(board); 
		
		this.add(mainPanel); 
		
		
		
		
	}
	
	public void setError(String error) {
		this.errorTitle.setText(error);
	}
	
	public void clearError() {
		this.errorTitle.setText("");
	}
	
	//any modification can be done 
	public HashMap<String, PieceButton> getButtonMap() {
		return buttonMap;
	}
}
