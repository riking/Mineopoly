package taco.mineopoly.sections.squares;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.SpecialSquare;

public class FreeParkingSquare extends SpecialSquare {
	
	public FreeParkingSquare() {
		super(20, "Free Parking", '4');
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		Mineopoly.plugin.getGame().getBoard().getPot().give(player);
	}

	@Override
	public void getInfo(Player player) {
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&6---[" + getColorfulName() + "&6]---");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&bLand on this square and you shall recieve all money in the pot");
	}

}
