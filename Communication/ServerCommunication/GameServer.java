package Communication.ServerCommunication;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import Communication.ClientServerMessage;
import Communication.GameStatistics;
import Communication.LeftGame;
import Communication.LoginFailure;
import Communication.LoginSuccess;
import Communication.LogoutOperation;
import Communication.LogoutOperationForClose;
import Communication.NewAccountFailure;
import Communication.NewAccountSuccess;
import Communication.NewGameRoom;
import Communication.NewGameRoomFailure;
import Communication.NewGameRoomSuccess;
import Communication.RequestGameRooms;
import Communication.SelectedGameRoom;
import Communication.TestObject;
import DatabaseConnection.Database;
import DatabaseConnection.User;
import Game.Game;
import Game.PlayerData;
import GameLogic.GameLogic;
import ServerGUI.ServerGUI;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	//I'm storing as much state as I can to make this as easy as possible. 
	
	//game room name ----> GameRoomData  (could just hash the game room... but I don't want to do that)
	private HashMap<String, GameRoomData> gameRoomNameToData; 
	
	//gameRoomName --->>> username
	private HashMap<String, String> gameNameToUsername; 
	private HashMap<String, GameRoomData> gameMap;
	private HashMap<String, String> playerMap; 
	//username --> connectionReference
	private HashMap<String, ConnectionToClient> usernameToConnection; 
	private Set<String> loggedInPlayers;
	private Set<String> gameRoomNamesInUse;
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
		this.gameMap = new HashMap<String, GameRoomData>();
		this.playerMap = new HashMap<String, String>(); 
		this.loggedInPlayers = new HashSet<String>(); 
		this.gameRoomNamesInUse = new HashSet<String>(); 
		this.gameRoomNameToData = new HashMap<String, GameRoomData>(); 
		this.gameNameToUsername = new HashMap<String, String>(); 
	}

	public GameServer(int port) {
		super(port);
	}

	@Override
	protected void clientConnected(ConnectionToClient client) {
		this.serverGUI.getLog().append(String.format("Client %d connected\n", client.getId()));
	}
	@Override
	protected synchronized void clientDisconnected(ConnectionToClient client) {
		this.serverGUI.getLog().append(String.format("Client %d disconnected\n", client.getId()));
	}
	@Override
	protected synchronized void clientException(ConnectionToClient client, Throwable exception) {
		// TODO Auto-generated method stub
		this.serverGUI.getLog().append(String.format("Client %d: ", client.getId()));
		exception.printStackTrace();
		
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

		if (object instanceof PlayerLoginData) {
			try {
				PlayerLoginData playerLoginData = (PlayerLoginData) object; 
				if (   !this.loggedInPlayers.contains(playerLoginData.getUserName()) &&
						this.database.verifyUserExistsForLogin(new User(playerLoginData.getUserName(), playerLoginData.getPassword()))) {
					connection.sendToClient(new LoginSuccess(new PlayerData(playerLoginData.getUserName(), connection.getId())));
					
					this.playerMap.put(Long.toString(connection.getId()), playerLoginData.getUserName()); 
					this.loggedInPlayers.add(playerLoginData.getUserName()); 
				}
				else {
					if (this.loggedInPlayers.contains(playerLoginData.getUserName())) {
						connection.sendToClient(new LoginFailure("Failure to login, user is already logged in"));
						return; 
					}
					connection.sendToClient(new LoginFailure("Failure to login, Invalid Credentials"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(String.format("Error in login logic and server client communication: %s", e.getMessage()));
			}
		}
		else if (object instanceof GameStatistics) {
			GameStatistics gameStatistics = (GameStatistics) object; 
			ArrayList<String> result = this.database.query(String.format("Select * from STATISTICS where username='%s'", gameStatistics.getUsername()));
			System.out.println(result.get(0));
			String[] resultStrings = result.get(0).split(",");
			try {
				connection.sendToClient(new GameStatistics(resultStrings[0], resultStrings[1], resultStrings[2]));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (object instanceof LogoutOperation) {
			LogoutOperation logoutOperation = (LogoutOperation) object; 
			if (this.gameMap.containsKey(logoutOperation.getPlayerData().getPlayerName())) {
				this.gameMap.remove(logoutOperation.getPlayerData().getPlayerName());
				if (this.gameRoomNamesInUse.contains(logoutOperation.getPlayerData().getPlayerName())) {
					this.gameRoomNamesInUse.remove(logoutOperation.getPlayerData().getPlayerName());
				}
			}
			this.handleLogoutOperationForClose(logoutOperation.getPlayerData());
		}
		
		else if(object instanceof PlayerNewAccountData) {
			System.out.println("New account data received");
			try {
				PlayerNewAccountData playerNewAccountData = (PlayerNewAccountData) object;
				if (this.database.verifyUserExistsForCreateUser(new User(playerNewAccountData.getUsername(), playerNewAccountData.getPassword()))) {
					connection.sendToClient(new NewAccountSuccess());
				}
				else {
					connection.sendToClient(new NewAccountFailure("Failure to create new account, username in use"));
				}
			} catch (Exception e) {
				System.out.println(String.format("Error in create account logic and server client communication: %s", e.getMessage())); 
			}
		}
		
		else if (object instanceof NewGameRoom) {
			NewGameRoom newGameRoom = (NewGameRoom) object; 
			String username = this.playerMap.get(Long.toString(connection.getId()));
			GameRoomData gameRoomData = new GameRoomData(newGameRoom.getName());
			
			//check and see if the game room name is in use
			if (this.gameRoomNamesInUse.contains(gameRoomData.getName())) {
				NewGameRoomFailure newGameRoomFailure = new NewGameRoomFailure("Name already in use"); 
				try {
					connection.sendToClient(newGameRoomFailure);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			this.gameRoomNameToData.put(gameRoomData.getName(), gameRoomData); 
			this.gameMap.put(username, gameRoomData);
			this.gameNameToUsername.put(gameRoomData.getName(), username); 
			try {
				connection.sendToClient(new NewGameRoomSuccess());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if (object instanceof RequestGameRooms) {
			this.printGameRooms();
			try {
				connection.sendToClient(this.handleRequestGameRooms());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (object instanceof SelectedGameRoom) {
			System.out.println("Selecting game room selected");
			
			//the user has selected a game room
			SelectedGameRoom selectedGameRoom = (SelectedGameRoom) object;
			
			//don't know what I need yet, so I will extracting everything
			String gameRoomName = selectedGameRoom.getGameRoomData().getName(); 
			String usernameForJoiningPlayer = this.playerMap.get(Long.toString(connection.getId()));
			System.out.println(String.format("Username:%s | ID: %d", usernameForJoiningPlayer, connection.getId()));
			//need to get the username of the player that is waiting in the game room
			String usernameForWaitingPlayer = this.gameNameToUsername.get(gameRoomName); 
			System.out.println(usernameForWaitingPlayer);
			this.handleSelectedGameRequest(gameRoomName, usernameForJoiningPlayer, usernameForWaitingPlayer); 
			
		}
		
		else if(object instanceof LogoutOperationForClose) {
			System.out.println("Logout operation");
			LogoutOperationForClose logoutOperation = (LogoutOperationForClose) object; 
			PlayerData playerData = logoutOperation.getPlayerData(); 
			if (this.gameMap.containsKey(logoutOperation.getPlayerData().getPlayerName())) {
				this.gameMap.remove(logoutOperation.getPlayerData().getPlayerName());
				if (this.gameRoomNamesInUse.contains(logoutOperation.getPlayerData().getPlayerName())) {
					this.gameRoomNamesInUse.remove(logoutOperation.getPlayerData().getPlayerName());
				}
			}
			try {
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.handleLogoutOperationForClose(playerData);
			
			
		}
		
		else if(object instanceof Game) {
			//coordinate games
			//get the user that send it 
			String justWentUser = this.playerMap.get(Long.toString(connection.getId())); 
			Game refGame = this.usernameToGameroom.get(justWentUser); 
			String opponentUser = (refGame.getPlayerOne().equals(justWentUser)) ? refGame.getPlayerTwo() : refGame.getPlayerOne(); 
			Game defactoGame = (Game) object;
			
			defactoGame.getBoard().printBoard();
			boolean acceptable; 
			if (defactoGame.isMoving()) {
				acceptable = GameLogic.handleMove(refGame, defactoGame, justWentUser); 
			}
			else if(defactoGame.isPromoting()) {
				acceptable = GameLogic.handlePromotion(defactoGame, justWentUser); 
			}
			else {
				acceptable = true; 
			}
			
			//find the connection id's (reusing code from earlier when the references were unknown)
			ConnectionToClient justWentConnection = null; 
			ConnectionToClient opponentConnection = null; 
			for (Thread thread : this.getClientConnections()) {
				System.out.println(thread.getId());
				if(this.playerMap.get(Long.toString(thread.getId())).equals(justWentUser)) {
					justWentConnection = (ConnectionToClient) thread; 
				}
				else if(this.playerMap.get(Long.toString(thread.getId())).equals(opponentUser)){
					opponentConnection = (ConnectionToClient) thread; 
				}
				
				if (justWentConnection != null && opponentConnection != null) {
					break; 
				}
			}
			
			//if the move was acceptable, we just apply the move and send it back to the users
			if (acceptable) {
				if (defactoGame.getWinner() != null) {
					//someone won
					if(defactoGame.getWinner().contentEquals(justWentUser)) {
						this.database.increment(justWentUser, true);
						this.database.increment(opponentUser, false);
						try {
							defactoGame.setWinnerMessage("You won! congrats!");
							justWentConnection.sendToClient(defactoGame);
							defactoGame.setWinnerMessage("You Lost! Sorry");
							opponentConnection.sendToClient(defactoGame);
							if (this.gameMap.containsKey(justWentUser)) {
								this.gameMap.remove(justWentUser); 
							}
							if(this.gameMap.containsKey(opponentUser)){
								this.gameMap.remove(opponentUser); 
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						this.database.increment(justWentUser, false);
						this.database.increment(opponentUser, true);
						try {
							defactoGame.setWinnerMessage("You Lost! Sorry");
							justWentConnection.sendToClient(defactoGame);
							defactoGame.setWinnerMessage("You won! Congrats");
							opponentConnection.sendToClient(defactoGame);
							if (this.gameMap.containsKey(justWentUser)) {
								this.gameMap.remove(justWentUser); 
							}
							if(this.gameMap.containsKey(opponentUser)){
								this.gameMap.remove(opponentUser); 
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					return;
				}
				
				
				//make sure the message is removed
				defactoGame.setErrorMessage(null);
				try {
					defactoGame.setYourTurn(false);
					justWentConnection.sendToClient(defactoGame);
					defactoGame.setYourTurn(true);
					opponentConnection.sendToClient(defactoGame);
				} catch (Exception e) {
					// TODO: handle exception
				}
				//now update the game reference for both the hashes
				this.usernameToGameroom.put(opponentUser, defactoGame); 
				this.usernameToGameroom.put(justWentUser, defactoGame); 
			} 
			//if the move was unacceptable, just redo!!!
			else {
				refGame.setErrorMessage("Illegal operation!");
				try {
					refGame.setYourTurn(true); 
					justWentConnection.sendToClient(refGame);
					refGame.setErrorMessage(null);
					refGame.setYourTurn(false);
					opponentConnection.sendToClient(refGame);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			
		}
		else if(object instanceof LeftGame) {
			System.out.println("Left game received");
			//get the person who left the game
			LeftGame leftGame = (LeftGame) object; 
			LeftGame response = new LeftGame(); 
			String userThatSentRequest = this.playerMap.get(Long.toString(connection.getId())); 
			
			
			
			//find out who else was in the damn game...
			Game refGame = this.usernameToGameroom.get(userThatSentRequest); 
			try {
				String userToRepondTo = refGame.getPlayerOne().equals(userThatSentRequest) ? refGame.getPlayerTwo() : refGame.getPlayerOne();
				//now that we have the user to respond to, we need to get their connection reference
				for (Thread connectionToClient : this.getClientConnections()) {
					if (this.playerMap.get(Long.toString(connectionToClient.getId())).contentEquals(userToRepondTo)) {
						ConnectionToClient connectionToClient2 = (ConnectionToClient) connectionToClient; 
						try {
							//do some cleanup.
							if (this.gameMap.containsKey(userThatSentRequest)) {
								try {
									this.gameRoomNamesInUse.remove(this.gameMap.get(userThatSentRequest).getName());
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								this.gameMap.remove(userThatSentRequest); 
							}
							if (this.gameMap.containsKey(userToRepondTo)) {
								try {
									this.gameRoomNamesInUse.remove(this.gameMap.get(userToRepondTo).getName());
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								this.gameMap.remove(userToRepondTo); 
							}
							if (this.usernameToGameroom.containsKey(userToRepondTo)) {
								this.usernameToGameroom.remove(userToRepondTo); 
							}
							if (this.usernameToGameroom.containsKey(userThatSentRequest)) {
								this.usernameToGameroom.remove(userThatSentRequest); 
							}
							
							response.setMessage(String.format("User: %s has left the game. YOU LOSE!", userThatSentRequest));
							this.database.increment(userThatSentRequest, false);
							connection.sendToClient(response);
							response.setMessage(String.format("User: %s has left the game. YOU WIN!", userThatSentRequest));
							this.database.increment(userToRepondTo, true);
							connectionToClient2.sendToClient(response);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				return;
			} catch (Exception e) {
				//this means that not both players were in the match... so exit gracefully
				response.setMessage("You left the game. Press leave to exit");
				try {
					connection.sendToClient(response);
					this.gameRoomNamesInUse.remove(this.gameMap.get(userThatSentRequest).getName()); 
					this.gameMap.remove(userThatSentRequest); 
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	//now... how to organize this
	//players should be put in the same room, which is identifiable both ways from username...
	//... so, how should this be done...
	//single hash should work... as long as game room has both players and 2 username hashes point to same game room. 
	
	//username --> gameroom
	
	private HashMap<String, Game> usernameToGameroom = new HashMap<String, Game>(); 
	public void handleSelectedGameRequest(String gameRoomName, String usernameForJoiningPlayer, String usernameForWaitingPlayer) {
		//we are creating a new game with a reference to both players
		Game game = new Game(); 
		game.setPlayerOne(usernameForJoiningPlayer);
		game.setPlayerTwo(usernameForWaitingPlayer);
		game.setPlayerOneAlias("up");
		game.setPlayerTwoAlias("down");
		//need to get the ID for the players that we can send the game to them......
		//best way to do this....
		//probably store the connection reference initially, but then we would need to mess with the unreliable hooks of the framework
		//to kill the threads reference upon disconnection. I dont trust it. 
		
		//I feel like there are already plenty of hashes, so no more. 
		
		this.usernameToGameroom.put(game.getPlayerOne(), game); 
		this.usernameToGameroom.put(game.getPlayerTwo(), game); 
		
		//now to find the connection, need to make sure each player has a different turn boolean. 
		ConnectionToClient playerOneReference = null; 
		ConnectionToClient playerTwoReference = null; 
		
		for (Thread thread : this.getClientConnections()) {
			System.out.println(thread.getId());
			if(this.playerMap.get(Long.toString(thread.getId())).equals(game.getPlayerOne())) {
				playerOneReference = (ConnectionToClient) thread; 
			}
			else if(this.playerMap.get(Long.toString(thread.getId())).equals(game.getPlayerTwo())){
				playerTwoReference = (ConnectionToClient) thread; 
			}
			
			if (playerOneReference != null && playerTwoReference != null) {
				break; 
			}
		}
		
		try {
			game.setInitialResponse(true);
			game.setYourTurn(true);
			playerOneReference.sendToClient(game);
			game.setYourTurn(false);
			playerTwoReference.sendToClient(game);
		} catch (IOException e) {
			System.out.println("Problem creating the initial game");
		}
		}
	
	public ArrayList<GameRoomData> handleRequestGameRooms() {
		ArrayList<GameRoomData> gameRoomDatas = new ArrayList<GameRoomData>(); 
		for (String gameRoomDataKey : this.gameMap.keySet()) {
			GameRoomData gameRoomData = this.gameMap.get(gameRoomDataKey); 
			gameRoomDatas.add(gameRoomData);
		}
		return gameRoomDatas; 
	}
	//will need to expanded upon
	public void handleLogoutOperationForClose(PlayerData playerData) {
		this.loggedInPlayers.remove(playerData.getPlayerName());
		
	}
	
	//debugging stuff
	public void printGameRooms() {
		Iterator<String> keys = this.gameMap.keySet().iterator();
		while(keys.hasNext()) {
			System.out.println(this.gameMap.get(keys.next()).getName());
		}
	}
}
