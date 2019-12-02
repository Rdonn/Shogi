package GameUI;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsPanel extends JPanel{
	JLabel title = new JLabel("Game Statistics"); 
	JLabel username; 
	JLabel gamesWon; 
	JLabel gamesLost; 
	JButton getBackButton; 
	GameGUI gameGUI;
	public StatisticsPanel(GameGUI gameGUI) {
		// TODO Auto-generated constructor stub
		this.gameGUI = gameGUI; 
		this.username = new JLabel("username"); 
		this.gamesLost = new JLabel("0");
		this.gamesWon = new JLabel("0"); 
		this.getBackButton = new JButton("Go Back");
		this.getBackButton.setName("Go Back");
		//setup functionality 
		StatisticsController statisticsController = new StatisticsController(gameGUI, this); 
		this.getBackButton.addActionListener(statisticsController);
		
		JPanel titleHolder = new JPanel(); 
		titleHolder.add(this.title); 
		//format it
		 
		GridLayout gridLayout = new GridLayout(2, 3);
		gridLayout.setVgap(4);
		JPanel titlePanel = new JPanel(gridLayout);
		titlePanel.add(new JLabel("Username")); 
		titlePanel.add(new JLabel("Games Won")); 
		titlePanel.add(new JLabel("Games Lost")); 
		titlePanel.add(this.username); 
		titlePanel.add(this.gamesWon); 
		titlePanel.add(this.gamesLost);
		
		JPanel buttonHolder = new JPanel();  
		buttonHolder.add(this.getBackButton); 
		
		JPanel totalHolder = new JPanel(); 
		totalHolder.setLayout(new BoxLayout(totalHolder, BoxLayout.PAGE_AXIS));
		
		totalHolder.add(titleHolder); 
		totalHolder.add(titlePanel); 
		totalHolder.add(buttonHolder); 
		
		this.add(totalHolder);
		
		
	}
	public JLabel getUsername() {
		return username;
	}
	public JLabel getGamesLost() {
		return gamesLost;
	}
	public JLabel getGamesWon() {
		return gamesWon;
	}
}
