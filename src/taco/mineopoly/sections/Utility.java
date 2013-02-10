package taco.mineopoly.sections;

import java.util.Random;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;

/**
 * Represents a utility space on the board. Implements {@link taco.mineopoly.sections.Ownable Ownable}
 * @author Taco
 *
 */
public class Utility extends OwnableSection implements CardinalSection{

	private MineopolyPlayer owner;
	private int side;
	
	public Utility(int id, String name, char color, int side) {
		super(id, Mineopoly.config.getCompanyName(name), color, 150);
		this.side = side;
	}

	@Override
	public void getInfo(Player player){
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&6---[" + getColorfulName() +"&6]---");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&" + color + "Owned&7: &b" + (isOwned() ? owner.getName() : "none"));
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&" + color + (isOwned() ? "Rent&7: " + "&bLand on it to find out" : "Price&7: "));
	}
	
	public int getRent(){
		if(this.isOwned()){
			Random random = new Random();
			switch(owner.ownedUtilities()){
				case 2:
					return 10 * ((random.nextInt(6) + 1) + (random.nextInt(6) + 1));
				default:
					return 4 * ((random.nextInt(6) + 1) + (random.nextInt(6) + 1));
			}
		}else{
			return 0;
		}
	}
	public String getName(){
		return super.getName() + " Company";
	}
	
	public String getColorfulName(){
		return super.getColorfulName() + " Company";
	}

	@Override
	public int getSide() {
		return side;
	}
}
