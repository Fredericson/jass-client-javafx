package domain;

import viewmodel.Player;

public class Table {

	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;

	private int pointsTeam1;
	private int pointsTeam2;

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(final Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(final Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public void setPlayer3(final Player player3) {
		this.player3 = player3;
	}

	public Player getPlayer4() {
		return player4;
	}

	public void setPlayer4(final Player player4) {
		this.player4 = player4;
	}
}
