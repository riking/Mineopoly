package taco.mineopoly.messages;

import taco.tacoapi.api.TacoMessage;

public class SectionNotFoundMessage extends TacoMessage {

	public SectionNotFoundMessage() {
		this.message = "Space on board not found";
	}

}
