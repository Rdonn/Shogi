package GameUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import Communication.GameStatistics;

public class StatisticsController implements ActionListener{
	GameGUI gameGUI; 
	StatisticsPanel statisticsPanel; 
	public StatisticsController(GameGUI gameGUI, StatisticsPanel statisticsPanel) {
		// TODO Auto-generated constructor stub
		GameGUI.getClientConnection().setStatisticsController(this);
		this.gameGUI = gameGUI; 
		this.statisticsPanel = statisticsPanel; 
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			JButton referenceButton = (JButton) arg0.getSource(); 
			if (referenceButton.getName().contentEquals("Go Back")) {
				System.out.println("Go back clicked");
				gameGUI.shuffleSelectCreateGameOrSelectGamePanel();
			}
		}
		
	}

	public void setStatistics(GameStatistics gameStatistics) {
		// TODO Auto-generated method stub
		this.statisticsPanel.getUsername().setText(gameStatistics.getUsername());
		this.statisticsPanel.getGamesWon().setText(gameStatistics.getGamesWon());
		this.statisticsPanel.getGamesLost().setText(gameStatistics.getGamesLost());
	}
}
