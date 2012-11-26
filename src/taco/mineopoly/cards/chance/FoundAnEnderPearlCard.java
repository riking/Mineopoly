package taco.mineopoly.cards.chance;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class FoundAnEnderPearlCard extends ChanceCard {

	public FoundAnEnderPearlCard() {
		this.description = "You found an Ender Pearl! Go forward two spaces";
	}

	@Override
	public void action(MineopolyPlayer player) {
		int newId = player.getCurrentSection().getId();
		player.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(newId + 2));
		player.sendMessage("&3You landed on " + player.getCurrentSection().getColorfulName());
	}

}
