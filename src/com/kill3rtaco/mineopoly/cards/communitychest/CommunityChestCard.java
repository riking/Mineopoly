package com.kill3rtaco.mineopoly.cards.communitychest;

import com.kill3rtaco.mineopoly.cards.MineopolyCard;

public class CommunityChestCard extends MineopolyCard {

	protected String description;
	
	public CommunityChestCard(String description, String action){
		super("&eCommunity Chest", description, action);
	}
	
}
