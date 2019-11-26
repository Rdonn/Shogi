package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Communication.NewGameRoom;
import Communication.ClientCommunication.GameClientConnection;
import Communication.ServerCommunication.GameRoomData;

public class CreateGameController implements ActionListener{

	private CreateGamePanel createGamePanel;
	private GameGUI view; 
	private GameClientConnection gameClientConnection; 
	public CreateGameController(CreateGamePanel createGamePanel, GameGUI gameGUI) {
		// TODO Auto-generated constructor stub
		GameGUI.getClientConnection().setCreateGameController(this);
		this.createGamePanel = createGamePanel; 
		this.view = gameGUI; 
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton actionButton = (JButton) arg0.getSource(); 
			if(actionButton.getName().equals("Submit")) {
				String gameName = this.createGamePanel.getGameNameField().getText(); 
				if (gameName.length() > 0) {
					NewGameRoom newGameRoom = new NewGameRoom(gameName); 
					GameGUI.getClientConnection().sendNewGameRoom(newGameRoom);
				}
			}
			else if(actionButton.getName().equals("Cancel")) {
				this.view.shuffleSelectCreateGameOrSelectGamePanel();
			}
		}
		
	}
	
	public void createGameFailure(String errorMsg) {
		
		this.createGamePanel.getTitle().setText(errorMsg);
	}
	
	public void createGameSuccess() {
		this.createGamePanel.setDefaultTitle();
		this.view.shuffleToGame();
	}

}
