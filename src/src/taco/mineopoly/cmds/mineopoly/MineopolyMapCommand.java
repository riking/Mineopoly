package taco.mineopoly.cmds.mineopoly;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.sections.MineopolySection;
import taco.mineopoly.sections.Ownable;
import taco.mineopoly.sections.Property;
import taco.tacoapi.api.TacoChatUtils;
import taco.tacoapi.api.command.TacoCommand;

public class MineopolyMapCommand extends TacoCommand {

	Player p;
	
	@Override
	protected String[] getAliases() {
		return new String[]{"map", "m"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(args.length == 0){
				showMap(player, player);
			}
		}else{
			player.sendMessage(new GameNotInProgressMessage() + "");
		}
		return true;
	}
	
	private void showMap(Player player, Player show){
		p = show;
		TacoChatUtils cu = Mineopoly.getChatUtils();
		String[] s = formatOwnable();
		player.sendMessage(cu.formatMessage("&0+---------------------------------------+"));
		player.sendMessage(cu.formatMessage(getBorder(20) + " &4FP " + getBorder(21) + " " + s[14] + " &r" + getBorder(22) + " &6CH " + getBorder(23) + " " + s[15] + " &r" + getBorder(24) + " " + s[16] + " &r" + getBorder(25) + " &8" + s[17] + "RR &r" + getBorder(26) + " " + s[18] + " &r" + getBorder(27) + " " + s[19] + " &r" + getBorder(28) + " &1" + s[20] + "WC &r" + getBorder(29) + " " + s[21] + " &r" + getBorder(30) + " &1GJ " + getBorder(102)));
		player.sendMessage(cu.formatMessage(getBorder(19) + " " + s[13] + " &r" + getBorder(19) + "                                                 " + getBorder(31) + " " + s[22] + " &r" + getBorder(31)));
		player.sendMessage(cu.formatMessage(getBorder(18) + " " + s[12] + " &r" + getBorder(18) + "                                                 " + getBorder(32) + " " + s[23] + " &r" + getBorder(32)));
		player.sendMessage(cu.formatMessage(getBorder(17) + " &eCC " + getBorder(17) + "                                                 " + getBorder(33) + " &eCC " + getBorder(33)));
		player.sendMessage(cu.formatMessage(getBorder(16) + " " + s[11] + " &r" + getBorder(16) + "                                                 " + getBorder(34) + " " + s[24] + " &r" + getBorder(34)));
		player.sendMessage(cu.formatMessage(getBorder(15) + " &8" + s[10] + "RR &r" + getBorder(15) + "                 &4MINEOPOLY                   " + getBorder(35) + " &8" + s[25] + "RR &r" + getBorder(35)));
		player.sendMessage(cu.formatMessage(getBorder(14) + " " + s[9] + " &r" + getBorder(14) + "                                                 " + getBorder(36) + " &6CH " + getBorder(36)));
		player.sendMessage(cu.formatMessage(getBorder(13) + " " + s[8] + " &r" + getBorder(13) + "                                                 " + getBorder(37) + " " + s[26] + " &r" + getBorder(37)));
		player.sendMessage(cu.formatMessage(getBorder(12) + " &4" + s[7] + "RC &r" + getBorder(12) + "                                                 " + getBorder(38) + " &7LT " + getBorder(38)));
		player.sendMessage(cu.formatMessage(getBorder(11) + " " + s[6] + " &r" + getBorder(11) + "                                                 " + getBorder(39) + " " + s[27] + " &r" + getBorder(39)));
		player.sendMessage(cu.formatMessage(getBorder(101) + " &1JL " + getBorder(10) + " " + s[5] + " &r" + getBorder(9) + " " + s[4] + " &r" + getBorder(8) + " &6CH " + getBorder(7) + " " + s[3] + " &r" + getBorder(6) + " &8" + s[2] + "RR &r" + getBorder(5) + " &7IT " + getBorder(4) + " " + s[1] + " &r" + getBorder(3) + " &eCC " + getBorder(2) + " " + s[0] + " &r" + getBorder(1) + " &6GO " + getBorder(0)));
		player.sendMessage(cu.formatMessage("&0+---------------------------------------+"));
	}
	
	private String[] formatOwnable(){
		String[] propStrings = new String[28];
		int i = 0;
		for(Ownable o : Mineopoly.plugin.getGame().getBoard().getOwnableSections()){
			String s = "";
			if(o instanceof Property){
				Property prop = (Property) o;
				if(o.isOwned()){
					if(o.getOwner().getName().equalsIgnoreCase(p.getName())){
						s = s + prop.getColor().toString() + "&o";
						if(prop.hasHotel())
							s = s + "1&4&oH";
						else
							s = s + prop.getHouses() + "&a&oH";
					}else{
						s = s + prop.getColor().toString() + "&o";
						if(prop.hasHotel())
							s = s + "1&4&oH";
						else
							s = s + prop.getHouses() + "&a&oH";
					}
				}else{
					s = s + prop.getColor().toString() + "0&aH";
				}
			}else{
				if(o.isOwned())
					s = s + "&o";
			}
			propStrings[i] = s;
			i++;
		}
		return propStrings;
	}
	
	public String getBorder(int id){
		if(Mineopoly.plugin.getGame().hasPlayer(p)){
			MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
			MineopolySection on = mp.getCurrentSection();
			if((id >= 0 && id <= 10) || (id >= 21 && id <= 30)){
				if(on.getId() == id || on.getId() == id - 1)
					return "&4|";
				else
					return "&0|";
			}else if ((id >= 11 && id <= 20) || (id >= 31 && id <= 39)){
				if(on.getId() == id)
					return "&4|";
				else
					return "&0|";
			}else if((id == 101 && on.getId() == 10) || (id == 102 && on.getId() == 30)){
				return "&4|";
			}else{
				return "&0|";
			}
		}else{
			return "&0|";
		}
	}
}
