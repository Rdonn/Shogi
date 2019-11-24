package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import Communication.ServerCommunication.GameRoomData;

import javax.swing.JButton;


//I will refactor the name later, just keeping it so I dont have to reference anything else. 
public class SelectCreateGameOrSelectGameController implements ActionListener{

	private GameGUI view; 
	private SelectCreateGameOrSelectGamePanel SelectCreateGameOrSelectGameController; 
	
	public SelectCreateGameOrSelectGameController(SelectCreateGameOrSelectGamePanel selectCreateGameOrSelectGamePanel, GameGUI view) {
		this.SelectCreateGameOrSelectGameController = selectCreateGameOrSelectGamePanel; 
		this.view = view; 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JButton) {
			JButton actionButton = (JButton)arg0.getSource(); 
			if(actionButton.getName().equals("Create Game")) {
				this.view.shuffCreateGamePanel();
			}
			else if(actionButton.getName().equals("Select Game")) {
				this.view.shuffleSelectGamePanel();
			}
		}
		
	}
	


}
