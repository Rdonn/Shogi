package Communication.ServerCommunication;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.swing.JLabel;

import Communication.ClientServerMessage;
import Communication.LoginFailure;
import Communication.LoginSuccess;
import Communication.NewAccountFailure;
import Communication.NewAccountSuccess;
import DatabaseConnection.Database;
import DatabaseConnection.User;
import ServerGUI.ServerGUI;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {

	private ServerGUI serverGUI; 
	private Database database; 
	public GameServer() {
		super(8300);
		try {
			//create the database. We WILL BE USING ONE STATIC DB.PROPERTIES IN THIS DATABASE IMPLEMENTATION
			//SO IT WILL BE HARDCODED IN THE CONSTRUCTOR. DON'T @ ME BOUT IT. 
			this.database = new Database();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(String.format("Error in creating the database: %s", e.getMessage()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(String.format("Error in creating the database: %s", e.getMessage()));
		} 
	}

	public GameServer(int port) {
		super(port);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
	}
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
	}
	@Override
	protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
		// TODO Auto-generated method stub
		System.out.println("Exception");
		exception.printStackTrace();
		System.out.println(exception.getMessage());
		System.out.println(exception.getCause());
	}
	@Override
	protected void serverStarted() {
		// TODO Auto-generated method stub
		this.serverGUI.getLog().append("Server started\n");
		JLabel referece = this.serverGUI.getStatus();
		referece.setText("Listening");
		referece.setForeground(Color.green);
	}
	
	@Override
	protected void serverStopped() {
		// TODO Auto-generated method stub
		this.serverGUI.getLog().append("Server Stopped\n");
		JLabel reference = this.serverGUI.getStatus(); 
		reference.setText("Stopped");
		reference.setForeground(Color.red);
	}
	@Override
	protected void serverClosed() {
		// TODO Auto-generated method stub
		this.serverStopped();
	}
	
	public void setServerGUI(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
	}
	
	@Override
	protected void handleMessageFromClient(Object object, ConnectionToClient connection) {

		this.serverGUI.getLog().append("Message received on server\n");
		
		
		if (object instanceof PlayerLoginData) {
			try {
				PlayerLoginData playerLoginData = (PlayerLoginData) object; 
				if (this.database.verifyUserExistsForLogin(new User(playerLoginData.getUserName(), playerLoginData.getPassword()))) {
					connection.sendToClient(new LoginSuccess());
				}
				else {
					connection.sendToClient(new LoginFailure("Failure to login, Invalid Credentials"));
				}
			} catch (Exception e) {
				System.out.println(String.format("Error in login logic and server client communication: %s", e.getMessage()));
			}
		}
		
		else if(object instanceof PlayerNewAccountData) {
			System.out.println("New account data received");
			try {
				PlayerNewAccountData playerNewAccountData = (PlayerNewAccountData) object;
				if (this.database.verifyUserExistsForCreateUser(new User(playerNewAccountData.getUsername(), playerNewAccountData.getPassword()))) {
					System.out.println("Success");
					connection.sendToClient(new NewAccountSuccess());
				}
				else {
					System.out.println("Failure");
					connection.sendToClient(new NewAccountFailure("Failure to create new account, username in use"));
				}
			} catch (Exception e) {
				System.out.println(String.format("Error in create account logic and server client communication: %s", e.getMessage())); 
			}
		}
		
//		if (object instanceof ClientServerMessage) {
//
//			ClientServerMessage clientServerMessage = (ClientServerMessage) object;
//			String message = clientServerMessage.getMessage(); 
//			Object data = clientServerMessage.getData();
//			switch (message) {
//			case "LOGIN":
//				if (data instanceof PlayerLoginData) {
//					PlayerLoginData playerLoginData = (PlayerLoginData) data; 
//					//formatting the data so that it will play nicely with the boilerplate database
//					try {
//						if (this.database.verifyUserExistsForLogin(new User(playerLoginData.getUserName(), playerLoginData.getPassword()))){
//							
//							ClientServerMessage clientServerMessage2 = new ClientServerMessage(); 
//							clientServerMessage.setMessageLOGIN_SUCCESS();
//							connection.sendToClient(clientServerMessage2);
//						}
//						else {
//							
//							ClientServerMessage clientServerMessage2 = new ClientServerMessage(); 
//							clientServerMessage.setMessageLOGIN_FAILURE();
//							clientServerMessage.setData(new String("Login information invalid"));
//							connection.sendToClient(clientServerMessage2);
//						}
//					}
//					catch (Exception e) {
//						
//					}
//					
//					
//
//				}
//
//				break;
//
//			case "NEW_ACCOUNT":
//				if (data instanceof PlayerNewAccountData) {
//					PlayerNewAccountData playerNewAccountData = (PlayerNewAccountData) data; 
//					// TODO: Database stuff
//					// For now, assume new account created.
//					
//					try {
//						if (this.database.verifyUserExistsForCreateUser(new User(playerNewAccountData.getUsername(), playerNewAccountData.getPassword()))) {
//							
//							ClientServerMessage clientServerMessage2 = new ClientServerMessage(); 
//							clientServerMessage.setMessageNEW_ACCOUNT_SUCCESS();
//							connection.sendToClient(clientServerMessage2);
//						}
//						else {
//							ClientServerMessage clientServerMessage2 = new ClientServerMessage(); 
//							clientServerMessage2.setMessageNEW_ACCOUNT_FAILURE();
//							clientServerMessage.setData(new String("Username already in use"));
//							connection.sendToClient(clientServerMessage2);
//						}
//					} catch (Exception e) {
//						
//					}
//				}
//
//			default:
//				break;
//			}
//
//		}
//
//	}
	}
}
