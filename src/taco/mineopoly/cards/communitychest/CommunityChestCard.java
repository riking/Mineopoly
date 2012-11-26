package taco.mineopoly.cards.communitychest;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public abstract class CommunityChestCard {

	protected String description;
	
	public String getDescription(){
		return this.description;
	}
	
	public void readDescription(MineopolyPlayer player){
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + player.getName() + " &3drew a &eCommunity Chest &3card, it says:");
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + description);
	}
	
	public abstract void action(MineopolyPlayer player);
	
}
