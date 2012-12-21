package taco.mineopoly.cards;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import taco.mineopoly.MineopolyPlayer;

public abstract class MineopolyCardSet implements Iterable<MineopolyCard> {

	protected ArrayList<MineopolyCard> cards;
	
	public MineopolyCardSet(){
		cards = new ArrayList<MineopolyCard>();
	}
	
	protected abstract void initCards();
	
	protected void addCard(MineopolyCard card){
		cards.add(card);
	}
	
	public void drawCard(MineopolyPlayer player){
		int cardToTake = new Random().nextInt(cards.size());
		MineopolyCard card = cards.get(cardToTake);
		card.readDescription(player);
		card.action(player);
	}
	
	@Override
	public Iterator<MineopolyCard> iterator() {
		return cards.iterator();
	}

}
