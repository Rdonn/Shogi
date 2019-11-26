package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;

import Communication.ServerCommunication.GameRoomData;

public class SelectGameController implements ActionListener{

	private SelectGamePanel selectGamePanel; 
	private GameGUI view; 
	

	public SelectGameController(SelectGamePanel selectGamePanel, GameGUI view) {
		// TODO Auto-generated constructor stub
		GameGUI.getClientConnection().setSelectGameController(this);
		this.selectGamePanel = selectGamePanel; 
		this.view = view;
		try {
			GameGUI.getClientConnection().requestGameRooms();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() instanceof JButton) {
			JButton referenceButton = (JButton) arg0.getSource(); 
			if (referenceButton.getName().contentEquals("Select Game")) {
				String gameName = this.selectGamePanel.getSelectGameBox().getSelectedItem().toString();
				if(gameName == null || gameName.equals("")) {
					this.selectGamePanel.getError().setText("Please select a game");
				}
				else {
					GameGUI.getClientConnection().handleSelectedGameRoom(gameName); 
				}
				
			}
			else if (referenceButton.getName().contentEquals("Cancel")){
				this.view.shuffleSelectCreateGameOrSelectGamePanel();
			}
			else if(referenceButton.getName().contentEquals("Refresh")) {
				try {
					GameGUI.getClientConnection().requestGameRooms();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public void selectedGameSuccess() {
		this.view.shuffleToGame();
	}
	
	public void setGameRooms(ArrayList<GameRoomData> gameRooms) {
		
		this.selectGamePanel.getSelectGameBox().removeAllItems();
		for (GameRoomData gameRoomData : gameRooms) {
			System.out.println("Adding " + gameRoomData.getName());
			this.selectGamePanel.getSelectGameBox().addItem(gameRoomData.getName());
		}
	}
	
}
