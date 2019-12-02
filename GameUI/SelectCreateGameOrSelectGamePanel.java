package GameUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectCreateGameOrSelectGamePanel extends JPanel{
	private JLabel title; 
	private JButton selectGameButton; 
	private JButton createGameButton; 
	private JButton logoutButton; 
	private JButton statistics; 
	public SelectCreateGameOrSelectGamePanel(GameGUI gameGUI) {
		// TODO Auto-generated constructor stub
		this.title = new JLabel("Welcome to Shogi! Choose an option");
		this.selectGameButton = new JButton("Select Game");
		this.createGameButton = new JButton("Create Game");
		this.statistics = new JButton("Statistics");
		this.statistics.setName("Statistics");
		
		this.selectGameButton.setName("Select Game");
		this.createGameButton.setName("Create Game");
		this.logoutButton = new JButton("Logout"); 
		this.logoutButton.setName("Logout");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(this.title); 
		JPanel buttonHolder = new JPanel(); 
		buttonHolder.add(this.selectGameButton); 
		buttonHolder.add(this.createGameButton); 
		buttonHolder.add(this.logoutButton); 
		buttonHolder.add(this.statistics); 
		SelectCreateGameOrSelectGameController selectCreateGameOrSelectGameController = new SelectCreateGameOrSelectGameController(this, gameGUI); 
		this.selectGameButton.addActionListener(selectCreateGameOrSelectGameController);
		this.createGameButton.addActionListener(selectCreateGameOrSelectGameController);
		this.logoutButton.addActionListener(selectCreateGameOrSelectGameController);
		this.statistics.addActionListener(selectCreateGameOrSelectGameController);
		this.add(buttonHolder);
		
		
	}
}
