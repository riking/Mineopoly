package taco.mineopoly.sections;

import taco.mineopoly.MineopolyPlayer;

/**
 * Represents a section that a player can own on the board
 * @author Taco
 *
 */
public interface Ownable {

	public boolean isOwned();
	
	public MineopolyPlayer getOwner();
	
	public void setOwner(MineopolyPlayer player);
	
	public int getRent();
	
	public int getPrice();
	
	public void reset();
	
}
