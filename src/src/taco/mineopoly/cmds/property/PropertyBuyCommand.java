package taco.mineopoly.cmds.property;

import org.bukkit.entity.Player;


import taco.tacoapi.api.command.TacoCommand;
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
import taco.mineopoly.sections.Ownable;

public class PropertyBuyCommand extends TacoCommand {

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(args.length > 0){
			player.sendMessage(new TooManyArgumentsMessage("/property buy").getMessage());
		}else{
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				MineopolySection section = mp.getCurrentSection();
				if(mp.hasTurn()){
					if(mp.hasRolled()){
						if(section instanceof Ownable){
							Ownable oSection = (Ownable) section;
							if(!oSection.isOwned()){
								if(mp.canBuy(oSection)){
									oSection.setOwner(mp);
									mp.takeMoney(oSection.getPrice());
									Mineopoly.plugin.getGame().getChannel().sendMessage("&2" + mp.getName() + " &abought " + section.getColorfulName(), mp);
									Mineopoly.plugin.getGame().getChannel().sendMessage("&aYou bought " + section.getColorfulName());
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
				player.sendMessage(new NotPlayingGameMessage().getMessage());
			}
		}
		return true;
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return true;
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"buy"};
	}

}
