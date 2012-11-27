package taco.mineopoly.cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import taco.mineopoly.MineopolyPlayer;

public abstract class MineopolyCardSet implements Iterable<MineopolyCard> {

	protected ArrayList<MineopolyCard> allCards, availableCards;
	
	public MineopolyCardSet(){
		allCards = new ArrayList<MineopolyCard>();
		availableCards = new ArrayList<MineopolyCard>();
	}
	
	protected abstract void initCards();
	
	protected void addCard(MineopolyCard card){
		allCards.add(card);
		availableCards.add(card);
	}
	
	public void drawCard(MineopolyPlayer player){
		int cardToTake = new Random().nextInt(availableCards.size());
		MineopolyCard card = availableCards.get(cardToTake);
		card.readDescription(player);
		card.action(player);
		availableCards.remove(cardToTake);
		if(availableCards.size() <= 0)
			resetCards();
	}
	
	public void resetCards(){
		availableCards = allCards;
	}
	
	public int getAvailableCardCount(){
		return availableCards.size();
	}
	
	@Override
	public Iterator<MineopolyCard> iterator() {
		return allCards.iterator();
	}

}
