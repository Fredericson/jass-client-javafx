package connection.jassmessage;

import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class MessageBuilder {

	private static final String FIELD_TYPE = "type";
	private static final String FIELD_DATA = "data";

	public static RequestMessage toJassMessage(final String requestMsg) {
		JsonObject jsonObject = Json.createReader(new StringReader(requestMsg)).readObject();
		String typeString = jsonObject.getString(FIELD_TYPE);
		String dataString = null;
		try {
			dataString = jsonObject.getString(FIELD_DATA);
		} catch (Exception ex) {
			// do nothing
		}

		RequestMessageType type = RequestMessageType.valueOf(typeString);
		if (type == null) {
			throw new IllegalArgumentException("Unknown RequestMessageType: " + typeString);
		}
		return new RequestMessage(type, dataString);
	}

	public static String toJSONString(final SendMessage sendMessage) {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder().add("type", sendMessage.getType().name());
		if (sendMessage.getData() != null) {
			jsonBuilder = jsonBuilder.add("data", sendMessage.getData());
		}
		return jsonBuilder.build().toString();
	}

	public static String toJSONString(final SendMessageDataMap sendMessage) {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder().add("type", sendMessage.getType().name())
				.add("data", createJsonObjBuilder(sendMessage.getData()));
		return jsonBuilder.build().toString();

	}

	public static String toJSONString(final SendMessageDataArray sendMessageDataArray) {
		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder().add("type", sendMessageDataArray.getType().name());
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		for (Map<String, String> map : sendMessageDataArray.getData()) {
			JsonObjectBuilder data = createJsonObjBuilder(map);
			jsonArray.add(data);
		}
		jsonBuilder = jsonBuilder.add("data", jsonArray);
		return jsonBuilder.build().toString();
	}

	private static JsonObjectBuilder createJsonObjBuilder(final Map<String, String> map) {
		JsonObjectBuilder jsonObjBuilder = Json.createObjectBuilder();
		for (Entry<String, String> entry : map.entrySet()) {
			jsonObjBuilder.add(entry.getKey(), entry.getValue());
		}
		return jsonObjBuilder;
	}
}
