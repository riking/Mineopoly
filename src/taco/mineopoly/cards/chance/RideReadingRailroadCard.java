package taco.mineopoly.cards.chance;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class RideReadingRailroadCard extends ChanceCard {

	public RideReadingRailroadCard() {
		super("Take a ride on the &0" + Mineopoly.config.getRailroadName("reading") + " Railroad. &bIf you pass &6GO &b collect &2200");
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(5));
	}

}
