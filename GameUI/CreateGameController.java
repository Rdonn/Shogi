package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Communication.ClientCommunication.GameClientConnection;

public class CreateGameController implements ActionListener{

	private CreateGamePanel createGamePanel;
	private GameGUI view; 
	private GameClientConnection gameClientConnection; 
	public CreateGameController(CreateGamePanel createGamePanel, GameGUI gameGUI, GameClientConnection gameClientConnection) {
		// TODO Auto-generated constructor stub
		gameClientConnection.setCreateGameController(this);
		this.createGamePanel = createGamePanel; 
		this.view = gameGUI; 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton actionButton = (JButton) arg0.getSource(); 
			if(actionButton.getName().equals("Submit")) {
				System.out.println("Coming Soon");
			}
			else if(actionButton.getName().equals("Cancel")) {
				this.view.shuffleSelectCreateGameOrSelectGamePanel();
			}
		}
		
	}

}
