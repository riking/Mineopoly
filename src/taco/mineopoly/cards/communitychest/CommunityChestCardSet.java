package taco.mineopoly.cards.communitychest;

import taco.mineopoly.cards.MineopolyCardSet;

public class CommunityChestCardSet extends MineopolyCardSet{

	@Override
	protected void initCards() {
		super.addCard(new AdvanceToGoCard());
		super.addCard(new VaultErrorCard());
	}

}
