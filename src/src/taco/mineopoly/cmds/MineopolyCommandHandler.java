package taco.mineopoly.cmds;

import taco.tacoapi.api.command.TacoCommandHandler;
import taco.mineopoly.cmds.mineopoly.MineopolyEndTurnCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyHelpCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyJoinChannelCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyJoinCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyKickPlayerCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyMapCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyDeedsCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyQueueCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyQuitCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyRollCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyStatsCommand;

public class MineopolyCommandHandler extends TacoCommandHandler{

	@Override
	protected void registerCommands() {
		registerCommand(new MineopolyEndTurnCommand());
		registerCommand(new MineopolyHelpCommand());
		registerCommand(new MineopolyJoinCommand());
		registerCommand(new MineopolyJoinChannelCommand());
		registerCommand(new MineopolyKickPlayerCommand());
		registerCommand(new MineopolyMapCommand());
		registerCommand(new MineopolyQueueCommand());
		registerCommand(new MineopolyQuitCommand());
		registerCommand(new MineopolyRollCommand());
		registerCommand(new MineopolyStatsCommand());
		registerCommand(new MineopolyDeedsCommand());
	}

}
