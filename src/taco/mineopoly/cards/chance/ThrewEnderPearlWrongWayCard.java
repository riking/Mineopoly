package taco.mineopoly.cards.chance;

import taco.mineopoly.MineopolyPlayer;

public class ThrewEnderPearlWrongWayCard extends ChanceCard {

	public ThrewEnderPearlWrongWayCard() {
		super("Threw an Ender Pearl in the wrong direction. Move back three spaces");
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.move(-3);
	}

}
