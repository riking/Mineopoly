package taco.mineopoly.sections.squares;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.sections.SpecialSquare;

public class GoSquare extends SpecialSquare {
	
	public GoSquare() {
		super(0, "Go", '6');
		// TODO Auto-generated constructor stub
	}

	@Override
	public void provokeAction(MineopolyPlayer player) {
		///player.setNeedsGoMoney(true);
		//if this were to be called the player would get an extra 200 on start and an extra 200 if they land on go
	}

	@Override
	public void getInfo(Player player) {
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&6---[" + getColorfulName() + "&6]---");
		Mineopoly.chat.sendPlayerMessageNoHeader(player, "&bThe inital starting point. Pass this square and you receive &2200");
	}

}
