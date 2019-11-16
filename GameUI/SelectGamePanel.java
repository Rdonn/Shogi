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
	public SelectGamePanel(GameGUI gameGUI) {
		PlayerData testData = new PlayerData("xxxBestPlayerxxx", "00000"); 
		String[] testDataStrings = {testData.getPlayerName()}; 
		this.selectPlayerBox = new JComboBox<String>(testDataStrings); 
		this.selectGameButton = new JButton("Select Game"); 
		this.error = new JLabel();
		this.error.setForeground(Color.red);
		this.title = new JLabel("Select A Game To Begin Playing with a player"); 
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(this.error); 
		this.add(this.title); 
		
		JPanel holder = new JPanel(new GridLayout(1, 2));
		holder.add(this.selectPlayerBox); 
		holder.add(this.selectGameButton); 
		
		this.add(holder); 
		
		
	}
}
