package taco.mineopoly.sections;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

public class TaxSection extends MineopolySection implements ActionProvoker {

	private int tax;
	
	public TaxSection(int id, String name, char color, int tax) {
		super(id, name, color);
		this.tax = tax;
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		player.payPot(tax);
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + player.getName() + "&3 paid &2" + tax + " &3to the bank", player);
		player.sendMessage("&3You paid &2" + tax + " &3to the bank");
	}

	@Override
	public void getInfo(Player player) {
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&6---[" + getColorfulName() + "&6]---");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&bLand on this space and you must pay &2" + tax + " &bto the bank");
	}
	
	public int getTax(){
		return tax;
	}

}
