package viewmodel;

public enum Card {

	SPADE_6(Color.SPADE, Rank.SIX), SPADE_7(Color.SPADE, Rank.SEVEN), SPADE_8(
			Color.SPADE, Rank.EIGHT), SPADE_9(Color.SPADE, Rank.NINE), SPADE_10(
			Color.SPADE, Rank.TEN), SPADE_J(Color.SPADE, Rank.JACK), SPADE_Q(
			Color.SPADE, Rank.QUEEN), SPADE_K(Color.SPADE, Rank.KING), SPADE_A(
			Color.SPADE, Rank.ACE),

	HEARTH_6(Color.HEARTH, Rank.SIX), HEARTH_7(Color.HEARTH, Rank.SEVEN), HEARTH_8(
			Color.HEARTH, Rank.EIGHT), HEARTH_9(Color.HEARTH, Rank.NINE), HEARTH_10(
			Color.HEARTH, Rank.TEN), HEARTH_J(Color.HEARTH, Rank.JACK), HEARTH_Q(
			Color.HEARTH, Rank.QUEEN), HEARTH_K(Color.HEARTH, Rank.KING), HEARTH_A(
			Color.HEARTH, Rank.ACE),

	DIAMOND_6(Color.DIAMOND, Rank.SIX), DIAMOND_7(Color.DIAMOND, Rank.SEVEN), DIAMOND_8(
			Color.DIAMOND, Rank.EIGHT), DIAMOND_9(Color.DIAMOND, Rank.NINE), DIAMOND_10(
			Color.DIAMOND, Rank.TEN), DIAMOND_J(Color.DIAMOND, Rank.JACK), DIAMOND_Q(
			Color.DIAMOND, Rank.QUEEN), DIAMOND_K(Color.DIAMOND, Rank.KING), DIAMOND_A(
			Color.DIAMOND, Rank.ACE),

	CLUB_6(Color.CLUB, Rank.SIX), CLUB_7(Color.CLUB, Rank.SEVEN), CLUB_8(
			Color.CLUB, Rank.EIGHT), CLUB_9(Color.CLUB, Rank.NINE), CLUB_10(
			Color.CLUB, Rank.TEN), CLUB_J(Color.CLUB, Rank.JACK), CLUB_Q(
			Color.CLUB, Rank.QUEEN), CLUB_K(Color.CLUB, Rank.KING), CLUB_A(
			Color.CLUB, Rank.ACE);

	private Card(final Color color, final Rank rank) {
		this.color = color;
		this.rank = rank;
	}

	public Color getColor() {
		return color;
	}

	public Rank getRank() {
		return rank;
	}

	private final Color color;
	private final Rank rank;

}
