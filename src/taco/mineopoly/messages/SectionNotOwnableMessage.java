package taco.mineopoly.messages;

import taco.mineopoly.sections.MineopolySection;
import taco.tacoapi.api.TacoMessage;

public class SectionNotOwnableMessage extends TacoMessage {

	public SectionNotOwnableMessage(MineopolySection section, String action){
		this.message = "&cYou cannot " + action + " the space &6" + section.getColorfulName();
	}
	
	
}
