package viewmodel;

import connection.MessageHandler;
import connection.jassmessage.ChooseSessionDataValue;
import connection.jassmessage.MessageBuilder;
import connection.jassmessage.RequestMessage;
import connection.jassmessage.RequestMessageType;

public class JassBot extends Player {

	public JassBot(final String playerName) {
		super(playerName);
		this.connection.addMessageHandler(new JassBotMessageHandler());
	}

	private class JassBotMessageHandler implements MessageHandler {

		@Override
		public void handleMessage(final String message) {
			System.out.println(message);
			RequestMessage requestMsg = MessageBuilder.toJassMessage(message);
			System.out.println(JassBot.this.getRepresentationString() + " " + requestMsg.toString());
			RequestMessageType type = requestMsg.getType();
			if (RequestMessageType.REQUEST_PLAYER_NAME.equals(type)) {
				connection.sendPlayerName(getPlayerName());
			} else if (RequestMessageType.REQUEST_SESSION_CHOICE.equals(type)) {
				connection.sendChooseSession(ChooseSessionDataValue.AUTO_JOIN);
			}
		}
	}

	public void connectToServer() {
		connection.connectToServer();
	}

}
