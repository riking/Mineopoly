package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.Permissions;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.tacoapi.api.command.TacoCommand;
import taco.tacoapi.api.messages.InvalidPermissionsMessage;
import taco.tacoapi.api.messages.TooFewArgumentsMessage;

public class MineopolyKickPlayerCommand extends TacoCommand {

	@Override
	protected String[] getAliases() {
		return new String[]{"kick", "boot"};
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(args.length == 0){
				Mineopoly.plugin.print("Too few arguments");
			}else{
				boolean success = false;
				for(String s : args){
					Player p = Mineopoly.plugin.getServer().getPlayer(s);
					if(p == null){
						Mineopoly.plugin.print("player '" + s + "' not found");
					}else{
						if(Mineopoly.plugin.getGame().hasPlayer(p)){
							MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
							Mineopoly.plugin.getGame().kick(mp, "kicked by CONSOLE");
							success = true;
						}else{
							Mineopoly.plugin.print(p.getName() + " is not playing Mineopoly");
						}
					}
				}
				if(success)
					Mineopoly.plugin.print("Player(s) kicked");
			}
		}
		return true;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(player.hasPermission(Permissions.KICK_PLAYER_FROM_GAME + "")){
			if(Mineopoly.plugin.getGame().isRunning()){
				if(args.length == 0){
					player.sendMessage(new TooFewArgumentsMessage("/mineopoly kick <players>") + "");
				}else{
					boolean success = false;
					for(String s : args){
						Player p = Mineopoly.plugin.getServer().getPlayer(s);
						if(p == null){
							player.sendMessage(Mineopoly.getChatUtils().formatMessage("player '" + s + "' not found"));
						}else{
							if(Mineopoly.plugin.getGame().hasPlayer(p)){
								MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
								Mineopoly.plugin.getGame().kick(mp, "kicked by " + player.getName());
								success = true;
							}else{
								player.sendMessage(Mineopoly.getChatUtils().formatMessage(p.getName() + " is not playing Mineopoly"));
							}
						}
					}
					if(success)
						Mineopoly.plugin.print("Player(s) kicked");
				}
			}else{
				player.sendMessage(new GameNotInProgressMessage() + "");
			}
		}else{
			player.sendMessage(new InvalidPermissionsMessage() + "");
		}
		return true;
	}

}
