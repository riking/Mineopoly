package taco.mineopoly.cmds;

import taco.mineopoly.cmds.jail.JailBailCommand;
import taco.mineopoly.cmds.jail.JailCardCommand;
import taco.mineopoly.cmds.jail.JailRollCommand;
import taco.tacoapi.api.command.TacoCommandHandler;

public class JailCommandHandler extends TacoCommandHandler {

	@Override
	protected void registerCommands() {
		registerCommand(new JailRollCommand());
		registerCommand(new JailCardCommand());
		registerCommand(new JailBailCommand());
	}

}
