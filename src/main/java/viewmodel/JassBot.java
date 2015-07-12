package viewmodel;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import player.PlayerAction;

public class JassBot extends PlayerAction {

	Set<Card> rememberPlayedCards = new HashSet<Card>();

	public JassBot(final String playerName) {
		super(playerName);
	}

	@Override
	public Card chooseCard(final Color requestedColor) {
		if (getTrumpfCards().size() > 0 && overtrumpf()) {
			return pickingRandomCard(getTrumpfCards());
		}
		Set<Card> cardsForRequestedColor = getOtherCards(requestedColor);
		if (requestedColor == null || cardsForRequestedColor.size() == 0) {
			return pickingRandomCard(getOtherCards());
		}
		return pickingRandomCard(cardsForRequestedColor);
	}

	private boolean overtrumpf() {
		return true;
		// Ace or Ten and my Trumpf is higher
		// Person behind didn't choose Trumpf
		// do not give Pawn if I can get Nell (I will loose all other stich)
	}

	@Override
	public Trumpf chooseTrumpf(final boolean geschoben) {

		Trumpf[] trumpfValues = Trumpf.values();
		int item = new Random().nextInt(trumpfValues.length);
		if (item == trumpfValues.length) {
			return null;
		}
		return trumpfValues[item];
		// int topDownStich countTopDownStich();
		// int possibleTopDownStich possibleTopDownStich();
		// TODO Auto-generated method stub
	}

	@Override
	public void rememberPlayedCard(final Card card) {
		rememberPlayedCards.add(card);
	}

	private Card pickingRandomCard(final Set<Card> cards) {
		int size = cards.size();
		int item = new Random().nextInt(size);
		int i = 0;
		for (Card card : cards) {
			if (i == item) {
				return card;
			}
			i = i + 1;
		}
		return null;
	}
}
