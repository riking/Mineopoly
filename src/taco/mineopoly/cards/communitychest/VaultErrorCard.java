package taco.mineopoly.cards.communitychest;

import taco.mineopoly.MineopolyPlayer;

public class VaultErrorCard extends CommunityChestCard {

	public VaultErrorCard() {
		super("Vault error in your favor. Collect &2200");
	}

	@Override
	public void action(MineopolyPlayer player) {
		player.addMoney(200);
		player.sendMessage("&3You were given &2200");
	}

}
