package taco.mineopoly.cards.communitychest;

import taco.mineopoly.cards.MineopolyCard;

public class CommunityChestCard extends MineopolyCard {

	protected String description;
	
	public CommunityChestCard(String description, String action){
		super("&eCommunity Chest", description, action);
	}
	
}
