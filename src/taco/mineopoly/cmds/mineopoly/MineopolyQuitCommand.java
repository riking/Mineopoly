package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.tacoapi.api.TacoCommand;

public class MineopolyQuitCommand extends TacoCommand {

	public MineopolyQuitCommand() {
		super("quit", new String[]{"forfeit", "leave"}, "", "Quit the game", "");
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
				Mineopoly.plugin.getGame().kick(mp, "quit");
			}else{
				Mineopoly.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}else{
			Mineopoly.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
		}
	}

}
