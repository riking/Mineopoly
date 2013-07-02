package com.kill3rtaco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPlayer;
import com.kill3rtaco.mineopoly.messages.GameNotInProgressMessage;
import com.kill3rtaco.mineopoly.messages.InvalidTurnMessage;
import com.kill3rtaco.mineopoly.messages.MustRollFirstMessage;
import com.kill3rtaco.mineopoly.messages.NotPlayingGameMessage;

import com.kill3rtaco.tacoapi.api.TacoCommand;

public class MineopolyEndTurnCommand extends TacoCommand {

	public MineopolyEndTurnCommand() {
		super("end-turn", new String[]{"et"}, "", "End your turn", "");
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		return false;
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer p = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(p.isJailed()){
					Mineopoly.plugin.chat.sendPlayerMessage(player, "&cYou cannot use that command because you are jailed");
					return;
				}
				if(p.hasTurn()){
					if(p.hasRolled()){
						if(p.getMoney() < 0){
							p.sendMessage("&cYou are in debt (negative money) you must gain money before ending your turn. Or you can quit with &3/mineopoly quit");
						}else{
							p.setTurn(false, false);
						}
					}else{
						p.sendMessage(new MustRollFirstMessage("ending your turn"));
					}
				}else{
					p.sendMessage(new InvalidTurnMessage());
				}
			}else{
				Mineopoly.plugin.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
			}
		}else{
			Mineopoly.plugin.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
		}
	}

}
