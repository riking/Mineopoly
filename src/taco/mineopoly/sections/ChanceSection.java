package taco.mineopoly.sections;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class ChanceSection extends MineopolySection implements CardinalSection, ActionProvoker {

	private int side;
	
	public ChanceSection(int id, int side) {
		super(id, "Chance", '6');
		this.side = side;
	}

	@Override
	public void getInfo(Player player) {
		player.sendMessage("&6---[" + getColorfulName() + "&b(&3" + getId() + "&b)&6]---");
		player.sendMessage("&3Landing on this space will draw a card from the &6Chance &3card pile");
	}

	@Override
	public int getSide() {
		return side;
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		Mineopoly.plugin.getGame().getBoard().getChanceCards().drawCard(player);
	}

}
