package taco.mineopoly.cards.communitychest;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;


public class AdvanceToGoCard extends CommunityChestCard {

	public AdvanceToGoCard() {
		super("Advance to GO &3(&bCollect &2200&3)");
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(0));
	}

}
