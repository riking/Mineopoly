package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.command.TacoCommand;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyChannelListener;
import taco.mineopoly.Permission;
import taco.mineopoly.messages.GameNotInProgressMessage;

public class MineopolyJoinChannelCommand extends TacoCommand {

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cYou are already in the channel"));
				return true;
			}else if(Mineopoly.plugin.getGame().getChannel().isListeningToChannel(player.getName())){
				player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cYou are already in the channel"));
				return true;
			}else{
				if(player.hasPermission(Permission.CHANNEL_CHAT.toString())){
					Mineopoly.plugin.getGame().getChannel().addPlayer(new MineopolyChannelListener(player));
					return true;
				}else{
					return false;
				}
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage() + "");
			return true;
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"join-channel", "jc"};
	}

}
