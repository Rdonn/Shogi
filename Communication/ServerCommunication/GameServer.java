package Communication.ServerCommunication;

import java.io.IOException;

import Communication.ClientServerMessage;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {

	public GameServer() {
		super(8300);
	}

	public GameServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object object, ConnectionToClient connection) {

		if (object instanceof ClientServerMessage) {

			ClientServerMessage message = (ClientServerMessage) object;

			Object data = message.getData();

			switch (message) {
			case LOGIN:

				if (data instanceof PlayerLoginData) {

					// TODO: Verify login information with database.
					// For now, just assume it's correct.
					try {
						connection.sendToClient(ClientServerMessage.LOGIN_SUCCESS);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				break;

			case NEW_ACCOUNT:

				if (data instanceof PlayerNewAccountData) {

					// TODO: Database stuff
					// For now, assume new account created.
					try {
						connection.sendToClient(ClientServerMessage.NEW_ACCOUNT_SUCCESS);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			default:
				break;
			}

		}

	}

}
