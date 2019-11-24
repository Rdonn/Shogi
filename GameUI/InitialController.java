package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class InitialController implements ActionListener{
	private InitialPanel initialPanel; 
	private GameGUI view; 
	public InitialController(InitialPanel initialPanel, GameGUI gameGUI) {
		// TODO Auto-generated constructor stub
		
		this.initialPanel = initialPanel; 
		this.view = gameGUI; 
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton actionButton = (JButton) arg0.getSource(); 
			if (actionButton.getName().equals("Login")) {
				this.view.shuffleToLogin();
			}
			else if(actionButton.getName().equals("Create Account")) {
				this.view.shuffleToCreateAccount();
			}
		}
		
	}

}
