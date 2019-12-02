package GameUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Communication.ClientCommunication.GameClientConnection;

public class CreateGamePanel extends JPanel{
	private JLabel title; 
	private JTextField gameNameField;
	private JLabel gameNameTitle; 
	private JButton submit; 
	private JButton cancel; 
	private JButton logoutButton; 
	
	public CreateGamePanel(GameGUI gameGUI) {
		this.title = new JLabel("Create game"); 
		this.gameNameField = new JTextField(10);
		this.gameNameTitle = new JLabel("Game Name"); 
		this.submit = new JButton("Submit"); 
		this.cancel = new JButton("Cancel");
		this.submit.setName("Submit");
		this.cancel.setName("Cancel");
		this.logoutButton = new JButton("Logout"); 
		this.logoutButton.setName("Logout");
		
		//set the controller 
		CreateGameController createGameController = new CreateGameController(this, gameGUI);
		
		this.submit.addActionListener(createGameController);
		this.cancel.addActionListener(createGameController);
		this.logoutButton.addActionListener(createGameController);
		
		
		//set up the first layer
		JPanel titleHolder = new JPanel(); 
		titleHolder.add(this.title); 
		
		
		//add some white space
		
		
		//set up the second layer
		JPanel userDataHolder = new JPanel(new GridLayout(1,2));
		
		JPanel userTitleHolder = new JPanel(); 
		JPanel userTextFieldHolder = new JPanel(); 
		userTitleHolder.add(this.gameNameTitle); 
		userTextFieldHolder.add(this.gameNameField); 
		
		userDataHolder.add(userTitleHolder); 
		userDataHolder.add(userTextFieldHolder); 
		
		
		//add a little more white space
		
		
		//set up the third layer
		JPanel buttonHolder = new JPanel(new GridLayout(1,3)); 
		JPanel leftHolder = new JPanel(); 
		JPanel centerButtonHolder = new JPanel(); 
		JPanel rightHolder = new JPanel(); 
		leftHolder.add(this.cancel); 
		centerButtonHolder.add(this.logoutButton); 
		rightHolder.add(this.submit); 
		
		
		
		buttonHolder.add(leftHolder); 
		buttonHolder.add(rightHolder); 
		buttonHolder.add(centerButtonHolder); 
		
		JPanel panel = new JPanel(); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(titleHolder); 
		panel.add(Box.createRigidArea(new Dimension(5,0))); 
		panel.add(userDataHolder); 
		panel.add(Box.createRigidArea(new Dimension(0,5))); 
		panel.add(buttonHolder); 
		this.add(panel); 
		
		
		
	}	
	
	public JLabel getTitle() {
		return title;
	}
	
	public void setDefaultTitle() {
		this.title.setText("Create game");
	}
	
	public JTextField getGameNameField() {
		return gameNameField;
	}
}
