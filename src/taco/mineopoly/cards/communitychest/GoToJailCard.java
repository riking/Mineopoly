package taco.mineopoly.cards.communitychest;

import taco.mineopoly.MineopolyPlayer;

public class GoToJailCard extends CommunityChestCard {

	public GoToJailCard() {
		this.description = "Go to jail. Do not pass Go. Do not collect &2200";
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.setJailed(true);
		player.sendMessage("&3You were &1jailed!");
	}

}
