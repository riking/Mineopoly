package taco.mineopoly.cmds.property;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.CannotPerformActionMessage;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.mineopoly.messages.SectionNotFoundMessage;
import taco.mineopoly.sections.MineopolySection;
import taco.mineopoly.sections.Property;
import taco.tacoapi.TacoAPI;
import taco.tacoapi.api.TacoCommand;

public class PropertyAddHotelCommand extends TacoCommand {

	public PropertyAddHotelCommand() {
		super("add-hotel", new String[]{}, "[property]", "Add a Hotel to a property", "");
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(mp.hasTurn()){
					if(args.length == 0){
						if(mp.getCurrentSection() instanceof Property){
							if(((Property) mp.getCurrentSection()).getHouses() == 4){ //must buy 4 houses first
								Property prop = (Property) mp.getCurrentSection();
								if(!prop.hasHotel()){
									prop.addHotel();
									Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3added a hotel to " + prop.getColorfulName(), mp);
									mp.sendMessage("&3You added a &ahotel &3to " + prop.getColorfulName());
								}else{
									mp.sendMessage("&cThat already has a hotel");
								}
							}else{
								mp.sendMessage(new CannotPerformActionMessage("add a hotel until you buy four houses"));
							}
						}else{
							mp.sendMessage(new CannotPerformActionMessage("add a hotel to that"));
						}
					}else{ 
						MineopolySection section;
						if(TacoAPI.getChatUtils().isNum(args[0]))
							section = Mineopoly.plugin.getGame().getBoard().getSection(Integer.parseInt(args[0]));
						else
							section = Mineopoly.plugin.getGame().getBoard().getSection(args[0]);
						if(section == null){
							mp.sendMessage(new SectionNotFoundMessage());
						}else{
							if(section instanceof Property){
								if(((Property) section).getHouses() == 4){ //must buy 4 houses first
									Property prop = (Property) section;
									if(!prop.hasHotel()){
										prop.addHotel();
										Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3added a hotel to " + prop.getColorfulName(), mp);
										mp.sendMessage("&3You added a &ahotel &3to " + prop.getColorfulName());
									}else{
										mp.sendMessage("&cThat already has a hotel");
									}
								}else{
									mp.sendMessage(new CannotPerformActionMessage("add a hotel until you buy four houses"));
								}
							}else{
								mp.sendMessage(new CannotPerformActionMessage("add a house to that"));
							}
						}
					}
				}else{
					Mineopoly.plugin.chat.sendPlayerMessage(player, new InvalidTurnMessage());
				}
			}else{
				Mineopoly.plugin.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}else{
			Mineopoly.plugin.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
		}
	}

}
