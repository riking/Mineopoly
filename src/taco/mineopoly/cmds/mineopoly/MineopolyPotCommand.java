package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.Permissions;
import taco.tacoapi.api.TacoCommand;

public class MineopolyPotCommand extends TacoCommand {

	public MineopolyPotCommand() {
		super("pot", new String[]{}, "", "See what is in the pot", "");
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				Mineopoly.plugin.getGame().getBoard().getPot().getInfo(player);
			}else{
				if(player.hasPermission(Permissions.VIEW_GAME_STATS)){
					Mineopoly.plugin.getGame().getBoard().getPot().getInfo(player);
				}else{
					Mineopoly.chat.sendInvalidPermissionsMessage(player);
				}
			}
		}else{
			
		}
	}

}
