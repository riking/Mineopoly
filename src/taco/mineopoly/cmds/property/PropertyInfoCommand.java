package taco.mineopoly.cmds.property;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.Permission;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.mineopoly.sections.MineopolySection;
import taco.tacoapi.api.command.TacoCommand;

public class PropertyInfoCommand extends TacoCommand {

	@Override
	protected String[] getAliases() {
		return new String[]{"info"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(player.hasPermission(Permission.VIEW_PROPERTY_STATS + "") || Mineopoly.plugin.getGame().hasPlayer(player)){
				if(args.length == 0){
					if(Mineopoly.plugin.getGame().hasPlayer(player)){
						MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
						mp.getCurrentSection().getInfo(player);
					}else{
						player.sendMessage(new NotPlayingGameMessage() + "");
						return true;
					}
				}else{
					MineopolySection section;
					if(Mineopoly.getChatUtils().isNum(args[0])){
						int id = Integer.parseInt(args[0]);
						section = Mineopoly.plugin.getGame().getBoard().getSection(id);
						if(section == null){
							player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cID cannot be lower than &60"));
							return true;
						}
					}else{
						section = Mineopoly.plugin.getGame().getBoard().getSection(args[0]);
						if(section == null){
							player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cSpace on board with name of &6" + args[0] + " &cnot found"));
							return true;
						}
					}
					section.getInfo(player);
				}
			}else if(!player.hasPermission(Permission.VIEW_PROPERTY_STATS + "")){
				return false;
			}else{
				player.sendMessage(new NotPlayingGameMessage() + "");
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage() + "");
		}
		return true;
	}

}
