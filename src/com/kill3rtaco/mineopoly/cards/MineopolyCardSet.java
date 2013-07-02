package com.kill3rtaco.mineopoly.cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.kill3rtaco.mineopoly.MineopolyPlayer;


public abstract class MineopolyCardSet implements Iterable<MineopolyCard> {

	protected ArrayList<MineopolyCard> cards;
	
	public MineopolyCardSet(){
		initCards();
		addJailCard();
	}
	
	protected abstract void initCards();

	protected abstract void addJailCard();
	
	protected void addCard(MineopolyCard card){
		cards.add(card);
	}
	
	public void drawCard(MineopolyPlayer player){
		int cardToTake = new Random().nextInt(cards.size());
		MineopolyCard card = cards.get(cardToTake);
		card.readDescription(player);
		card.action(player);
		cards.remove(card);
		if(cards.size() <= 0) initCards();
	}
	
	@Override
	public Iterator<MineopolyCard> iterator() {
		return cards.iterator();
	}
	

}
