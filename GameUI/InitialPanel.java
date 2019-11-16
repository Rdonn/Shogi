package GameUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitialPanel extends JPanel{
	private JLabel title; 
	private JButton loginButton; 
	private JButton createAccountButton; 
	
	public InitialPanel(GameGUI gameGUI) {
		this.title = new JLabel("Welcome to Shogi! Select an option"); 
		this.loginButton = new JButton("Login"); 
		this.createAccountButton = new JButton("Create Account"); 
		
		//set up some buttons names to make the controller functionality more explicit
		this.loginButton.setName("Login");
		this.createAccountButton.setName("Create Account");
		
		InitialController initialController = new InitialController(this, gameGUI); 
		
		this.loginButton.addActionListener(initialController);
		this.createAccountButton.addActionListener(initialController);
		
		//need to keep them all in their individual container to avoid any bullshit 
		
		JPanel holders[] = new JPanel[3];
		for (int i = 0; i < holders.length; i++) {
			holders[i] = new JPanel(); 
		}
		
		holders[0].add(this.title); 
		holders[1].add(this.loginButton); 
		holders[2].add(this.createAccountButton); 
		
		//need to create a box layout to hold these elements
		
		JPanel containerForBoxLayout = new JPanel(); 
		containerForBoxLayout.setLayout(new BoxLayout(containerForBoxLayout, BoxLayout.PAGE_AXIS));
		
		containerForBoxLayout.add(holders[0]); 
		containerForBoxLayout.add(holders[1]); 
		containerForBoxLayout.add(holders[2]);
		
		this.add(containerForBoxLayout); 
		
		
	}
}
