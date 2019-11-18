package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectGameController implements ActionListener{

	private SelectGamePanel selectGamePanel; 
	private GameGUI view; 
	
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

	
}
