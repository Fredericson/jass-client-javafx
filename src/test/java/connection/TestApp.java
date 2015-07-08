package connection;

import javax.json.Json;

public class TestApp {

	public static void main(final String[] args) {
		// open websocket
		final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint();

		// add listener
		clientEndPoint.addMessageHandler(new MessageHandler() {
			@Override
			public void handleMessage(final String message) {
				System.out.println(message);
			}
		});

		clientEndPoint.connectToServer();

		// send message to websocket
		clientEndPoint.sendMessageString(getMessage("Frederic"));

		try {
			// wait 5 seconds for messages from websocket
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: "
					+ ex.getMessage());
		}
	}

	private static String getMessage(final String playerName) {
		return Json.createObjectBuilder().add("type", "CHOOSE_PLAYER_NAME").add("data", playerName).build().toString();
	}
}