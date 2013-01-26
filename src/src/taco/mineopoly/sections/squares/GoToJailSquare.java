package taco.mineopoly.sections.squares;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.SpecialSquare;
import taco.tacoapi.api.TacoChatUtils;

public class GoToJailSquare extends SpecialSquare {
	
	public GoToJailSquare() {
		super(30, "Go to Jail", '1');
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		player.setJailed(true);
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + player.getName() + "&3was &1jailed&3!", player);
		player.sendMessage("&3You were &1jailed&3!");
	}

	@Override
	public void getInfo(Player player) {
		TacoChatUtils cu = Mineopoly.getChatUtils();
		player.sendMessage(cu.formatMessage("&6---[" + getColorfulName() + "&6]---"));
		player.sendMessage("&bLand on this space and you will be sent to jail immediatley.");
		player.sendMessage("");
		player.sendMessage("&bYou can use a &1Get out of Jail Free &bcard on your next turn if you were jailed.");
	}

}
