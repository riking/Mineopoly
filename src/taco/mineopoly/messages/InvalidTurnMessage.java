package taco.mineopoly.messages;

import taco.tacoapi.api.TacoMessage;

public class InvalidTurnMessage extends TacoMessage {

	public InvalidTurnMessage(){
		this.message = "&cIt is not your turn";
	}
	
}
