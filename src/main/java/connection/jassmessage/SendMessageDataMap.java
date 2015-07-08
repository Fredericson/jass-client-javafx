package connection.jassmessage;

import java.util.HashMap;
import java.util.Map;

public class SendMessageDataMap {

	private final SendMessageType type;
	private final Map<String, String> data = new HashMap<String, String>();

	public SendMessageDataMap(final SendMessageType type) {
		this.type = type;
	}

	public SendMessageType getType() {
		return type;
	}

	public void addData(final String key, final String value) {
		data.put(key, value);
	}

	public Map<String, String> getData() {
		return data;
	}
}
