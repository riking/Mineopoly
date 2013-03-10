package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.Permissions;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.tacoapi.api.TacoCommand;
import taco.tacoapi.api.messages.TooFewArgumentsMessage;

public class MineopolyKickPlayerCommand extends TacoCommand {

	public MineopolyKickPlayerCommand() {
		super("kick", new String[]{}, "<players>", "Kick a player from the game", Permissions.KICK_PLAYER_FROM_GAME);
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(args.length == 0){
				Mineopoly.plugin.chat.out("Too few arguments");
			}else{
				boolean success = false;
				for(String s : args){
					Player p = Mineopoly.plugin.getServer().getPlayer(s);
					if(p == null){
						Mineopoly.plugin.chat.out("player '" + s + "' not found");
					}else{
						if(Mineopoly.plugin.getGame().hasPlayer(p)){
							MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
							Mineopoly.plugin.getGame().kick(mp, "kicked by CONSOLE");
							success = true;
						}else{
							Mineopoly.plugin.chat.out(p.getName() + " is not playing Mineopoly");
						}
					}
				}
				if(success)
					Mineopoly.plugin.chat.out("Player(s) kicked");
			}
		}
		return true;
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(args.length == 0){
				player.sendMessage(new TooFewArgumentsMessage("/mineopoly kick <players>") + "");
			}else{
				boolean success = false;
				for(String s : args){
					Player p = Mineopoly.plugin.getServer().getPlayer(s);
					if(p == null){
						Mineopoly.plugin.chat.sendPlayerMessage(player, "player '" + s + "' not found");
					}else{
						if(Mineopoly.plugin.getGame().hasPlayer(p)){
							MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
							Mineopoly.plugin.getGame().kick(mp, "kicked by " + player.getName());
							success = true;
						}else{
							Mineopoly.plugin.chat.sendPlayerMessage(player, p.getName() + " is not playing Mineopoly");
						}
					}
				}
				if(success)
					Mineopoly.plugin.chat.sendPlayerMessage(player, "Player(s) kicked");
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage() + "");
		}
	}

}
