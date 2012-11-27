package taco.mineopoly.cards.communitychest;

import taco.mineopoly.cards.MineopolyCardSet;

public class CommunityChestCardSet extends MineopolyCardSet{

	@Override
	protected void initCards() {
		addCard(new AdvanceToGoCard());
	}

}
