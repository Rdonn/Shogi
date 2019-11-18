package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Communication.ServerCommunication.PlayerNewAccountData;

public class CreateAccountController implements ActionListener{

	private CreateAccountPanel createAccountPanelReference; 
	private GameGUI view; 
	public CreateAccountController(CreateAccountPanel createAccountPanel, GameGUI view) {
		// TODO Auto-generated constructor stub
		GameGUI.getClientConnection().setCreateAccountController(this);
		this.view = view; 
		this.createAccountPanelReference = createAccountPanel; 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton actionButton = (JButton) arg0.getSource(); 
			if (actionButton.getName().contentEquals("Submit")) {
				//wait for success
				//we will send a request through the communication which will respond by.... using the method to traverse to the login panel
				
				//lets go ahead and do some ol' logic
				String passwordOne = this.createAccountPanelReference.getPasswordField().getPassword().toString(); 
				String passwordTwo = this.createAccountPanelReference.getReenterPasswordField().getPassword().toString(); 
				String username = this.createAccountPanelReference.getUsernameField().getText(); 
				
				if (username.length() < 6) {
					this.setError("username must be longer than 6 characters");
					return; 
				}
				if (!passwordOne.equals(passwordTwo)) {
					this.setError("passwords are not equal");
					return; 
				}
				CreateAccountData createAccountData = new CreateAccountData(username, passwordOne); 
				
				//here is the create account data, get back a positive response from the server and go ahead and hit the navigate method
				//set an error if not. 
				
				//communication object
				GameGUI.getClientConnection().sendPlayerNewAccountData(new PlayerNewAccountData(username, passwordOne));
			}
			else if (actionButton.getName().contentEquals("Cancel")) {
				this.view.shuffleToInitial();
			}
		}
		
	}
	
	public void createAccountFailure(String error) {
		this.setError(error);
	}
	
	public void createAccountSuccess() {
		this.view.shuffleToLogin();
	}
	
	private void setError (String error) {
		this.createAccountPanelReference.getError().setText(error);
	}
	

}
