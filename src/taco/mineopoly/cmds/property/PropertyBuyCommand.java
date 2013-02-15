package taco.mineopoly.cmds.property;

import org.bukkit.entity.Player;

import taco.tacoapi.api.TacoCommand;
import taco.tacoapi.api.messages.TooManyArgumentsMessage;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.InsufficientFundsMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.MustRollFirstMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.mineopoly.messages.SectionAlreadyOwnedMessage;
import taco.mineopoly.messages.SectionNotOwnableMessage;
import taco.mineopoly.sections.MineopolySection;
import taco.mineopoly.sections.OwnableSection;
import taco.mineopoly.sections.Property;
import taco.mineopoly.sections.Railroad;
import taco.mineopoly.sections.Utility;

public class PropertyBuyCommand extends TacoCommand {

	public PropertyBuyCommand() {
		super("buy", new String[]{}, "", "Buy the space your are on", "");
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(args.length > 0){
			player.sendMessage(new TooManyArgumentsMessage("/property buy").getMessage());
		}else{
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				MineopolySection section = mp.getCurrentSection();
				if(mp.hasTurn()){
					if(mp.hasRolled()){
						if(section instanceof OwnableSection){
							OwnableSection oSection = (OwnableSection) section;
							if(!oSection.isOwned()){
								if(mp.canBuy(oSection)){
									oSection.setOwner(mp);
									mp.takeMoney(oSection.getPrice());
									Mineopoly.plugin.getGame().getChannel().sendMessage("&3" + mp.getName() + " &3bought " + section.getColorfulName() +"&3 for &2" + oSection.getPrice(), mp);
									mp.sendMessage("&3You bought " + section.getColorfulName() + "&3 for &2" + oSection.getPrice());
									if(oSection instanceof Property){
										Property prop = (Property) oSection;
										if(mp.hasMonopoly(prop.getColor())){
											Mineopoly.plugin.getGame().getChannel().sendMessage("&b " + mp.getName() + "now has a monopoly for the color " + prop.getColor().getName(), mp);
											mp.sendMessage("&3You now have a monopoly for the color " + prop.getColor().getName());
											mp.sendMessage("&3You can now add houses by typing &b/property add-house [property]");
										}
									}else if(oSection instanceof Railroad && mp.ownedRailRoads() == 4){
										Mineopoly.plugin.getGame().getChannel().sendMessage("&b " + mp.getName() + "now owns all Railroad spaces", mp);
										mp.sendMessage("&3You now now own all Railroad spaces");
									}else if(oSection instanceof Utility && mp.ownedUtilities() == 2){
										Mineopoly.plugin.getGame().getChannel().sendMessage("&b " + mp.getName() + "now owns both Utility spaces", mp);
										mp.sendMessage("&3You now own both Utility spaces");
									}
								}else{
									mp.sendMessage(new InsufficientFundsMessage());
								}
							}else{
								mp.sendMessage(new SectionAlreadyOwnedMessage(section));
							}
						}else{
							mp.sendMessage(new SectionNotOwnableMessage(section, "buy"));
						}
					}else{
						mp.sendMessage(new MustRollFirstMessage("buying property"));
					}
				}else{
					mp.sendMessage(new InvalidTurnMessage());
				}
			}else{
				Mineopoly.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return true;
	}

}
