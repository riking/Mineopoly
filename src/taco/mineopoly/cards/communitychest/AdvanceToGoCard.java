package taco.mineopoly.cards.communitychest;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class AdvanceToGoCard extends CommunityChestCard {

	public AdvanceToGoCard() {
		this.description = "Advance to GO (Collect &2200&b)";
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(0));
	}

}
