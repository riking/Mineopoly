package taco.mineopoly.cards.communitychest;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class CollectFromEachPlayerCard extends CommunityChestCard {

	public CollectFromEachPlayerCard() {
		super("Collect &250 &bfrom each player");
	}

	@Override
	public void action(MineopolyPlayer player) {
		for(MineopolyPlayer p : Mineopoly.plugin.getGame().getBoard().getPlayers()){
			if(!p.getName().equalsIgnoreCase(player.getName())){
				p.payPlayer(player, 50);
				p.sendMessage("&3You paid &250 &3to &b" + player.getName());
			}
		}
	}

}
