package taco.mineopoly.sections.tax;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.ActionProvoker;
import taco.mineopoly.sections.CardinalSection;
import taco.mineopoly.sections.MineopolySection;
import taco.tacoapi.api.TacoChatUtils;

public class LuxuryTax extends MineopolySection implements ActionProvoker, CardinalSection {

	public LuxuryTax() {
		super(38, "Luxury Tax", '7');
	}

	@Override
	public int getSide() {
		return 3;
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		player.takeMoney(75);
		player.sendMessage("&3You paid &275 &3to the bank");
	}

	@Override
	public void getInfo(Player player) {
		TacoChatUtils cu = Mineopoly.getChatUtils();
		player.sendMessage(cu.formatMessage("&6---[" + getColorfulName() + "&6]---"));
		player.sendMessage(cu.formatMessage("&bLand on this space and you must pay &275 &bto the bank"));
	}

}
