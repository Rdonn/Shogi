package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Communication.ClientCommunication.GameClientConnection;
import Communication.ServerCommunication.PlayerLoginData;

public class LoginController implements ActionListener{

	LoginPanel loginPanel; 
	GameGUI view; 
	GameClientConnection gameClientConnection; 
	public LoginController(LoginPanel loginPanel, GameGUI view, GameClientConnection gameClientConnection) {
		// TODO Auto-generated constructor stub
		this.gameClientConnection = gameClientConnection; 
		this.gameClientConnection.setLoginController(this);
		this.loginPanel = loginPanel; 
		this.view = view; 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton actionButton = (JButton)arg0.getSource(); 
			if (actionButton.getName().equals("Submit")) {
			   //later logic can be added here... for the time being, we will rotate back to the initial panel so i dont have to restart for testing
				String username = this.loginPanel.getUsernameField().getText(); 
				String password = new String(this.loginPanel.getPasswordField().getPassword());
				if (username.length() < 6 || password.length() < 6) {
					this.setError("Both fields are, by default, required to be longer than 6 characters");
					return; 
				}
				
				//need to contact the communication object HERE
				
				this.gameClientConnection.sendPlayerLoginData(new PlayerLoginData(username, password));
			}
			else if(actionButton.getName().equals("Cancel")) {
				setError(" ");
				this.view.shuffleToInitial();
			}
		}
		
	}
	
	//call this if there is a login failure
	public void loginFailure(String error) {
		this.setError(error);
	}
	
	
	private void setError(String error) {
		//if you try to add a blank error, it will mess up my formatting
		if (error.contentEquals("")) {
			error = " "; 
		}
		this.loginPanel.getErrorLabel().setText(error); 
		

	}

	//call this is there is a login success
	public void loginSuccess() {
		this.view.shuffleSelectCreateGameOrSelectGamePanel();
	}
}
