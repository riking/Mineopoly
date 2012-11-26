package taco.mineopoly.sections;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class CommunityChestSection extends MineopolySection implements ActionProvoker, CardinalSection{

	private int side;
	
	public CommunityChestSection(int id, int side) {
		super(id, "Community Chest", 'e');
		this.side = side;
	}

	public void getInfo(Player player){
		player.sendMessage(""); //title
		player.sendMessage(""); //id:
		player.sendMessage(""); //description
	}
	
	public void provokeAction(MineopolyPlayer player){
		Mineopoly.plugin.getGame().getBoard().getCommunityChestCards().drawCard(player);
	}

	@Override
	public int getSide() {
		return side;
	}

}
