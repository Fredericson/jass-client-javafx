package connection.jassmessage;

import java.util.List;
import java.util.Map;

public class SendMessageDataArray {

	private final SendMessageType type;
	private final MessageDataArray msgDataArray;

	public SendMessageDataArray(final SendMessageType type) {
		this.type = type;
		this.msgDataArray = new MessageDataArray();
	}

	public SendMessageType getType() {
		return type;
	}

	public void addData(final Map<String, String> map) {
		msgDataArray.addData(map);
	}

	public List<Map<String, String>> getData() {
		return msgDataArray.getData();
	}
}
