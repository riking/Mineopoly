package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.TacoAPI;
import taco.tacoapi.api.TacoCommand;
import taco.tacoapi.api.messages.TooManyArgumentsMessage;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.Permissions;

public class MineopolyQueueCommand extends TacoCommand {

	public MineopolyQueueCommand() {
		super("queue", new String[]{"q"}, "", "View players in the game queue", Permissions.VIEW_PLAYER_QUEUE);
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			if(Mineopoly.plugin.getQueue().getSize() == 0){
				Mineopoly.chat.sendPlayerMessage(player, "&cThere is no one in the game queue");
			}else{
				Mineopoly.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("&7Mineopoly Queue"));
				String players = "";
				for(Player p : Mineopoly.plugin.getQueue()){
					if(Mineopoly.plugin.getQueue().getIndexFromPlayer(p) == Mineopoly.plugin.getQueue().getSize() - 1){
						players = players + "&7" + p.getName();
					}else{
						players = players + "&7" + p.getName() + "&8, ";
					}
				}
				Mineopoly.chat.sendPlayerMessageNoHeader(player, players);
			}
		}else{
			player.sendMessage(new TooManyArgumentsMessage("/mineopoly queue") + "");
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}
	
}
