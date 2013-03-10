package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.TacoCommand;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyChannelListener;
import taco.mineopoly.Permissions;
import taco.mineopoly.messages.GameNotInProgressMessage;

public class MineopolyJoinChannelCommand extends TacoCommand {

	public MineopolyJoinChannelCommand() {
		super("join-channel", new String[]{"jc"}, "", "Join the game's output channel", Permissions.CHANNEL_CHAT);
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				Mineopoly.plugin.chat.sendPlayerMessage(player, "&cYou are already in the channel");
			}else if(Mineopoly.plugin.getGame().getChannel().isListeningToChannel(player.getName())){
				Mineopoly.plugin.chat.sendPlayerMessage(player, "&cYou are already in the channel");
				if(player.hasPermission(Permissions.CHANNEL_CHAT.toString())){
					Mineopoly.plugin.getGame().getChannel().addPlayer(new MineopolyChannelListener(player));
				}
			}
		}else{
			Mineopoly.plugin.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

}
