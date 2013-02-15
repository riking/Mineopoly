package taco.mineopoly.cmds;

import org.bukkit.entity.Player;

import taco.tacoapi.TacoAPI;
import taco.tacoapi.api.TacoCommandHandler;
import taco.mineopoly.Mineopoly;
import taco.mineopoly.cmds.mineopoly.MineopolyEndTurnCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyJoinChannelCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyJoinCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyKickPlayerCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyDeedsCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyMonopoliesCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyQueueCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyQuitCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyRollCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyStartCommand;
import taco.mineopoly.cmds.mineopoly.MineopolyStatsCommand;

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
		registerCommand(new MineopolyStatsCommand());
		registerCommand(new MineopolyStartCommand());
	}

	@Override
	protected boolean onConsoleCommand() {
		return false;
	}

	@Override
	protected void onPlayerCommand(Player player) {
		Mineopoly.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("&c/mineopoly"));
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&3Aliases&7: &b/mineopoly&7, &b/mgame&7, &b/m");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&3Commands&7: &b/mineopoly ? [page]");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("Plugin Information"));
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&6Author&7: &cKILL3RTACO");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&6Version&7: &5" + Mineopoly.plugin.getDescription().getVersion());
	}

}
