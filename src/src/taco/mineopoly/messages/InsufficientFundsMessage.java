package taco.mineopoly.messages;

import taco.tacoapi.api.TacoMessage;

public class InsufficientFundsMessage extends TacoMessage{

	public InsufficientFundsMessage(){
		this.message = "&cYou do not have enough money";
	}
	
}
