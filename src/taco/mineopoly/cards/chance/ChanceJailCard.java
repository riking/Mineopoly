package taco.mineopoly.cards.chance;

import taco.mineopoly.MineopolyPlayer;

public class ChanceJailCard extends ChanceCard {

	public ChanceJailCard() {
		super("Get out of Jail Free card. You may use this card to get out of jail");
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.giveChanceJailCard();
	}

}
