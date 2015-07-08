package connection;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import connection.jassmessage.ChooseSessionDataValue;
import connection.jassmessage.MessageBuilder;
import connection.jassmessage.SendMessage;
import connection.jassmessage.SendMessageDataArray;
import connection.jassmessage.SendMessageDataMap;
import connection.jassmessage.SendMessageType;

/**
 * JassServer Client
 *
 */
@ClientEndpoint
public class WebsocketClientEndpoint {

	Session userSession = null;
	private MessageHandler messageHandler;

	public void connectToServer() {
		try {
			URI endpointURI = PropertyFileHelper.getJassServerEndpointUri();
			WebSocketContainer container = ContainerProvider
					.getWebSocketContainer();
			container.connectToServer(this, endpointURI);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Callback hook for Connection open events.
	 *
	 * @param userSession
	 *            the userSession which is opened.
	 */
	@OnOpen
	public void onOpen(final Session userSession) {
		System.out.println("opening websocket");
		this.userSession = userSession;
	}

	/**
	 * Callback hook for Connection close events.
	 *
	 * @param userSession
	 *            the userSession which is getting closed.
	 * @param reason
	 *            the reason for connection close
	 */
	@OnClose
	public void onClose(final Session userSession, final CloseReason reason) {
		System.out.println("closing websocket");
		this.userSession = null;
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a
	 * client send a message.
	 *
	 * @param message
	 *            The text message
	 */
	@OnMessage
	public void onMessage(final String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	/**
	 * register message handler
	 *
	 * @param msgHandler
	 */
	public void addMessageHandler(final MessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}

	/**
	 * Send a message.
	 *
	 * @param message
	 */
	private void sendMessage(final SendMessage sendMessage) {
		sendMessageString(MessageBuilder.toJSONString(sendMessage));
	}

	/**
	 * Send a message.
	 *
	 * @param message
	 */
	private void sendMessage(final SendMessageDataArray sendMessage) {
		sendMessageString(MessageBuilder.toJSONString(sendMessage));
	}

	/**
	 * Send a message.
	 *
	 * @param message
	 */
	private void sendMessage(final SendMessageDataMap sendMessageDataMap) {
		sendMessageString(MessageBuilder.toJSONString(sendMessageDataMap));
	}

	public void sendMessageString(final String msg) {
		System.out.println("send: " + msg);
		this.userSession.getAsyncRemote().sendText(msg);
	}

	/**
	 * Send a playerName.
	 *
	 * @param name
	 * @param message
	 */
	public void sendPlayerName(final String playerName) {
		SendMessage sendMsg = new SendMessage(SendMessageType.CHOOSE_PLAYER_NAME, playerName);
		sendMessage(sendMsg);
	}

	public void sendChooseSession(final ChooseSessionDataValue chooseSessionDataValue) {
		SendMessageDataMap sendMsg = new SendMessageDataMap(SendMessageType.CHOOSE_SESSION);
		sendMsg.addData(ChooseSessionDataValue.DATA_TYPE, chooseSessionDataValue.name());
		sendMessage(sendMsg);
	}

}