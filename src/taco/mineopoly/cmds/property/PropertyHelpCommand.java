package taco.mineopoly.cmds.property;

import taco.tacoapi.api.command.TacoHelpCommand;

public class PropertyHelpCommand extends TacoHelpCommand {

	@Override
	protected String getCommandName() {
		return "property";
	}

	@Override
	protected void initGeneral() {
		gOutput.add("add-house [#] [property name|id]: Add a house or houses to a property");
		gOutput.add("add-hotel [property name|id]: Add a hotel to a property");
		gOutput.add("info [property name|id]: Get info on a property");
	}

	@Override
	protected void initSpecific() {
		sOutput.put(this, "Shows general help for /property\n\nUsing /property ? [sub-command] will give more specific help");
	}

	@Override
	protected String[] getAliases() {
		return new String[]{"help", "?"};
	}

}
