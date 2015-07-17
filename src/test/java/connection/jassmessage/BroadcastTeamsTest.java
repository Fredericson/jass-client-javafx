package connection.jassmessage;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import viewmodel.PlayerOnTable;
import domain.JassTable;
import domain.Team;

public class BroadcastTeamsTest {

	// {"type":"BROADCAST_TEAMS","data":[{"name":"Team 1","players":[{"name":"bot1","id":0},{"name":"bot3","id":2}]},{"name":"Team 2","players":[{"name":"bot2","id":1},{"name":"bot4","id":3}]}]}
	private static final String message = "{\"type\":\"BROADCAST_TEAMS\",\"data\":[{\"name\":\"Team 1\",\"players\":[{\"name\":\"bot1\",\"id\":0},{\"name\":\"bot3\",\"id\":2}]},{\"name\":\"Team 2\",\"players\":[{\"name\":\"bot2\",\"id\":1},{\"name\":\"bot4\",\"id\":3}]}]}";

	private static JassTable jassTable;

	@BeforeClass
	public static void setup() {
		jassTable = MessageBuilder.getBroadcastTeams(message);
	}

	@Test
	public void verifyPlayer1() {
		PlayerOnTable player1 = jassTable.getPlayer1();
		Assert.assertEquals("bot1", player1.getName());
		Assert.assertEquals(Team.TEAM_1, player1.getTeam());
		Assert.assertEquals(1, player1.getPlayerNumber());
	}

	@Test
	public void verifyPlayer2() {
		PlayerOnTable player2 = jassTable.getPlayer2();
		Assert.assertEquals("bot2", player2.getName());
		Assert.assertEquals(Team.TEAM_2, player2.getTeam());
		Assert.assertEquals(2, player2.getPlayerNumber());
	}

	@Test
	public void verifyPlayer3() {
		PlayerOnTable player3 = jassTable.getPlayer3();
		Assert.assertEquals("bot3", player3.getName());
		Assert.assertEquals(Team.TEAM_1, player3.getTeam());
		Assert.assertEquals(3, player3.getPlayerNumber());
	}

	@Test
	public void verifyPlayer4() {
		PlayerOnTable player4 = jassTable.getPlayer4();
		Assert.assertEquals("bot4", player4.getName());
		Assert.assertEquals(Team.TEAM_2, player4.getTeam());
		Assert.assertEquals(4, player4.getPlayerNumber());
	}

}
