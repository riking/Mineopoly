package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.command.TacoCommand;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.Permission;

public class MineopolyJoinCommand extends TacoCommand {

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(player.hasPermission(Permission.JOIN_GAME.toString())){
			if(Mineopoly.plugin.getQueue().playerIsInQueue(player)){
				player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cYou are already queued to join the next Mineopoly game"));
				return true;
			}else{
				Mineopoly.plugin.getQueue().addPlayer(player);
				if(!Mineopoly.plugin.getGame().isRunning() && Mineopoly.plugin.getQueue().getSize() >= Mineopoly.config.getMinPlayers())
					Mineopoly.plugin.restartGame();
				else
					player.sendMessage("You've been added to the game queue, please wait until the next game is over or until more players join");
				return true;
			}
		}else{
			return false;
		}
		
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"join", "j"};
	}

}
