package taco.mineopoly.cards.communitychest;

import taco.mineopoly.cards.MineopolyCard;

public abstract class CommunityChestCard extends MineopolyCard {

	protected String description;
	
	public CommunityChestCard(String description){
		super(description, "&eCommunity Chest");
	}
	
}
