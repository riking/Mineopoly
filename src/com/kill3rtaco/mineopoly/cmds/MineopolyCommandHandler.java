package com.kill3rtaco.mineopoly.cmds;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyDeedsCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyEndTurnCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyJoinChannelCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyJoinCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyKickPlayerCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyMonopoliesCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyQueueCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyQuitCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyRollCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolySetPasteLocationCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyStartCommand;
import com.kill3rtaco.mineopoly.cmds.mineopoly.MineopolyStatsCommand;

import com.kill3rtaco.tacoapi.TacoAPI;
import com.kill3rtaco.tacoapi.api.TacoCommandHandler;

public class MineopolyCommandHandler extends TacoCommandHandler{

	public MineopolyCommandHandler() {
		super("mineopoly", "Mineopoly commands", "");
	}

	@Override
	protected void registerCommands() {
		registerCommand(new MineopolyDeedsCommand());
		registerCommand(new MineopolyEndTurnCommand());
		registerCommand(new MineopolyJoinCommand());
		registerCommand(new MineopolyJoinChannelCommand());
		registerCommand(new MineopolyKickPlayerCommand());
		registerCommand(new MineopolyMonopoliesCommand());
		registerCommand(new MineopolyQueueCommand());
		registerCommand(new MineopolyQuitCommand());
		registerCommand(new MineopolyRollCommand());
		registerCommand(new MineopolySetPasteLocationCommand());
		registerCommand(new MineopolyStatsCommand());
		registerCommand(new MineopolyStartCommand());
	}

	@Override
	protected boolean onConsoleCommand() {
		return false;
	}

	@Override
	protected void onPlayerCommand(Player player) {
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("&c/mineopoly"));
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&3Aliases&7: &b/mineopoly&7, &b/mgame&7, &b/m");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&3Commands&7: &b/mineopoly ? [page]");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("Plugin Information"));
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&6Author&7: &cKILL3RTACO");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&6Version&7: &5" + Mineopoly.plugin.getDescription().getVersion());
	}

}
