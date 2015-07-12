package domain;

import viewmodel.PlayerOnTable;
import viewmodel.Trumpf;

public class JassTable {

	private PlayerOnTable player1;
	private PlayerOnTable player2;
	private PlayerOnTable player3;
	private PlayerOnTable player4;

	private Trumpf actualTrumpf;

	private int pointsTeam1;
	private int pointsTeam2;

	public PlayerOnTable getPlayer1() {
		return player1;
	}

	public PlayerOnTable getPlayer2() {
		return player2;
	}

	public PlayerOnTable getPlayer3() {
		return player3;
	}

	public PlayerOnTable getPlayer4() {
		return player4;
	}

	public void addTeam(final PlayerOnTable[] players) {
		addPlayer(players[0]);
		addPlayer(players[1]);
	}

	public void addPlayer(final PlayerOnTable player) {
		switch (player.getPlayerNumber()) {
		case 1:
			player1 = player;
			break;
		case 2:
			player2 = player;
			break;
		case 3:
			player3 = player;
		case 4:
			player4 = player;
			break;
		default:
			throw new IllegalStateException("Player " + player.getName() + " has invalid PlayerNumber: "
					+ player.getPlayerNumber());
		}
	}

	public int getPointsTeam1() {
		return pointsTeam1;
	}

	public void addPointsTeam1(final int points) {
		pointsTeam1 += points;
	}

	public int getPointsTeam2() {
		return pointsTeam2;
	}

	public void setPointsTeam2(final int points) {
		pointsTeam2 += points;
	}

	public Trumpf getActualTrumpf() {
		return actualTrumpf;
	}

	public void setActualTrumpf(final Trumpf actualTrumpf) {
		this.actualTrumpf = actualTrumpf;
	}
}
