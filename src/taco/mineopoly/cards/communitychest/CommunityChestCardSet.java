package taco.mineopoly.cards.communitychest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import taco.mineopoly.MineopolyPlayer;

public class CommunityChestCardSet implements Iterable<CommunityChestCard>{

	private ArrayList<CommunityChestCard> allCards;
	private ArrayList<CommunityChestCard> availableCards;
	
	public CommunityChestCardSet(){
		allCards = new ArrayList<CommunityChestCard>();
		availableCards = new ArrayList<CommunityChestCard>();
		initCards();
	}
	
	private void initCards(){
//		addCard(new CommunityChestJailCard());
		addCard(new GoToJailCard());
	}
	
	private void addCard(CommunityChestCard card){
		allCards.add(card);
		availableCards.add(card);
	}
	
	public void drawCard(MineopolyPlayer player){
		int cardToTake = new Random().nextInt(availableCards.size());
		CommunityChestCard card = availableCards.get(cardToTake);
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
	public Iterator<CommunityChestCard> iterator() {
		return allCards.iterator();
	}

}
