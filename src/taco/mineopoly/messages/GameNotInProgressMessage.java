package taco.mineopoly.messages;

import taco.tacoapi.api.TacoMessage;

public class GameNotInProgressMessage extends TacoMessage {

	public GameNotInProgressMessage(){
		this.message = "&cThere isn't a Mineopoly game in progress right now";
	}
	
}
