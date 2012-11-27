package taco.mineopoly.cards.chance;

import taco.mineopoly.cards.MineopolyCardSet;

public class ChanceCardSet extends MineopolyCardSet{

	@Override
	protected void initCards() {
		addCard(new FoundAnEnderPearlCard());
	}

}
