package taco.mineopoly.cmds.jail;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.NotInJailMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.tacoapi.api.command.TacoCommand;

public class JailCardCommand extends TacoCommand{

	@Override
	protected String[] getAliases() {
		return new String[]{"card"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		//TODO if game running -> if playing -> if hasTurn-> if jailed -> true
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(mp.hasTurn()){
					if(mp.isJailed()){
						if(mp.hasChanceJailCard() || mp.hasCommunityChestJailCard()){
							if(mp.hasChanceJailCard()){
								mp.takeChanceJailCard();
								Mineopoly.plugin.getGame().getBoard().getPot().addChanceJailCard();
							}else if(mp.hasCommunityChestJailCard()){
								mp.takeCommunityChestJailCard();
								Mineopoly.plugin.getGame().getBoard().getPot().addCommunityChestJailCard();
							}
							mp.setJailed(false);
							Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3has used a &bGet out of Jail Free &3card", mp);
							mp.sendMessage("&3You are out of jail. You can now use &b/mineopoly roll");
						}else{
							mp.sendMessage("&cYou do not have a &6Get out of Jail Free &ccard to use");
						}
					}else{
						mp.sendMessage(new NotInJailMessage());
					}
				}else{
					mp.sendMessage(new InvalidTurnMessage());
				}
			}else{
				player.sendMessage(new NotPlayingGameMessage() + "");
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage() + "");
		}
		return true;
	}

}
