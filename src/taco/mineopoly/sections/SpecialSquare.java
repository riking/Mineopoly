package taco.mineopoly.sections;

import org.bukkit.entity.Player;

public abstract class SpecialSquare extends MineopolySection implements ActionProvoker {
	
	public SpecialSquare(int id, String name, char color) {
		super(id, name, color);
	}
	
	public void getInfo(Player player){
		
	}
	
}
