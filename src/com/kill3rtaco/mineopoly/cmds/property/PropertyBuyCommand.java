package com.kill3rtaco.mineopoly.cmds.property;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPlayer;
import com.kill3rtaco.mineopoly.messages.InsufficientFundsMessage;
import com.kill3rtaco.mineopoly.messages.InvalidTurnMessage;
import com.kill3rtaco.mineopoly.messages.MustRollFirstMessage;
import com.kill3rtaco.mineopoly.messages.NotPlayingGameMessage;
import com.kill3rtaco.mineopoly.messages.SectionAlreadyOwnedMessage;
import com.kill3rtaco.mineopoly.messages.SectionNotOwnableMessage;
import com.kill3rtaco.mineopoly.sections.MineopolySection;
import com.kill3rtaco.mineopoly.sections.OwnableSection;
import com.kill3rtaco.mineopoly.sections.Property;
import com.kill3rtaco.mineopoly.sections.Railroad;
import com.kill3rtaco.mineopoly.sections.Utility;

import com.kill3rtaco.tacoapi.api.TacoCommand;
import com.kill3rtaco.tacoapi.api.messages.TooManyArgumentsMessage;

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
									Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3bought " + section.getColorfulName() +"&3 for &2" + oSection.getPrice(), mp);
									mp.sendMessage("&3You bought " + section.getColorfulName() + "&3 for &2" + oSection.getPrice());
									mp.sendBalanceMessage();
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
				Mineopoly.plugin.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return true;
	}

}
