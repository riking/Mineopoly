package taco.mineopoly.cmds.mineopoly;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyColor;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.tacoapi.api.command.TacoCommand;
import taco.tacoapi.api.messages.PlayerNotOnlineMessage;

public class MineopolyMonopoliesCommand extends TacoCommand {

	@Override
	protected String[] getAliases() {
		return new String[]{"monopolies", "monos", "ms"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			if(Mineopoly.plugin.getGame().isRunning()){
				if(Mineopoly.plugin.getGame().hasPlayer(player)){
					MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
					if(mp.ownedSections().size() == 0){
						player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cYou do not have any monopolies"));
					}else{
						String s = "";
						ArrayList<MineopolyColor> monopolies = mp.getMonopolies();
						for(int i=0; i<monopolies.size(); i++){
							if(i == monopolies.size() - 1)
								s = s + monopolies.get(i).getName();
							else s = s + monopolies.get(i).getName() + "&8, ";
						}
						player.sendMessage(Mineopoly.getChatUtils().formatMessage(Mineopoly.getChatUtils().createHeader("&3" + mp.getName() + "&b's Monopolies")));
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
							player.sendMessage(Mineopoly.getChatUtils().formatMessage("&cThis player does not have any monopolies"));
						}else{
							String s = "";
							ArrayList<MineopolyColor> monopolies = mp.getMonopolies();
							for(int i=0; i<monopolies.size(); i++){
								if(i == monopolies.size() - 1)
									s = s + monopolies.get(i).getName();
								else s = s + monopolies.get(i).getName() + "&8, ";
							}
							player.sendMessage(Mineopoly.getChatUtils().formatMessage(Mineopoly.getChatUtils().createHeader("&3" + mp.getName() + "&b's Monopolies")));
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
