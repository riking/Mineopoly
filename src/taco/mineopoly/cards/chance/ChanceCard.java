package taco.mineopoly.cards.chance;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public abstract class ChanceCard {

	protected String description;
	
	
	public String getDescription(){
		return this.description;
	}
	
	public void readDescription(MineopolyPlayer player){
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + player.getName() + " &3drew a &6Chance &3card, it says:");
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + description);
	}
	
	public abstract void action(MineopolyPlayer player);
	
}
