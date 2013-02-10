package taco.mineopoly.cmds;

import org.bukkit.entity.Player;

import taco.tacoapi.api.TacoCommandHandler;
import taco.mineopoly.cmds.property.PropertyAddHotelCommand;
import taco.mineopoly.cmds.property.PropertyAddHouseCommand;
import taco.mineopoly.cmds.property.PropertyBuyCommand;
import taco.mineopoly.cmds.property.PropertyInfoCommand;

public class PropertyCommandHandler extends TacoCommandHandler {

	public PropertyCommandHandler() {
		super("property", "Mineopoly Property commands", "");
	}

	@Override
	protected void registerCommands() {
		registerCommand(new PropertyAddHouseCommand());
		registerCommand(new PropertyAddHotelCommand());
		registerCommand(new PropertyBuyCommand());
		registerCommand(new PropertyInfoCommand());
	}

	@Override
	protected boolean onConsoleCommand() {
		return false;
	}

	@Override
	protected void onPlayerCommand(Player arg0) {
	}

}
