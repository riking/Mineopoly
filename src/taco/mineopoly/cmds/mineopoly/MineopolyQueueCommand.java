package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.command.TacoCommand;
import taco.tacoapi.api.messages.TooManyArgumentsMessage;
import taco.mineopoly.Mineopoly;

public class MineopolyQueueCommand extends TacoCommand {

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			if(Mineopoly.plugin.getQueue().getSize() == 0){
				player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cThere is no one in the game queue"));
			}else{
				player.sendMessage(Mineopoly.getChatUtils().formatMessage("&6======[&7Mineopoly Queue&6]======"));
				String players = "";
				for(Player p : Mineopoly.plugin.getQueue()){
					if(Mineopoly.plugin.getQueue().getIndexFromPlayer(p) == Mineopoly.plugin.getQueue().getSize() - 1){
						players = players + "&7" + p.getName();
					}else{
						players = players + "&7" + p.getName() + "&8, ";
					}
				}
				player.sendMessage(Mineopoly.getChatUtils().formatMessage(players));
			}
		}else{
			player.sendMessage(new TooManyArgumentsMessage("/mineopoly queue") + "");
		}
		return true;
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"queue", "q"};
	}
	
}
