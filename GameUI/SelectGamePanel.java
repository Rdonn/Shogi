package GameUI;
import Game.PlayerData;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectGamePanel extends JPanel{
	JButton selectGameButton; 
	JLabel error; 
	JLabel title; 
	JComboBox<String> selectGameBox; 
	JButton cancelButton; 
	JButton refreshButton; 
	JButton logoutButton; 
	public SelectGamePanel(GameGUI gameGUI) {
		this.selectGameBox = new JComboBox<String>(); 
		this.selectGameButton = new JButton("Select Game"); 
		this.selectGameButton.setName("Select Game");
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.setName("Cancel");
		this.refreshButton= new JButton("Refresh"); 
		this.refreshButton.setName("Refresh");
		this.logoutButton = new JButton("Logout");
		this.logoutButton.setName("Logout");
		this.error = new JLabel();
		this.error.setForeground(Color.red);
		this.title = new JLabel("Select A Game To Begin Playing with a player"); 
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(this.error); 
		this.add(this.title); 
		
		
		JPanel selectPlayerBoxHolder = new JPanel(); 
		selectPlayerBoxHolder.add(this.selectGameBox); 
		
		JPanel buttonHolder = new JPanel(); 
		buttonHolder.add(this.selectGameButton); 
		buttonHolder.add(this.cancelButton); 
		buttonHolder.add(this.refreshButton); 
		buttonHolder.add(this.logoutButton); 
		
		
		SelectGameController selectGamePanel = new SelectGameController(this, gameGUI); 
		
		//set up the button functionality 
		this.selectGameButton.addActionListener(selectGamePanel);
		this.cancelButton.addActionListener(selectGamePanel);
		this.refreshButton.addActionListener(selectGamePanel);
		this.logoutButton.addActionListener(selectGamePanel);
		this.add(buttonHolder);
		this.add(selectPlayerBoxHolder);
		
		
	}
	public JLabel getError() {
		return error;
	}
	public JComboBox<String> getSelectGameBox() {
		return selectGameBox;
	}
	public JLabel getTitle() {
		return title;
	}
}
