package GameUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JFrame;

import Communication.ClientCommunication.GameClientConnection;
import Game.PlayerData;

public class GameGUI extends JFrame{
	private GameClientConnection clientConnection; 
	private static PlayerData playerData; 
	public GameGUI(String IP, int PORT) throws IOException {
		// TODO Auto-generated constructor stub
		
		clientConnection = new GameClientConnection(); 
		
		//we need this to run... so going to throw error from this constructor if it doesn't work, exiting before GUI logic
		clientConnection.openConnection();
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set up the card layout
		this.getContentPane().setLayout(new CardLayout());
		this.getContentPane().add("Create Account Panel",new CreateAccountPanel(this, clientConnection)); 
		this.getContentPane().add("Game Panel",new GamePanel(this));
		this.getContentPane().add("Initial Panel",new InitialPanel(this)); 
		this.getContentPane().add("Login Panel",new LoginPanel(this, clientConnection)); 
		this.getContentPane().add("Create Game Panel", new CreateGamePanel(this, clientConnection));
		this.getContentPane().add("Select Game Panel", new SelectGamePanel(this)); 
		this.getContentPane().add("Select Create Game Or Select Game Panel", new SelectCreateGameOrSelectGamePanel(this)); 
		this.shuffleToInitial();
		this.setVisible(true);
		this.pack();
	
	}
	
	public static void setPlayerData(PlayerData playerData) {
		GameGUI.playerData = playerData; 
	}
	
	public static PlayerData getPlayerData() {
		return playerData;
	}
	
	public GameClientConnection getClientConnection() {
		return clientConnection; 
	}
	
	//shuffling functionality
	public void shuffCreateGamePanel() {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Create Game Panel");

	}
	public void shuffleSelectGamePanel() {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Select Game Panel");

	}
	
	public void shuffleSelectCreateGameOrSelectGamePanel() {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Select Create Game Or Select Game Panel");

	}
	public void shuffleToCreateAccount() {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Create Account Panel");
	}
	
	public void shuffleToGame() {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Game Panel");

	}
	public void shuffleToInitial() {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Initial Panel");
		
	}
	
	public void shuffleToLogin() {
		CardLayout cardLayout = (CardLayout) this.getContentPane().getLayout(); 
		cardLayout.show(this.getContentPane(), "Login Panel");

	}
	
	
	
	 
	
	
	
	
	public static void main(String[] args) {
		String IP = args[0]; 
		int PORT = Integer.parseInt(args[1]); 
		
		try {
			new GameGUI(IP, PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return; 
		} 
		
	}
}
