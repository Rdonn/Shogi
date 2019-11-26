package Communication.ClientCommunication;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Communication.ClientServerMessage;
import Communication.GameData;
import Communication.GetMessagesInstance;
import Communication.LeftGame;
import Communication.LoginFailure;
import Communication.LoginSuccess;
import Communication.LogoutOperationForClose;
import Communication.LogoutOperationForClose;
import Communication.NewAccountFailure;
import Communication.NewAccountSuccess;
import Communication.NewGameRoom;
import Communication.NewGameRoomFailure;
import Communication.NewGameRoomSuccess;
import Communication.PlayerLeftGame;
import Communication.RequestGameRooms;
import Communication.SelectedGameRoom;
import Communication.TestObject;
import Communication.ServerCommunication.GameRoomData;
import Communication.ServerCommunication.PlayerLoginData;
import Communication.ServerCommunication.PlayerNewAccountData;
import Game.Game;
import Game.PlayerData;
import GameUI.CreateAccountController;
import GameUI.CreateGameController;
import GameUI.GameController;
import GameUI.GameGUI;
import GameUI.LoginController;
import GameUI.SelectGameController;
import ocsf.client.AbstractClient;

public class GameClientConnection extends AbstractClient {

	private GameController gameController;
	private SelectGameController selectGameController;
	private LoginController loginController;
	private CreateAccountController createAccountController;
	private CreateGameController createGameController;
	
	/***
	 * Uses '127.0.0.1:8300' as default host
	 */
	public GameClientConnection() {
		this("127.0.0.1", 8300);
	}


	/***
	 * 
	 * @param host Host IP Address to be used when creating the OCSF Client
	 * @param port Port number to be used when creating the OCSF Client
	 */
	public GameClientConnection(String host, int port) {
		super(host, port);
		
	}

    @Override
    protected void connectionEstablished() {
    	// TODO Auto-generated method stub
    	System.out.println("Connection opened");
    }
    
    @Override
    protected void connectionException(Exception exception) {
    	// TODO Auto-generated method stub
    	System.out.println(exception.getMessage());
    }
	@Override
	protected void connectionClosed() {
		// TODO Auto-generated method stub
		System.out.println("connection closed");
	}
	
	public GameController getGameController() {
		return gameController;
	}


	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}


	public SelectGameController getSelectGameController() {
		return selectGameController;
	}


	public void setSelectGameController(SelectGameController selectGameController) {
		this.selectGameController = selectGameController;
	}


	public LoginController getLoginController() {
		return loginController;
	}


	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}


	public CreateAccountController getCreateAccountController() {
		return createAccountController;
	}


	public void setCreateAccountController(CreateAccountController createAccountController) {
		this.createAccountController = createAccountController;
	}


	public CreateGameController getCreateGameController() {
		return createGameController;
	}


	public void setCreateGameController(CreateGameController createGameController) {
		this.createGameController = createGameController;
	}


	@Override
	protected void handleMessageFromServer(Object object) {
		
		if (object instanceof LoginFailure) {
			System.out.println("Login failure received");
			LoginFailure loginFailure = (LoginFailure) object; 
			this.handleReceiveLoginFailure(loginFailure);
		}
		else if (object instanceof GameData) {
			GameData gameData = (GameData) object;
			this.handleReceiveGameData(gameData);
		}
		else if(object instanceof LoginSuccess) {
			LoginSuccess loginSuccess = (LoginSuccess) object; 
			this.handleReceiveLoginSuccess(loginSuccess);
		}
		else if(object instanceof NewAccountFailure) {
			NewAccountFailure newAccountFailure = (NewAccountFailure) object; 
			this.handleReceiveNewAccountFailure(newAccountFailure);
		}
		else if(object instanceof NewAccountSuccess) {
			this.handleReceiveNewAccountSuccess();
		}
		else if(object instanceof NewGameRoomSuccess) {
			this.handleReceiveNewGameRoomSuccess();
		}
		else if(object instanceof NewGameRoomFailure) {
			NewGameRoomFailure newGameRoomFailure = (NewGameRoomFailure) object; 
			this.handleReceiveNewGameRoomFailure(newGameRoomFailure);
		}
		else if(object instanceof LeftGame) {
			System.out.println("left game received by client");
			LeftGame leftGame = (LeftGame) object; 
			System.out.println(leftGame.getMessage());
			this.gameController.setTitle(leftGame.getMessage());
			this.gameController.otherUserForfeiter();
		}
		else if(object instanceof ArrayList<?>) {
			ArrayList<?> arrayList = (ArrayList<?>) object; 
			if (arrayList.size() > 0 && arrayList.get(0) instanceof GameRoomData) {
				System.out.println("Requested game room data successfully");
				ArrayList<GameRoomData> gameRoomDatas = (ArrayList<GameRoomData>) arrayList;
				this.handleReceiveGameRooms(gameRoomDatas);
				
			}
			else {
				//debuggin section. can be removed
			}
		}
		else if(object instanceof Game) {
			System.out.println("Game received");
			Game game = (Game) object; 
			if(game.isYourTurn()) {
				this.gameController.enable(); 
			}
			else {
				this.gameController.disable();
			}
			this.gameController.setGame(game);
			if(game.isInitialResponse()) {
				
				this.gameController.setTitle(game.getPlayerOne(), game.getPlayerTwo());
			}
			
		}
		
		
//		if(object instanceof ClientServerMessage) {
//			ClientServerMessage clientServerMessage= (ClientServerMessage) object; 
//			String message = clientServerMessage.getMessage(); 
//			if(message.equals(GetMessagesInstance.GAME_DATA())) {
//				if(clientServerMessage.getData() instanceof Game) {
//					handleReceiveGameData((Game) clientServerMessage.getData());
//				}
//			}
//			
//			if(message.contentEquals(GetMessagesInstance.LOGIN_FAILURE())) {
//				handleReceiveLoginFailure(clientServerMessage);
//			}
//			
//			if(message.equals(GetMessagesInstance.LOGIN_SUCCESS())) {
//				handleReceiveLoginSuccess();
//			}
//			
//			if(message.equals(GetMessagesInstance.NEW_ACCOUNT_FAILURE())) {
//				handleReceiveNewAccountFailure(clientServerMessage);
//			}
//			
//			if(message.equals(GetMessagesInstance.NEW_ACCOUNT_SUCCESS())) {
//				handleReceiveNewAccountSuccess();
//			}
//			
//			if(message.equals(GetMessagesInstance.LEFT_GAME())) {
//				if(clientServerMessage.getData() instanceof PlayerData) { //Ensure the message was constructed correctly
//					
//					//TODO: Client1 receives message that Client2 left the game from server
//				}
//			}
			
//			if(message.equals(GetMessagesInstance.REQUEST_GAME_ROOMS())) {
//				if(clientServerMessage.getData() instanceof List<?>) {
//					List<?> data = (List<?>) clientServerMessage.getData();
//					if(data.size() >= 0 || data.get(0) instanceof GameRoomData) {
//						List<GameRoomData> gameRooms = (List<GameRoomData>) data;
//						
//						handleReceiveGameRooms((GameRoomData[]) gameRooms.toArray()); 
//					}
//				}
//			}
		}
		
	
	
	public void testObjectSend() {
		try {
			this.sendToServer(new TestObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void handleReceiveGameData(GameData game) {
		//TODO: Update board, join game?, etc.
		
	}
	
	private void handleReceiveLoginSuccess(LoginSuccess loginSuccess) {
		//setting static player data so it is retrievable at all times
		GameGUI.setPlayerData(loginSuccess.getPlayerData());
		loginController.loginSuccess();
	}
	
	private void handleReceiveLoginFailure(LoginFailure loginFailure) {
		if(loginFailure.getMessage() != null) {
			loginController.loginFailure(loginFailure.getMessage());
		} else {
			loginController.loginFailure("Failed to login! Invalid information or the database is down!");
		}
	}
	
	private void handleReceiveNewAccountSuccess() {
		createAccountController.createAccountSuccess();
	}
	
	private void handleReceiveNewAccountFailure(NewAccountFailure newAccountFailure) {
		System.out.println("Failure method reached");
		if(newAccountFailure.getMessage() != null) {
			createAccountController.createAccountFailure(newAccountFailure.getMessage());
		} else {
			createAccountController.createAccountFailure("Failed to login! Invalid information or the database is down!");
		}
	}
	
	private void handleReceiveNewGameRoomSuccess() {
		this.createGameController.createGameSuccess();
	}
	
	private void handleReceiveNewGameRoomFailure(NewGameRoomFailure newGameRoomFailure) {
		this.createGameController.createGameFailure(newGameRoomFailure.getMessage());
	}
	
	private void handleReceiveGameRooms(ArrayList<GameRoomData> rooms) {
		selectGameController.setGameRooms(rooms);
	}
	
	public void sendGameForVerification(Game game) {
		try {
			this.sendToServer(game);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPlayerLoginData(PlayerLoginData data) {
		try {
			this.sendToServer(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//this will need to be expanded on
	public void sendPlayerLogoutDataForClose(PlayerData playerData) {
		LogoutOperationForClose logoutOperation = new LogoutOperationForClose(playerData);
		try {
			this.sendToServer(logoutOperation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendPlayerNewAccountData(PlayerNewAccountData data) {
		try {
			this.sendToServer(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendNewGameRoom(NewGameRoom gameRoom) {
		try {
			this.sendToServer(gameRoom);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendLeftGame(PlayerData playerData) {
		PlayerLeftGame playerLeftGame = new PlayerLeftGame(playerData); 
		// The LEFT_GAME message needs to know which player left the game, so the server knows to inform the
										//other player that they won
		try {
			this.sendToServer(playerLeftGame);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void requestGameData() throws IOException {
		ClientServerMessage message = new ClientServerMessage(); 
		message.setMessageREQUEST_GAME_DATA();
		try {
			this.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void requestGameRooms() throws IOException {
		RequestGameRooms requestGameRooms = new RequestGameRooms();  
		try {
			this.sendToServer(requestGameRooms);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void handleSelectedGameRoom(String gameName) {
		GameRoomData gameRoomData = new GameRoomData(gameName); 
		SelectedGameRoom selectedGameRoom = new SelectedGameRoom(gameRoomData);
		try {
			this.sendToServer(selectedGameRoom);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.selectGameController.selectedGameSuccess();
		
	}


	public void sendForfeit() {
		// TODO Auto-generated method stub
		try {
			this.sendToServer(new LeftGame());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	
}
