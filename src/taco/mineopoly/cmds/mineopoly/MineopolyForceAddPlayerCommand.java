package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.Permissions;
import taco.tacoapi.api.TacoCommand;
import taco.tacoapi.api.messages.PlayerNotOnlineMessage;
import taco.tacoapi.api.messages.TooFewArgumentsMessage;

public class MineopolyForceAddPlayerCommand extends TacoCommand {

	public MineopolyForceAddPlayerCommand() {
		super("force-add", new String[]{"fa"}, "[player]", "Forcefully add a player", Permissions.FORCE_ADD_PLAYER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		if(args.length == 1){
			Mineopoly.plugin.chat.out(new TooFewArgumentsMessage("mineopoly force-add <player>").getMessage());
		}else{
			if(Mineopoly.plugin.getGame().isRunning()){
				Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
				if(p == null){
					Mineopoly.plugin.chat.out(new PlayerNotOnlineMessage(args[0]).getMessage());
				}else{
					if(!Mineopoly.plugin.getGame().hasPlayer(p)){
						MineopolyPlayer mp = new MineopolyPlayer(p);
						Mineopoly.plugin.getGame().getBoard().addPlayer(mp);
						Mineopoly.plugin.getGame().getChannel().addPlayer(mp);
						mp.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(0), false);
						Mineopoly.plugin.chat.out(mp.getName() + " was added to the game");
					}else{
						Mineopoly.plugin.chat.out("&cThat player is already playing");
					}
				}
			}
		}
		return true;
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(args.length == 1){
			Mineopoly.plugin.chat.sendPlayerMessage(player, new TooFewArgumentsMessage("mineopoly force-add <player>"));
		}else{
			if(Mineopoly.plugin.getGame().isRunning()){
				Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
				if(p == null){
					Mineopoly.plugin.chat.sendPlayerMessage(player, new PlayerNotOnlineMessage(args[0]));
				}else{
					if(!Mineopoly.plugin.getGame().hasPlayer(p)){
						MineopolyPlayer mp = new MineopolyPlayer(p);
						Mineopoly.plugin.getGame().getBoard().addPlayer(mp);
						Mineopoly.plugin.getGame().getChannel().addPlayer(mp);
						mp.setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(0), false);
						Mineopoly.plugin.getGame().getChannel().sendMessage("&e" + mp.getName() + " was added to the game");
					}else{
						Mineopoly.plugin.chat.sendPlayerMessage(player, "&cThat player is already playing");
					}
				}
			}
		}
	}
	
}
