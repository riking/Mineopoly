package taco.mineopoly.sections.squares;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.SpecialSquare;

public class JailSquare extends SpecialSquare {

	private final double visitX = 119.5;
	private final double visitZ = 2.5;
	private final double cellX = 116;
	private final double cellZ = 12;

	public JailSquare() {
		super(10, "Jail/Just Visiting", '1');
	}
	
	@Override
	public void provokeAction(MineopolyPlayer player) {
		//nothing to do
	}

	@Override
	public void getInfo(Player player) {
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&6---[" + getColorfulName() + "&b(&3" + getId() + "&b)&6]---");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, ("&3Just Visiting&7:&b You are only visiting, you can watch everyone in jail."));
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&3Jail&7:&b You are in jail and must wait until either one of three things happens:");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "   &1*&3You roll doubles");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "   &1*&3You pay bail (&250&b)");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "   &1*&3You use a &1Get Out of Jail Free &3card");
	}
	public Location getJailCellLocation(){
		Location origin = Mineopoly.config.getBoardOrigin();
		origin.setX(origin.getX() - cellX);
		origin.setZ(origin.getZ() - cellZ);
		origin.setPitch(0);
		origin.setYaw(180);
		return origin;
	}
	
	public Location getJustVisitingLocation(){
		Location origin = Mineopoly.config.getBoardOrigin();
		origin.setX(origin.getX() - visitX);
		origin.setZ(origin.getZ() - visitZ);
		origin.setPitch(0);
		origin.setYaw(180);
		return origin;
	}

}
