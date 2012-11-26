package taco.mineopoly.sections.squares;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.SpecialSquare;
import taco.tacoapi.api.TacoChatUtils;

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
		TacoChatUtils cu = Mineopoly.getChatUtils();
		player.sendMessage(cu.formatMessage("&6---[" + getColorfulName() + "&6]---"));
		player.sendMessage(cu.formatMessage("&3Just Visiting&7:&b You are only visiting, you can watch everyone in jail."));
		player.sendMessage(cu.formatMessage("&3Jail&7:&b You are in jail and must wait until either one of three things happens:"));
		player.sendMessage(cu.formatMessage("   &1*&3You roll doubles"));
		player.sendMessage(cu.formatMessage("   &1*&3You pay bail (&250&b)"));
		player.sendMessage(cu.formatMessage("   &1*&3You use a &1Get Out of Jail Free &3card"));
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
