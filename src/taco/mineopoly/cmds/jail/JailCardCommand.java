package taco.mineopoly.cmds.jail;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.NotInJailMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.tacoapi.api.TacoCommand;

public class JailCardCommand extends TacoCommand{

	public JailCardCommand() {
		super("card", new String[]{"goojf", "gojf", "out"}, "", "Use a Get Out of Jail Free card", "");
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
					if(mp.isJailed()){
						if(mp.hasChanceJailCard() || mp.hasCommunityChestJailCard()){
							if(mp.hasChanceJailCard()){
								mp.takeChanceJailCard();
								Mineopoly.plugin.getGame().getBoard().getPot().addChanceJailCard();
							}else if(mp.hasCommunityChestJailCard()){
								mp.takeCommunityChestJailCard();
								Mineopoly.plugin.getGame().getBoard().getPot().addCommunityChestJailCard();
							}
							Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3has used a &bGet out of Jail Free &3card", mp);
							mp.sendMessage("&3You are out of jail. You can now use &b/mineopoly roll");
							mp.setJailed(false, true);
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
				Mineopoly.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}else{
			Mineopoly.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
		}
	}

}
