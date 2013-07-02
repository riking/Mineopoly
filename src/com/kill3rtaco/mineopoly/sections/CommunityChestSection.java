package com.kill3rtaco.mineopoly.sections;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPlayer;


public class CommunityChestSection extends MineopolySection implements ActionProvoker, CardinalSection{

	private int side;
	
	public CommunityChestSection(int id, int side) {
		super(id, "Community Chest", 'e');
		this.side = side;
	}

	public void getInfo(Player player){
		player.sendMessage("&6---[" + getColorfulName() + "&b(&3" + getId() + "&b)&6]---");
		player.sendMessage("&3Landing on this space will draw a card from the &eCommunity Chest &3card pile");
	}
	
	public void provokeAction(MineopolyPlayer player){
		Mineopoly.plugin.getGame().getBoard().getCommunityChestCards().drawCard(player);
	}

	@Override
	public int getSide() {
		return side;
	}

}
