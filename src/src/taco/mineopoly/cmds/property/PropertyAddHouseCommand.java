package taco.mineopoly.cmds.property;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.CannotPerformActionMessage;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.mineopoly.sections.Property;
import taco.tacoapi.api.command.TacoCommand;

public class PropertyAddHouseCommand extends TacoCommand {

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(mp.hasTurn()){
					if(args.length == 0){ //add house to current property
						if(mp.getCurrentSection() instanceof Property){
							if(mp.canAddHouse((Property) mp.getCurrentSection())){
								Property prop = (Property) mp.getCurrentSection();
								prop.addHouse();
								Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3added a house to " + prop.getColorfulName(), mp);
								mp.sendMessage("&3You added a &ahouse &3to " + prop.getColorfulName());
							}else{
								
							}
						}else{
							player.sendMessage(new CannotPerformActionMessage("add a house to that") + "");
						}
					}else{ //get specified property and add house to it if not null (id or name, name being the config name)
						
					}
				}else{
					player.sendMessage(new InvalidTurnMessage() + "");
				}
			}else{
				player.sendMessage(new NotPlayingGameMessage() + "");
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage().getMessage());
		}
		return true;
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"add-house"};
	}

}