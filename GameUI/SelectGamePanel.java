package GameUI;
import Game.PlayerData;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectGamePanel extends JPanel{
	JButton selectGameButton; 
	JLabel error; 
	JLabel title; 
	JComboBox selectPlayerBox; 
	JButton cancelButton; 
	public SelectGamePanel(GameGUI gameGUI) {
		PlayerData testData = new PlayerData("xxxBestPlayerxxx", "00000"); 
		String[] testDataStrings = {testData.getPlayerName()}; 
		this.selectPlayerBox = new JComboBox<String>(testDataStrings); 
		this.selectGameButton = new JButton("Select Game"); 
		this.selectGameButton.setName("Select Game");
		this.cancelButton = new JButton("Cancel");
		this.cancelButton.setName("Cancel");
		this.error = new JLabel();
		this.error.setForeground(Color.red);
		this.title = new JLabel("Select A Game To Begin Playing with a player"); 
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(this.error); 
		this.add(this.title); 
		
		
		JPanel selectPlayerBoxHolder = new JPanel(); 
		selectPlayerBoxHolder.add(this.selectPlayerBox); 
		
		JPanel buttonHolder = new JPanel(); 
		buttonHolder.add(this.selectGameButton); 
		buttonHolder.add(this.cancelButton); 
		
		
		
		//set up the button functionality 
		this.selectGameButton.addActionListener(new SelectGameController(this, gameGUI));
		this.cancelButton.addActionListener(new SelectGameController(this, gameGUI));
		
		
		this.add(buttonHolder);
		this.add(selectPlayerBoxHolder);
		
		
	}
}
