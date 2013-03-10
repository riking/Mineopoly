package taco.mineopoly.cmds.jail;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InsufficientFundsMessage;
import taco.mineopoly.messages.NotInJailMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.tacoapi.api.TacoCommand;

public class JailBailCommand extends TacoCommand {

	public JailBailCommand() {
		super("bail", new String[]{"pay-bail", "b"}, "", "Pay your bail", "");
		// TODO Auto-generated constructor stub
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
				if(mp.isJailed()){
					if(mp.hasMoney(50)){
						mp.payPot(50);
						Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3paid bail and was let out of jail", mp);
						mp.sendMessage("&3You paid bail and were let out of jail");
						mp.sendMessage("&3You can now use &b/mineopoly roll");
						mp.setJailed(false, true);
					}else{
						mp.sendMessage(new InsufficientFundsMessage());
					}
				}else{
					mp.sendMessage(new NotInJailMessage());
				}
			}else{
				Mineopoly.plugin.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}else{
			Mineopoly.plugin.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
		}
	}

}
