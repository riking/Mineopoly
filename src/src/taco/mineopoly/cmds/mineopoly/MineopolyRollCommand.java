package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.command.TacoCommand;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.CannotPerformActionMessage;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;

public class MineopolyRollCommand extends TacoCommand {

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer p = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(p.hasTurn()){
					if(p.isJailed()){
						p.sendMessage("&cYou cannot use that command because you are jailed. Please use &6/jail roll &cinstead");
					}else{
						if(p.canRoll())
							p.roll();
						else
							p.sendMessage(new CannotPerformActionMessage() + "");
					}
				}else{
					p.sendMessage(new InvalidTurnMessage() + "");
				}
			}else{
				player.sendMessage(new NotPlayingGameMessage() + "");
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage() + "");
		}
		return true;
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"roll", "r"};
	}

}
