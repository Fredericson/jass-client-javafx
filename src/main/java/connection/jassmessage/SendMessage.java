package connection.jassmessage;

public class SendMessage {

	private final SendMessageType type;
	private final String data;

	public SendMessage(final SendMessageType type, final String data) {
		this.type = type;
		this.data = data;
	}

	public SendMessageType getType() {
		return type;
	}

	public String getData() {
		return data;
	}
}
