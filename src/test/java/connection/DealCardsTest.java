package connection;

import org.junit.Before;
import org.junit.Test;

import viewmodel.PlayerWithConnection;

public class DealCardsTest {

	private PlayerWithConnection bot1;
	private PlayerWithConnection bot2;
	private PlayerWithConnection bot3;
	private PlayerWithConnection bot4;

	@Before
	public void setup() {
		this.bot1 = new PlayerWithConnection("bot1");
		this.bot2 = new PlayerWithConnection("bot2");
		this.bot3 = new PlayerWithConnection("bot3");
		this.bot4 = new PlayerWithConnection("bot4");
	}

	@Test
	public void executeTest() {
		try {
			bot1.connectToServer();
			Thread.sleep(1000);
			bot2.connectToServer();
			// Thread.sleep(1000);
			bot3.connectToServer();
			// Thread.sleep(1000);
			bot4.connectToServer();
			// wait 10 seconds for messages from websocket
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		}

	}
}
