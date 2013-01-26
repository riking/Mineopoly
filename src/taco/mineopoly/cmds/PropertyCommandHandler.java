package taco.mineopoly.cmds;

import taco.tacoapi.api.command.TacoCommandHandler;
import taco.mineopoly.cmds.property.PropertyAddHotelCommand;
import taco.mineopoly.cmds.property.PropertyAddHouseCommand;
import taco.mineopoly.cmds.property.PropertyBuyCommand;
import taco.mineopoly.cmds.property.PropertyHelpCommand;
import taco.mineopoly.cmds.property.PropertyInfoCommand;

public class PropertyCommandHandler extends TacoCommandHandler {

	@Override
	protected void registerCommands() {
		registerCommand(new PropertyAddHouseCommand());
		registerCommand(new PropertyAddHotelCommand());
		registerCommand(new PropertyBuyCommand());
		registerCommand(new PropertyInfoCommand());
//		registerCommand(new PropertyMortgageCommand());
		registerCommand(new PropertyHelpCommand());
	}

}
