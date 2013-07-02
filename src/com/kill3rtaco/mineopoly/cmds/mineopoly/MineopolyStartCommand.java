package com.kill3rtaco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPermissions;

import com.kill3rtaco.tacoapi.api.TacoCommand;

public class MineopolyStartCommand extends TacoCommand {

	public MineopolyStartCommand() {
		super("start", new String[]{}, "", "Start the game", MineopolyPermissions.START_GAME);
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(!Mineopoly.plugin.getGame().isRunning()){
			int queued = Mineopoly.plugin.getQueue().getSize();
			int min = Mineopoly.config.getMinPlayers();
			if(queued >= min){
				Mineopoly.plugin.restartGame();
			}else{
				Mineopoly.plugin.chat.sendPlayerMessage(player, "&e" + queued + " &cplayers in queue with minimum of &e" + min + " &crequired");
				Mineopoly.plugin.chat.sendPlayerMessage(player, "&eView players in the queue by using &a/mineopoly queue");
			}
		}else{
			Mineopoly.plugin.chat.sendPlayerMessage(player, "&cThere is already a game in progress");
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		if(!Mineopoly.plugin.getGame().isRunning()){
			int queued = Mineopoly.plugin.getQueue().getSize();
			int min = Mineopoly.config.getMinPlayers();
			if(queued >= min){
				Mineopoly.plugin.restartGame();
			}else{
				Mineopoly.plugin.chat.out(queued + " players in queue with minimum of " + min + " required");
			}
		}else{
			Mineopoly.plugin.chat.out("There is already a game in progress");
		}
		return true;
	}

}
