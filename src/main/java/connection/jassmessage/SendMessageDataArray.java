package connection.jassmessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SendMessageDataArray {

	private final SendMessageType type;
	private final List<Map<String, String>> data = new ArrayList<Map<String, String>>();

	public SendMessageDataArray(final SendMessageType type) {
		this.type = type;
	}

	public SendMessageType getType() {
		return type;
	}

	public void addData(final Map<String, String> map) {
		data.add(map);
	}

	public List<Map<String, String>> getData() {
		return data;
	}
}
