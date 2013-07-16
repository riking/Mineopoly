package com.kill3rtaco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPermissions;
import com.kill3rtaco.tacoapi.api.TacoCommand;

public class MineopolyReloadCommand extends TacoCommand {

	public MineopolyReloadCommand() {
		super("reload", new String[]{}, "", "Reload the Mineopoly config", MineopolyPermissions.RELOAD);
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		Mineopoly.config.reload();
		Mineopoly.plugin.chat.sendPlayerMessage(player, "&aConfig reloaded");
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		Mineopoly.config.reload();
		Mineopoly.plugin.chat.out("&aConfig reloaded");
		return true;
	}

}
