package taco.mineopoly.cmds.mineopoly;

import taco.tacoapi.api.command.TacoHelpCommand;

public class MineopolyHelpCommand extends TacoHelpCommand {

	@Override
	protected String getCommandName() {
		return "mineopoly";
	}

	@Override
	protected void initGeneral() {
		gOutput.add("end-turn: End your turn (if you are playing)");
		gOutput.add("queue: See who is in the game queue");
		gOutput.add("join: Join the game queue");
		gOutput.add("join-channel: Join the current game's chat channel");
		gOutput.add("roll: Roll the dice (if you are playing)");
		gOutput.add("stats: See a player's game stats (if they are playing)");
	}

	@Override
	protected void initSpecific() {
		sOutput.put(new MineopolyJoinChannelCommand(), "Using this command will allow you to see chat from players in the Mineopoly game (as well as sending messages to them)\n\n&bPrecondition:\n\n" +
				"* You must have permission to use this command if you are not in the game yourself");
		sOutput.put(this, "Shows general help for /mineopoly\n\nUsing /mineopoly ? [sub-command] will give you more specific help");
		sOutput.put(new MineopolyQueueCommand(), "Adds you to the game queue (admin command)\n\n&bPrecondition:\n* Must not be in the game queue already\n\n" +
				"&bPostconditions:\n* If you disconnect you will be removed from the queue\n* If a game isn't running and there are enough players in the queue, the game will start");
		sOutput.put(new MineopolyRollCommand(), "Rolls the dice\n\n&bPreconditions:\n* Must be in the game\n* Must be your turn\n* You must have not rolled or rolled doubles in the past turn\n\n" +
				"&bPostcondition:\n* If you roll three doubles in a row, you will be sent to jail");
		sOutput.put(new MineopolyStatsCommand(), "Retrieves info on a player playing Mineopoly\n\n&bPreconditions:\n\n* The player specified must be in game\n* You must have permission to use this command if " +
				"you are not in a game yourself");
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"help", "?"};
	}

}
