package taco.mineopoly.messages;

import taco.tacoapi.api.TacoMessage;

public class CannotPerformActionMessage extends TacoMessage {
	
	public CannotPerformActionMessage(){
		this.message = "&cYou cannot do that right now";
	}
	
	public CannotPerformActionMessage(String action){
		this.message = "&cYou cannot " + action;
	}

}
