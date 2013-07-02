package com.kill3rtaco.mineopoly.cmds;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.cmds.jail.JailBailCommand;
import com.kill3rtaco.mineopoly.cmds.jail.JailCardCommand;
import com.kill3rtaco.mineopoly.cmds.jail.JailRollCommand;

import com.kill3rtaco.tacoapi.TacoAPI;
import com.kill3rtaco.tacoapi.api.TacoCommandHandler;

public class JailCommandHandler extends TacoCommandHandler {

	public JailCommandHandler() {
		super("jail", "Mineopoly Jail commands", "");
	}

	@Override
	protected void registerCommands() {
		registerCommand(new JailRollCommand());
		registerCommand(new JailCardCommand());
		registerCommand(new JailBailCommand());
	}

	@Override
	protected boolean onConsoleCommand() {
		return false;
	}

	@Override
	protected void onPlayerCommand(Player player) {
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("&c/jail"));
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&3Aliases&7: &b/jail&7, &b/mineopolyjail&7, &b/mjail&7, &b/j");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&3Commands&7: &b/jail ? [page]");
	}

}
