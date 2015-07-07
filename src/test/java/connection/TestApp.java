package connection;


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

			// send message to websocket
		// clientEndPoint.sendMessage("{'event':'addChannel','channel':'ok_btccny_ticker'}");

		try {
			// wait 5 seconds for messages from websocket
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: "
					+ ex.getMessage());
		}
	}
}