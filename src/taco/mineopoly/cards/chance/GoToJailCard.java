package taco.mineopoly.cards.chance;

import taco.mineopoly.MineopolyPlayer;

public class GoToJailCard extends ChanceCard {

	public GoToJailCard() {
		super("Go to jail. Do not pass Go. Do not collect &2200");
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.setJailed(true);
		player.sendMessage("&3You were &1jailed!");
	}

}
