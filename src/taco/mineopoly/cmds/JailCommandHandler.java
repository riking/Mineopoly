package taco.mineopoly.cmds;

import org.bukkit.entity.Player;

import taco.mineopoly.cmds.jail.JailBailCommand;
import taco.mineopoly.cmds.jail.JailCardCommand;
import taco.mineopoly.cmds.jail.JailRollCommand;
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
	protected void onPlayerCommand(Player arg0) {
	}

}
