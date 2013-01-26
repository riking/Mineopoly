package taco.mineopoly.sections.tax;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.ActionProvoker;
import taco.mineopoly.sections.CardinalSection;
import taco.mineopoly.sections.MineopolySection;
import taco.tacoapi.api.TacoChatUtils;

public class IncomeTax extends MineopolySection implements ActionProvoker, CardinalSection {
	
	public IncomeTax() {
		super(4, "Income Tax", '7');
	}

	public void provokeAction(MineopolyPlayer player) {
		player.takeMoney(200);
		player.sendMessage("&3You paid &2200 &3to the bank");
	}

	public int getSide() {
		return 0;
	}

	@Override
	public void getInfo(Player player) {
		TacoChatUtils cu = Mineopoly.getChatUtils();
		player.sendMessage(cu.formatMessage("&6---[" + getColorfulName() + "&6]---"));
		player.sendMessage(cu.formatMessage("&bLand on this space and you must pay &2200 &bto the bank"));
	}

}
