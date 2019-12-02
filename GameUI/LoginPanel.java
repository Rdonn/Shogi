package GameUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Communication.ClientCommunication.GameClientConnection;

public class LoginPanel extends JPanel{
	private JLabel errorLabel; 
	private JLabel title; 
	private JLabel usernameLabel; 
	private JLabel passwordLabel;
	private JTextField usernameField; 
	private JPasswordField passwordField;
	private JButton submitButton; 
	private JButton cancelButton; 
	
	
	public LoginPanel(GameGUI gameGUI) {
		this.errorLabel = new JLabel(" "); 
		this.errorLabel.setForeground(Color.RED);
		this.title = new JLabel("Enter your login information");
		this.usernameLabel = new JLabel("Username"); 
		this.passwordLabel = new JLabel("Password");
		this.usernameField = new JTextField(10);
		this.passwordField = new JPasswordField(10); 
		this.submitButton = new JButton("Submit");
		this.cancelButton = new JButton("Cancel"); 
		
		//set up the buttons
		LoginController loginController = new LoginController(this, gameGUI); 
		this.submitButton.setName("Submit");
		this.cancelButton.setName("Cancel");
		this.submitButton.addActionListener(loginController);
		this.cancelButton.addActionListener(loginController);
		
		
		
		//again, we need to make sure each of the components are wrapped in a panel, as the formatting will be off otherwise
		
		JPanel titleHolder = new JPanel(); 
		titleHolder.add(this.title); 
		
		JPanel usernameLabelHolder = new JPanel(); 
		usernameLabelHolder.add(this.usernameLabel); 
		
		JPanel passwordLabelHolder = new JPanel(); 
		passwordLabelHolder.add(this.passwordLabel); 
		
		JPanel usernameFieldHolder = new JPanel();
		usernameFieldHolder.add(this.usernameField); 
		
		JPanel passwordFieldHolder = new JPanel(); 
		passwordFieldHolder.add(this.passwordField); 
		
		JPanel submitButtonHolder = new JPanel(); 
		submitButtonHolder.add(this.submitButton); 
		
		JPanel cancelButtonHolder = new JPanel(); 
		cancelButtonHolder.add(this.cancelButton); 
		
		
		//using the same generic format as earlier...
		//build them by section
		
		//starting with the label section
		
		JPanel titleSection = new JPanel(); 
		titleSection.add(titleHolder); 
		
		//next the fields we are concerned with
		
		JPanel fieldBox = new JPanel(new GridLayout(2,2));
		fieldBox.add(usernameLabelHolder); 
		fieldBox.add(usernameFieldHolder); 
		fieldBox.add(passwordLabelHolder); 
		fieldBox.add(passwordFieldHolder); 
		
		//now holde the buttons
		
		JPanel buttonGrid = new JPanel(new GridLayout(1,2)); 
		buttonGrid.add(submitButtonHolder); 
		buttonGrid.add(cancelButtonHolder); 
		
		
		//now we need to stack them 
		
		JPanel overallStack = new JPanel(); 
		overallStack.setLayout(new BoxLayout(overallStack, BoxLayout.PAGE_AXIS));
		overallStack.add(this.errorLabel); 
		overallStack.add(titleSection); 
		
		//BIG SHOCKER HERE!!! IT IS MESSING UP THE FORMATTING EVEN MORE
		//need to add more containers
		JPanel holder1 = new JPanel(); 
		JPanel holder2 = new JPanel(); 
		holder1.add(fieldBox); 
		holder2.add(buttonGrid); 
		overallStack.add(holder1); 
		overallStack.add(holder2); 
		
		this.add(overallStack); 
		
	}
	
	public JTextField getUsernameField() {
		return usernameField;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	public JLabel getErrorLabel() {
		return errorLabel;
	}
	
}
