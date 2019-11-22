package Communication.ClientCommunication;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import Communication.ClientServerMessage;
import Communication.ServerCommunication.GameRoomData;
import Communication.ServerCommunication.PlayerLoginData;
import Communication.ServerCommunication.PlayerNewAccountData;
import Game.Game;
import Game.PlayerData;
import GameUI.CreateAccountController;
import GameUI.CreateGameController;
import GameUI.GameController;
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
		
		if(object instanceof ClientServerMessage) {
			ClientServerMessage message = (ClientServerMessage) object;
			
			if(message == ClientServerMessage.GAME_DATA) {
				if(message.getData() instanceof Game) {
					handleReceiveGameData((Game) message.getData());
				}
			}
			
			if(message == ClientServerMessage.LOGIN_FAILURE) {
				handleReceiveLoginFailure(message);
			}
			
			if(message == ClientServerMessage.LOGIN_SUCCESS) {
				handleReceiveLoginSuccess();
			}
			
			if(message == ClientServerMessage.NEW_ACCOUNT_FAILURE) {
				handleReceiveNewAccountFailure(message);
			}
			
			if(message == ClientServerMessage.NEW_ACCOUNT_SUCCESS) {
				handleReceiveNewAccountSuccess();
			}
			
			if(message == ClientServerMessage.LEFT_GAME) {
				if(message.getData() instanceof PlayerData) { //Ensure the message was constructed correctly
					
					//TODO: Client1 receives message that Client2 left the game from server
				}
			}
			
			if(message == ClientServerMessage.REQUEST_GAME_ROOMS) {
				if(message.getData() instanceof List<?>) {
					List<?> data = (List<?>) message.getData();
					if(data.size() >= 0 || data.get(0) instanceof GameRoomData) {
						List<GameRoomData> gameRooms = (List<GameRoomData>) data;
						
						handleReceiveGameRooms((GameRoomData[]) gameRooms.toArray()); 
					}
				}
			}
		}
		
	}
	
	private void handleReceiveGameData(Game game) {
		//TODO: Update board, join game?, etc.
		
	}
	
	private void handleReceiveLoginSuccess() {
		loginController.loginSuccess();
	}
	
	private void handleReceiveLoginFailure(ClientServerMessage message) {
		if(message.getData() != null && message.getData() instanceof String) {
			loginController.loginFailure((String) message.getData());
		} else {
			loginController.loginFailure("Failed to login! Invalid information or the database is down!");
		}
	}
	
	private void handleReceiveNewAccountSuccess() {
		createAccountController.createAccountSuccess();
	}
	
	private void handleReceiveNewAccountFailure(ClientServerMessage message) {
		if(message.getData() != null && message.getData() instanceof String) {
			createAccountController.createAccountFailure((String) message.getData());
		} else {
			createAccountController.createAccountFailure("Failed to login! Invalid information or the database is down!");
		}
	}
	
	private void handleReceiveGameRooms(GameRoomData[] rooms) {
		selectGameController.setGameRooms(rooms);
	}
	
	public void sendGameForVerification(Game game) {
		ClientServerMessage message = ClientServerMessage.GAME_DATA;
		message.setData(game);
		try {
			this.sendToServer(game);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPlayerLoginData(PlayerLoginData data) {
		ClientServerMessage message = ClientServerMessage.LOGIN;
		message.setData(data);
		try {
			this.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendPlayerNewAccountData(PlayerNewAccountData data) {
		ClientServerMessage message = ClientServerMessage.NEW_ACCOUNT;
		message.setData(data);
		try {
			this.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendNewGameRoom(GameRoomData gameRoom) {
		ClientServerMessage message = ClientServerMessage.NEW_GAME_ROOM;
		message.setData(gameRoom);
		try {
			this.sendToServer(gameRoom);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendLeftGame(PlayerData playerData) {
		ClientServerMessage message = ClientServerMessage.LEFT_GAME;
		message.setData(playerData); // The LEFT_GAME message needs to know which player left the game, so the server knows to inform the
										//other player that they won
		try {
			this.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void requestGameData() throws IOException {
		ClientServerMessage message = ClientServerMessage.REQUEST_GAME_DATA;
		try {
			this.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void requestGameRooms() throws IOException {
		ClientServerMessage message = ClientServerMessage.REQUEST_GAME_ROOMS;
		try {
			this.sendToServer(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
