package GameUI;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameGUI extends JFrame{

	public GameGUI(int IP, String PORT) {
		// TODO Auto-generated constructor stub
		this.add(new GamePanel()); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		
		
	}
	
	public static void main(String[] args) {
		int IP = Integer.parseInt(args[0]); 
		String PORT = args[0]; 
		new GameGUI(IP, PORT); 
	}
}
