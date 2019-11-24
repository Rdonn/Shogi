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
	public CreateGamePanel(GameGUI gameGUI, GameClientConnection gameClientConnection) {
		this.title = new JLabel("Create game"); 
		this.gameNameField = new JTextField(10);
		this.gameNameTitle = new JLabel("Game Name"); 
		this.submit = new JButton("Submit"); 
		this.cancel = new JButton("Cancel");
		this.submit.setName("Submit");
		this.cancel.setName("Cancel");
		
		//set the controller 
		CreateGameController createGameController = new CreateGameController(this, gameGUI, gameClientConnection);
		
		this.submit.addActionListener(createGameController);
		this.cancel.addActionListener(createGameController);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//set up the first layer
		JPanel titleHolder = new JPanel(); 
		titleHolder.add(this.title); 
		this.add(titleHolder); 
		
		//add some white space
		this.add(Box.createRigidArea(new Dimension(5,0))); 
		
		//set up the second layer
		JPanel userDataHolder = new JPanel(new GridLayout(1,2));
		
		JPanel userTitleHolder = new JPanel(); 
		JPanel userTextFieldHolder = new JPanel(); 
		userTitleHolder.add(this.gameNameTitle); 
		userTextFieldHolder.add(this.gameNameField); 
		
		userDataHolder.add(userTitleHolder); 
		userDataHolder.add(userTextFieldHolder); 
		this.add(userDataHolder); 
		
		//add a little more white space
		this.add(Box.createRigidArea(new Dimension(0,5)));
		
		//set up the third layer
		JPanel buttonHolder = new JPanel(new GridLayout(1,2)); 
		JPanel leftHolder = new JPanel(); 
		JPanel rightHolder = new JPanel(); 
		leftHolder.add(this.cancel); 
		rightHolder.add(this.submit); 
		
		buttonHolder.add(leftHolder); 
		buttonHolder.add(rightHolder); 
		this.add(buttonHolder); 
		
		
	}	
	
	public JTextField getGameNameField() {
		return gameNameField;
	}
}
