package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import Communication.ServerCommunication.GameRoomData;

public class SelectGameController implements ActionListener{

	private SelectGamePanel selectGamePanel; 
	private GameGUI view; 
	

	private List<GameRoomData> gameRooms = new ArrayList<>();
	
	public SelectGameController(SelectGamePanel selectGamePanel, GameGUI view) {
		// TODO Auto-generated constructor stub
		this.selectGamePanel = selectGamePanel; 
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() instanceof JButton) {
			JButton referenceButton = (JButton) arg0.getSource(); 
			if (referenceButton.getName().contentEquals("Select Game")) {
				this.view.shuffleToGame();
			}
			else if (referenceButton.getName().contentEquals("Cancel")){
				this.view.shuffleSelectCreateGameOrSelectGamePanel();
			}
		}
		
	}

	public void setGameRooms(GameRoomData[] gameRooms) {
		this.gameRooms = Arrays.asList(gameRooms);
	}
	
}
