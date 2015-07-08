package viewmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import connection.WebsocketClientEndpoint;
import domain.Team;

public class Player {

	// Infrastructure
	protected final WebsocketClientEndpoint connection;

	private final String playerName;
	private Team team;
	private Card playedCard;
	protected List<Card> cards = new ArrayList<Card>();

	public Player(final String playerName) {
		this.playerName = playerName;
		this.connection = new WebsocketClientEndpoint();
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public void setCards(final Card[] cards) {
		this.cards = Arrays.asList(cards);
	}

	public void setTeam(final Team team) {
		this.team = team;
	}

	public void playCard(final Card card) {
		this.cards.remove(card);
		this.playedCard = card;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getRepresentationString() {
		return playerName + " " + team;
	}

	@Override
	public String toString() {
		return playerName + " " + team;
	}
}
