package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.TacoCommand;
import taco.tacoapi.api.messages.PlayerNotOnlineMessage;
import taco.tacoapi.api.messages.TooManyArgumentsMessage;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.Permissions;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;

public class MineopolyStatsCommand extends TacoCommand {
	
	public MineopolyStatsCommand() {
		super("stats", new String[]{"info", "player-info", "pi", "s"}, "[player]", "View player stats", "");
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			if(Mineopoly.plugin.getGame().isRunning()){
				if(Mineopoly.plugin.getGame().hasPlayer(player)){
					Mineopoly.plugin.getGame().getBoard().getPlayer(player).getInfo(player);
				}else{
					Mineopoly.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
				}
			}else{
				Mineopoly.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
			}
		}else if(args.length == 1){
			if(Mineopoly.plugin.getGame().isRunning()){
				Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
				if(p != null){
					if(Mineopoly.plugin.getGame().hasPlayer(player)){
						Mineopoly.plugin.getGame().getBoard().getPlayer(p).getInfo(player);
					}else{
						if(player.hasPermission(Permissions.VIEW_GAME_STATS)){
							Mineopoly.plugin.getGame().getBoard().getPlayer(p).getInfo(player);
						}else{
							Mineopoly.chat.sendInvalidPermissionsMessage(player);
						}
					}
				}else{
					Mineopoly.chat.sendPlayerMessage(player, new PlayerNotOnlineMessage(args[0]));
				}
			}else{
				Mineopoly.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
			}
		}else{
			Mineopoly.chat.sendPlayerMessage(player, new TooManyArgumentsMessage("/mineopoly stats [player]") + "");
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

}
