package taco.mineopoly.cmds;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.cmds.jail.JailBailCommand;
import taco.mineopoly.cmds.jail.JailCardCommand;
import taco.mineopoly.cmds.jail.JailRollCommand;
import taco.tacoapi.TacoAPI;
import taco.tacoapi.api.TacoCommandHandler;

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
		Mineopoly.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("&c/jail"));
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&3Aliases&7: &b/jail&7, &b/mineopolyjail&7, &b/mjail&7, &b/j");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&3Commands&7: &b/jail ? [page]");
	}

}
