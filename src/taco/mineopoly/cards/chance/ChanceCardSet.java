package taco.mineopoly.cards.chance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import taco.mineopoly.MineopolyPlayer;

public class ChanceCardSet implements Iterable<ChanceCard>{

	private ArrayList<ChanceCard> allCards = new ArrayList<ChanceCard>();
	private ArrayList<ChanceCard> availableCards = new ArrayList<ChanceCard>();
	
	public ChanceCardSet(){
		initCards();
	}
	
	private void initCards(){ //order of addition not important
		addCard(new FoundAnEnderPearlCard());
	}
	
	private void addCard(ChanceCard card){
		allCards.add(card);
		availableCards.add(card);
	}
	
	public void drawCard(MineopolyPlayer player){
		int cardToTake = new Random().nextInt(availableCards.size());
		ChanceCard card = availableCards.get(cardToTake);
		card.readDescription(player);
		card.action(player);
		availableCards.remove(cardToTake);
		if(availableCards.size() <= 0)
			resetCards();
	}
	
	public void resetCards(){
		availableCards = allCards;
		allCards.remove(new ChanceJailCard());
	}
	
	public int getAvailableCardCount(){
		return availableCards.size();
	}
	
	@Override
	public Iterator<ChanceCard> iterator() {
		return allCards.iterator();
	}

}
