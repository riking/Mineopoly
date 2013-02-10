package taco.mineopoly.messages;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.MineopolySection;
import taco.mineopoly.sections.OwnableSection;
import taco.tacoapi.api.TacoMessage;

public class SectionAlreadyOwnedMessage extends TacoMessage {

	public SectionAlreadyOwnedMessage(MineopolySection section){
		OwnableSection space = (OwnableSection) section;
		MineopolyPlayer owner = space.getOwner();
		if(owner.getName().equalsIgnoreCase(Mineopoly.plugin.getGame().getPlayerWithCurrentTurn().getName()))
			this.message = "&cYou already own the space " + section.getColorfulName();
		else
			this.message = "&cThe space " + section.getColorfulName() + " &cis already owned by &6" + space.getOwner().getName();
	}
	
}
