package connection;

import org.junit.Before;
import org.junit.Test;

import viewmodel.JassBot;

public class DealCardsTest {

	private JassBot bot1;
	private JassBot bot2;
	private JassBot bot3;
	private JassBot bot4;

	@Before
	public void setup() {
		this.bot1 = new JassBot("bot1");
		this.bot2 = new JassBot("bot2");
		this.bot3 = new JassBot("bot3");
		this.bot4 = new JassBot("bot4");
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
			Thread.sleep(10000);
		} catch (InterruptedException ex) {
			System.err.println("InterruptedException exception: " + ex.getMessage());
		}

	}
}
