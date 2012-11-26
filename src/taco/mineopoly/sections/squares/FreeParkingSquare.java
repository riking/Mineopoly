package taco.mineopoly.sections.squares;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyChatChannel;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.SpecialSquare;
import taco.tacoapi.api.TacoChatUtils;

public class FreeParkingSquare extends SpecialSquare {
	
	public FreeParkingSquare() {
		super(20, "Free Parking", '4');
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		MineopolyChatChannel channel = Mineopoly.plugin.getGame().getChannel();
		int amount = Mineopoly.plugin.getGame().getBoard().getPot().getMoney();
		Mineopoly.plugin.getGame().getBoard().getPot().give(player);
		channel.sendMessage("&b" +  player + " &3landed on &4Free Parking and was awarded &2" + amount, player);
		player.sendMessage("&3You were awarded &2" + amount + " &3for landing on &4Free Parking");
		
	}

	@Override
	public void getInfo(Player player) {
		TacoChatUtils cu = Mineopoly.getChatUtils();
		player.sendMessage(cu.formatMessage("&6---[" + getColorfulName() + "&6]---"));
		player.sendMessage(cu.formatMessage("&bLand on this square and you shall recieve all money in the pot"));
	}

}
