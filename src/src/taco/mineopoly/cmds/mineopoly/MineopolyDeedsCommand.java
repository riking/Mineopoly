package taco.mineopoly.cmds.mineopoly;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.mineopoly.sections.MineopolySection;
import taco.tacoapi.api.command.TacoCommand;
import taco.tacoapi.api.messages.PlayerNotOnlineMessage;

public class MineopolyDeedsCommand extends TacoCommand {

	@Override
	protected String[] getAliases() {
		return new String[]{"deeds", "properties", "props", "ps", "ds"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			if(Mineopoly.plugin.getGame().isRunning()){
				if(Mineopoly.plugin.getGame().hasPlayer(player)){
					MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
					if(mp.ownedSections().size() == 0){
						player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cYou do not own any title deeds"));
					}else{
						String s = "";
						ArrayList<MineopolySection> props = mp.ownedSections();
						Collections.sort(props);
						for(int i=0; i<props.size(); i++){
							if(i == props.size() - 1)
								s = s + props.get(i).getColorfulName();
							else s = s + props.get(i).getColorfulName() + "&8, ";
						}
						player.sendMessage(Mineopoly.getChatUtils().formatMessage(Mineopoly.getChatUtils().createHeader("&3" + mp.getName() + "&b's Title Deeds")));
						player.sendMessage(Mineopoly.getChatUtils().formatMessage(s));
					}
				}
			}
		}else{
			if(Mineopoly.plugin.getGame().isRunning()){
				Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
				if(p ==  null){
					player.sendMessage(new PlayerNotOnlineMessage(args[0]) + "");
				}else{
					if(Mineopoly.plugin.getGame().hasPlayer(p)){
						MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
						if(mp.ownedSections().size() == 0){
							player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cThis player does not own any title deeds"));
						}else{
							String s = "";
							ArrayList<MineopolySection> props = mp.ownedSections();
							Collections.sort(props);
							for(int i=0; i<props.size(); i++){
								if(i == props.size() - 1)
									s = s + props.get(i).getColorfulName();
								else s = s + props.get(i).getColorfulName() + "&8, ";
							}
							player.sendMessage(Mineopoly.getChatUtils().formatMessage(Mineopoly.getChatUtils().createHeader("&3" + mp.getName() + "&b's Title Deeds")));
							player.sendMessage(Mineopoly.getChatUtils().formatMessage(s));
						}
					}
				}
			}else{
				player.sendMessage(new GameNotInProgressMessage() + "");
			}
		}
		return true;
	}

	

}
