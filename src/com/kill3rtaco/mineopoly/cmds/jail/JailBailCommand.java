package com.kill3rtaco.mineopoly.cmds.jail;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.game.MineopolyPlayer;
import com.kill3rtaco.mineopoly.messages.GameNotInProgressMessage;
import com.kill3rtaco.mineopoly.messages.InsufficientFundsMessage;
import com.kill3rtaco.mineopoly.messages.InvalidTurnMessage;
import com.kill3rtaco.mineopoly.messages.NotInJailMessage;
import com.kill3rtaco.mineopoly.messages.NotPlayingGameMessage;

import com.kill3rtaco.tacoapi.api.TacoCommand;

public class JailBailCommand extends TacoCommand {

    public JailBailCommand() {
        super("bail", new String[] {"pay-bail", "b"}, "", "Pay your bail", "");
    }

    @Override
    public boolean onConsoleCommand(String[] arg0) {
        return false;
    }

    @Override
    public void onPlayerCommand(Player player, String[] args) {
        if (Mineopoly.plugin.getGame().isRunning()) {
            if (Mineopoly.plugin.getGame().hasPlayer(player)) {
                MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
                if (!mp.hasTurn()) {
                    Mineopoly.plugin.chat.sendPlayerMessage(player, new InvalidTurnMessage());
                    return;
                }
                if (mp.isJailed()) {
                    int bail = Mineopoly.houseRules.bailPrice();
                    if (mp.hasMoney(bail)) {
                        mp.payPot(bail);
                        Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + mp.getName() + " &3paid bail &b(&2" + bail + "&b) and was let out of jail", mp);
                        mp.sendMessage("&3You paid bail &b(&2" + bail + "&b) and were let out of jail");
                        mp.sendMessage("&3You are out of jail. You can now use &b/" + Mineopoly.getMAlias() + " roll on your next turn");
                        mp.setJailed(false, true);
                    } else {
                        mp.sendMessage(new InsufficientFundsMessage());
                    }
                } else {
                    mp.sendMessage(new NotInJailMessage());
                }
            } else {
                Mineopoly.plugin.chat.sendPlayerMessage(player, new NotPlayingGameMessage());
            }
        } else {
            Mineopoly.plugin.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
        }
    }

}
