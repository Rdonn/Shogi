package GameUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel{
	private JLabel error; 
	private JLabel title; 
	private JLabel userNameLabel;
	private JLabel passwordLabel; 
	private JLabel reenterpasswordFieldTitle; 
	
	private JTextField usernameField; 
	private JPasswordField passwordField;
	private JPasswordField reenterPasswordField; 
	
	private JButton submitButton; 
	private JButton cancelButton; 
	
	public CreateAccountPanel(GameGUI gameGUI) {
		this.error = new JLabel(); 
		this.error.setForeground(Color.red);
		this.title = new JLabel("Create an account"); 
		this.userNameLabel = new JLabel("Username"); 
		this.passwordLabel = new JLabel("Password"); 
		this.reenterpasswordFieldTitle = new JLabel("Reenter Pass"); 
		this.usernameField = new JTextField(10); 
		this.passwordField = new JPasswordField(10); 
		this.reenterPasswordField = new JPasswordField(10); 
		this.submitButton = new JButton("Submit"); 
		this.cancelButton = new JButton("Cancel"); 
		
		this.submitButton.setName("Submit");
		this.cancelButton.setName("Cancel");
		
		
		CreateAccountController controller = new CreateAccountController(this, gameGUI); 
		
		//set up some basic behaviors for the buttons
		this.submitButton.addActionListener(controller);
		this.cancelButton.addActionListener(controller);
		
		
		
		//first, lets set up the title 
		
		JPanel titleHolder = new JPanel(); 
		titleHolder.add(this.title); 
		
		//next, we need to build the text fields stuff
		//first we need to place each in a JPanel to avoid formatting issues
		
		JPanel usernameLabelHolder = new JPanel(); 
		usernameLabelHolder.add(this.userNameLabel); 
		
		JPanel passwordLabelHolder = new JPanel() ;
		passwordLabelHolder.add(this.passwordLabel); 
		
		JPanel usernameFieldHolder = new JPanel(); 
		usernameFieldHolder.add(this.usernameField); 
		
		JPanel passwordFieldHolder = new JPanel(); 
		passwordFieldHolder.add(this.passwordField); 
		
		JPanel reenterPasswordTitleHolder = new JPanel(); 
		reenterPasswordTitleHolder.add(this.reenterpasswordFieldTitle); 
		
		JPanel  reenterPasswordFieldHolder = new JPanel(); 
		reenterPasswordFieldHolder.add(this.reenterPasswordField); 
		
		JPanel submitButtonHolder = new JPanel();
		submitButtonHolder.add(this.submitButton); 
		
		JPanel cancelButtonHolder = new JPanel(); 
		cancelButtonHolder.add(this.cancelButton); 
		
		//now we will do a 2x3 grid to hold all of the stuff
		
		JPanel fieldGrid = new JPanel(new GridLayout(3,2));
		
		fieldGrid.add(usernameLabelHolder); 
		fieldGrid.add(usernameFieldHolder); 
		
		fieldGrid.add(passwordLabelHolder); 
		fieldGrid.add(passwordFieldHolder); 
		
		fieldGrid.add(reenterPasswordTitleHolder); 
		fieldGrid.add(reenterPasswordFieldHolder); 
		
		//next, lets set up the buttons
		//normal flow layout will do... sike. of course it doesnt
		
		JPanel buttonGrid = new JPanel(new GridLayout(1, 2));
		buttonGrid.add(submitButtonHolder); 
		buttonGrid.add(cancelButtonHolder); 
		
		
		
		
		//now we need to wrap them up in a box layout 
		JPanel boxHolder = new JPanel(); 
		boxHolder.setLayout(new BoxLayout(boxHolder, BoxLayout.PAGE_AXIS));
		
		boxHolder.add(titleHolder); 
		boxHolder.add(fieldGrid); 
		boxHolder.add(buttonGrid); 
		
		this.add(boxHolder); 
		
		
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JLabel getUserNameLabel() {
		return userNameLabel;
	}

	public void setUserNameLabel(JLabel userNameLabel) {
		this.userNameLabel = userNameLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JLabel getReenterpasswordFieldTitle() {
		return reenterpasswordFieldTitle;
	}

	public void setReenterpasswordFieldTitle(JLabel reenterpasswordFieldTitle) {
		this.reenterpasswordFieldTitle = reenterpasswordFieldTitle;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}

	public void setUsernameField(JTextField usernameField) {
		this.usernameField = usernameField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JPasswordField getReenterPasswordField() {
		return reenterPasswordField;
	}

	public void setReenterPasswordField(JPasswordField reenterPasswordField) {
		this.reenterPasswordField = reenterPasswordField;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}

	public void setSubmitButton(JButton submitButton) {
		this.submitButton = submitButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	public JLabel getError() {
		return error;
	}
}
