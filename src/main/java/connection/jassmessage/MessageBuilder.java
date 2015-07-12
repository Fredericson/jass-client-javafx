package connection.jassmessage;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import viewmodel.Card;
import viewmodel.Color;
import viewmodel.PlayerOnTable;
import viewmodel.Rank;
import viewmodel.Trumpf;
import connection.mapping.CardColorMapper;
import connection.mapping.CardNumberMapper;
import domain.JassTable;
import domain.Team;

public class MessageBuilder {

	private static final String FIELD_TYPE = "type";
	private static final String FIELD_DATA = "data";

	public static RequestMessageType toRequestMessageType(final String requestMsg) {

		JsonObject jsonObject = Json.createReader(new StringReader(requestMsg)).readObject();
		String typeString = jsonObject.getString(FIELD_TYPE);
		RequestMessageType type = RequestMessageType.valueOf(typeString);
		if (type == null) {
			throw new IllegalArgumentException("Unknown RequestMessageType: " + typeString);
		}
		return type;
	}

	public static String toJSONString(final SendMessage sendMessage) {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder().add(FIELD_TYPE, sendMessage.getType().name());
		if (sendMessage.getData() != null) {
			jsonBuilder = jsonBuilder.add(FIELD_DATA, sendMessage.getData());
		}
		return jsonBuilder.build().toString();
	}

	public static String toJSONString(final SendMessageDataMap sendMessage) {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder().add(FIELD_TYPE, sendMessage.getType().name())
				.add(FIELD_DATA, createJsonObjBuilder(sendMessage.getData()));
		return jsonBuilder.build().toString();

	}

	private static JsonObjectBuilder createJsonObjBuilder(final Map<String, Object> map) {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		for (Entry<String, Object> entry : map.entrySet()) {
			Object value = entry.getValue();
			if (value instanceof Integer) {
				jsonObjBuilder.add(entry.getKey(), (int) value);
			} else {
				jsonObjBuilder.add(entry.getKey(), (String) value);
			}
		}
		return jsonObjBuilder;
	}

	public static JassTable toTable(final String message) {
		JassTable table = new JassTable();
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		JsonArray jsonArray = jsonObject.getJsonArray(FIELD_DATA);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObjectMap = jsonArray.getJsonObject(i);
			String teamName = jsonObjectMap.getString("name");
			Team team = Team.getTeam(teamName);
			PlayerOnTable[] players = getPlayers(team, jsonObjectMap, "players");
			table.addTeam(players);
		}
		return table;
	}

	private static PlayerOnTable[] getPlayers(final Team team, final JsonObject jsonObject, final String field) {
		PlayerOnTable[] players = new PlayerOnTable[2];
		JsonArray jsonArray = jsonObject.getJsonArray(field);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObjectMap = jsonArray.getJsonObject(i);
			String playerName = jsonObjectMap.getString("name");
			int playerNumber = jsonObjectMap.getInt("id") + 1;
			PlayerOnTable player = new PlayerOnTable(playerName, team, playerNumber);
			players[i] = player;
		}
		return players;
	}

	public static Set<Card> receiveCards(final String message) {
		Set<Card> cards = new HashSet<Card>();
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		JsonArray jsonArray = jsonObject.getJsonArray(FIELD_DATA);
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject jsonObjectMap = jsonArray.getJsonObject(i);
			Card card = getCard(jsonObjectMap);
			cards.add(card);
		}
		return cards;
	}

	private static Card getCard(final JsonObject jsonObjectMap) {
		int number = jsonObjectMap.getInt(CardNumberMapper.PROPERTY_NAME);
		Rank rank = CardNumberMapper.getRank(number);
		String colorString = jsonObjectMap.getString(CardColorMapper.PROPERTY_NAME);
		Color color = Color.valueOf(colorString);
		return Card.getCard(color, rank);
	}

	public static boolean getRequestTrumpfData(final String message) {
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		return jsonObject.getBoolean(FIELD_DATA);
	}

	public static Trumpf getBroadcastTrumpfData(final String message) {
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		JsonObject jsonObjectMap = jsonObject.getJsonObject(FIELD_DATA);
		String modeString = jsonObjectMap.getString(ChooseTrumpfModeDataValue.PROPERTY_NAME);
		ChooseTrumpfModeDataValue mode = ChooseTrumpfModeDataValue.valueOf(modeString);
		if (ChooseTrumpfModeDataValue.SCHIEBE.equals(mode)) {
			return null;
		} else if (ChooseTrumpfModeDataValue.OBEABE.equals(mode)) {
			return Trumpf.TOPDOWN;
		} else if (ChooseTrumpfModeDataValue.UNDEUFE.equals(mode)) {
			return Trumpf.BUTTOMUP;
		} else if (ChooseTrumpfModeDataValue.TRUMPF.equals(mode)) {
			String colorString = jsonObjectMap.getString(ChooseTrumpfColorDataValue.PROPERTY_NAME);
			Color color = Color.valueOf(colorString);
			return Trumpf.getTrumpf(color);
		}

		throw new IllegalStateException("Trumpf mode " + mode + " could not be parsed!");
	}

	public static Color getColorOfFirstPlayedCard(final String message) {
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		JsonArray jsonArray = jsonObject.getJsonArray(FIELD_DATA);
		if (jsonArray.size() == 0) {
			return null;
		}
		// the color of the first card this is the color the player has to play
		JsonObject jsonObjectMap = jsonArray.getJsonObject(0);
		String colorString = jsonObjectMap.getString(CardColorMapper.PROPERTY_NAME);
		return Color.valueOf(colorString);
	}

	public static Card getLastPlayedCard(final String message) {
		JsonObject jsonObject = Json.createReader(new StringReader(message)).readObject();
		JsonArray jsonArray = jsonObject.getJsonArray(FIELD_DATA);
		JsonObject jsonObjectMap = jsonArray.getJsonObject(jsonArray.size() - 1);
		return getCard(jsonObjectMap);
	}

}
