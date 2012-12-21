package taco.mineopoly.cards.chance;

import taco.mineopoly.cards.MineopolyCardSet;

public class ChanceCardSet extends MineopolyCardSet{

	@Override
	protected void initCards() {
		super.addCard(new FoundAnEnderPearlCard());
		super.addCard(new ThrewEnderPearlWrongWayCard());
	}

}
