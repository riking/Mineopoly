package taco.mineopoly.cards;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public abstract class MineopolyCard {

	private String description, name;
	
	public MineopolyCard(String description, String name) {
		this.description = description;
		this.name = name;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void readDescription(MineopolyPlayer player){
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + player.getName() + " &3drew a " + name + " &3card, it says:");
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + description);
	}
	
	public abstract void action(MineopolyPlayer player);

}
