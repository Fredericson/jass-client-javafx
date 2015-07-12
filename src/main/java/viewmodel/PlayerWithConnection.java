package viewmodel;

import java.util.Set;

import player.PlayerAction;

import com.sun.istack.internal.logging.Logger;

import connection.MessageHandler;
import connection.WebsocketClientEndpoint;
import connection.jassmessage.ChooseSessionDataValue;
import connection.jassmessage.MessageBuilder;
import connection.jassmessage.RequestMessageType;
import domain.JassTable;

public class PlayerWithConnection {

	private static final Logger LOGGER = Logger.getLogger(PlayerWithConnection.class);

	// Infrastructure
	private final WebsocketClientEndpoint connection;
	private final PlayerAction player;

	public PlayerWithConnection(final String playerName) {
		this(new JassBot(playerName));
	}

	public PlayerWithConnection(final PlayerAction player) {
		this.player = player;
		this.connection = new WebsocketClientEndpoint();
		this.connection.addMessageHandler(new JassBotMessageHandler());
	}

	private class JassBotMessageHandler implements MessageHandler {

		@Override
		public void handleMessage(final String message) {
			try {
				System.out.println(player.getName() + ": " + message);
				RequestMessageType requestMsgType = MessageBuilder.toRequestMessageType(message);
				// System.out.println(JassBot.this.getRepresentationString() + " " + requestMsg.toString());
				if (RequestMessageType.REQUEST_PLAYER_NAME.equals(requestMsgType)) {
					connection.sendPlayerName(player.getName());
				} else if (RequestMessageType.REQUEST_SESSION_CHOICE.equals(requestMsgType)) {
					connection.sendChooseSession(ChooseSessionDataValue.AUTO_JOIN);
				} else if (RequestMessageType.BROADCAST_SESSION_JOINED.equals(requestMsgType)) {
				} else if (RequestMessageType.BROADCAST_TEAMS.equals(requestMsgType)) {
					JassTable jassTable = MessageBuilder.toTable(message);
					player.receiveTeams(jassTable);
				} else if (RequestMessageType.DEAL_CARDS.equals(requestMsgType)) {
					Set<Card> cards = MessageBuilder.receiveCards(message);
					player.receiveCards(cards);
				} else if (RequestMessageType.REQUEST_TRUMPF.equals(requestMsgType)) {
					boolean geschoben = MessageBuilder.getRequestTrumpfData(message);
					Trumpf trumpf = player.chooseTrumpf(geschoben);
					connection.sendChooseTrumpf(trumpf);
				} else if (RequestMessageType.BROADCAST_TRUMPF.equals(requestMsgType)) {
					Trumpf trumpf = MessageBuilder.getBroadcastTrumpfData(message);
					player.receiveTrumpfForGame(trumpf);
				} else if (RequestMessageType.REQUEST_CARD.equals(requestMsgType)) {
					Color color = MessageBuilder.getColorOfFirstPlayedCard(message);
					Card card = player.chooseCard(color);
					connection.sendChooseCard(card);
				} else if (RequestMessageType.REJECT_CARD.equals(requestMsgType)) {
					LOGGER.info("Trumpf: " + player.getJassTableInfo().getActualTrumpf());
				} else if (RequestMessageType.PLAYED_CARDS.equals(requestMsgType)) {
					Card card = MessageBuilder.getLastPlayedCard(message);
					player.receivePlayedCard(card);
				} else if (RequestMessageType.BROADCAST_STICH.equals(requestMsgType)) {
				} else if (RequestMessageType.BROADCAST_GAME_FINISHED.equals(requestMsgType)) {
				} else if (RequestMessageType.BROADCAST_WINNER_TEAM.equals(requestMsgType)) {
				}
			} catch (Exception ex) {
				LOGGER.info(player.getName() + ": Error while receiving message: " + message, ex);
			}
		}
	}

	public void connectToServer() {
		connection.connectToServer();
	}
}
