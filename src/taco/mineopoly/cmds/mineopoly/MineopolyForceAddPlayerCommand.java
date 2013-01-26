package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.Permissions;
import taco.tacoapi.api.command.TacoCommand;
import taco.tacoapi.api.messages.PlayerNotOnlineMessage;
import taco.tacoapi.api.messages.TooFewArgumentsMessage;

public class MineopolyForceAddPlayerCommand extends TacoCommand {

	@Override
	protected String[] getAliases() {
		return new String[]{"force-add", "fa"};
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		if(args.length == 1){
			Mineopoly.plugin.print(new TooFewArgumentsMessage("mineopoly force-add <player>") + "");
		}else{
			if(Mineopoly.plugin.getGame().isRunning()){
				Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
				if(p == null){
					Mineopoly.plugin.print(new PlayerNotOnlineMessage(args[0]) + "");
				}else{
					MineopolyPlayer player = new MineopolyPlayer(p);
					Mineopoly.plugin.getGame().getBoard().addPlayer(player);
					Mineopoly.plugin.getGame().getChannel().addPlayer(player);
					player.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(0), false);
					Mineopoly.plugin.print(player.getName() + " was added to the game");
				}
			}
		}
		return true;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(player.hasPermission(Permissions.FORCE_ADD_PLAYER)){
			if(args.length == 1){
				Mineopoly.plugin.print(new TooFewArgumentsMessage("mineopoly force-add <player>") + "");
			}else{
				if(Mineopoly.plugin.getGame().isRunning()){
					Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
					if(p == null){
						Mineopoly.plugin.print(new PlayerNotOnlineMessage(args[0]) + "");
					}else{
						MineopolyPlayer mp = new MineopolyPlayer(p);
						Mineopoly.plugin.getGame().getBoard().addPlayer(mp);
						Mineopoly.plugin.getGame().getChannel().addPlayer(mp);
						mp.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(0), false);
						Mineopoly.plugin.print(player.getName() + " was added to the game");
					}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	
}
