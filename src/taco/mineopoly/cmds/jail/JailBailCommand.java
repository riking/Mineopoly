package taco.mineopoly.cmds.jail;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InsufficientFundsMessage;
import taco.mineopoly.messages.NotInJailMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.tacoapi.api.command.TacoCommand;

public class JailBailCommand extends TacoCommand {

	@Override
	protected String[] getAliases() {
		return new String[]{"pay-bail", "bail", "b"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(mp.isJailed()){
					if(mp.hasMoney(50)){
						mp.payPot(50);
						mp.setJailed(false);
						mp.sendMessage("&3You can now use &b/mineopoly roll");
					}else{
						mp.sendMessage(new InsufficientFundsMessage());
					}
				}else{
					mp.sendMessage(new NotInJailMessage());
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
