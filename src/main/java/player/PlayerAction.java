package player;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import viewmodel.Card;
import viewmodel.Color;
import viewmodel.Player;
import viewmodel.Trumpf;
import domain.JassTable;
import domain.PlayerNumber;
import domain.SchieberStich;

public abstract class PlayerAction extends Player {

	// Each Player has information about what is visible on the Table
	protected JassTable jassTableInfo;
	private final Set<Card> trumpfCards = new HashSet<Card>();
	private final Set<Card> otherCards = new HashSet<Card>();

	public PlayerAction(final String name) {
		super(name);
	}

	public JassTable getJassTableInfo() {
		return jassTableInfo;
	}

	public void receiveTeams(final JassTable jassTableInfo) {
		this.jassTableInfo = jassTableInfo;
	}

	public void receiveCards(final Set<Card> cards) {
		// trumpfCards.clear();
		// otherCards.clear();
		this.otherCards.addAll(cards);
	}

	public void receiveTrumpfForGame(final Trumpf trumpf) {
		jassTableInfo.setActualTrumpf(trumpf);
		Color trumpfColor = trumpf.getColor();
		if (trumpfColor == null) {
			return;
		}
		trumpfCards.addAll(getOtherCards(trumpfColor));
		otherCards.removeAll(trumpfCards);
	}

	public Card getHighestTrumpfCard() {
		Card highest = null;
		for (Card card : trumpfCards) {
			if (highest == null || card.getRank().ordinal() > highest.getRank().ordinal()) {
				highest = card;
			}
		}
		return highest;
	}

	public Set<Card> getTrumpfCards() {
		return Collections.unmodifiableSet(trumpfCards);
	}

	protected Set<Card> getOtherCards() {
		return Collections.unmodifiableSet(otherCards);
	}

	public Set<Card> getAllCards() {
		Set<Card> cards = new HashSet<Card>(trumpfCards);
		cards.addAll(otherCards);
		return cards;
	}

	protected Set<Card> getOtherCards(final Color requestedColor) {
		Set<Card> cardsForRequestedColor = new HashSet<Card>();
		for (Card card : otherCards) {
			if (requestedColor == card.getColor()) {
				cardsForRequestedColor.add(card);
			}
		}
		return cardsForRequestedColor;
	}

	/**
	 * If the Player is the first in the round
	 * 
	 * @param requestedColor
	 * @return Card
	 */
	public abstract Card chooseCard(Color requestedColor);

	/**
	 * Return null means shifted.
	 * 
	 * @param geschoben
	 * @return Trumpf
	 */
	public abstract Trumpf chooseTrumpf(boolean geschoben);

	public void receivePlayedCard(final Card card) {
		trumpfCards.remove(card);
		otherCards.remove(card);
		rememberPlayedCard(card);
		jassTableInfo.setPlayedCard(card);
	}

	protected abstract void rememberPlayedCard(Card card);

	public void setActualTrumpfablePlayer(final PlayerNumber actualTrumpfablePlayer) {
		this.jassTableInfo.setActualTrumpfablePlayer(actualTrumpfablePlayer);
	}

	public void setStich(final SchieberStich stich) {
		this.jassTableInfo.setStich(stich);
	}
}
