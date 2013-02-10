package taco.mineopoly.cmds.mineopoly;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyColor;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.Permissions;
import taco.mineopoly.messages.GameNotInProgressMessage;
import taco.tacoapi.TacoAPI;
import taco.tacoapi.api.TacoCommand;
import taco.tacoapi.api.messages.PlayerNotOnlineMessage;

public class MineopolyMonopoliesCommand extends TacoCommand {

	public MineopolyMonopoliesCommand() {
		super("monopolies", new String[]{"monos", "ms"}, "[player]", "View a player's monopolies", "");
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			if(Mineopoly.plugin.getGame().isRunning()){
				if(Mineopoly.plugin.getGame().hasPlayer(player)){
					MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
					if(mp.ownedSections().size() == 0){
						Mineopoly.chat.sendPlayerMessage(player, "&cYou do not have any monopolies");
					}else{
						String s = "";
						ArrayList<MineopolyColor> monopolies = mp.getMonopolies();
						for(int i=0; i<monopolies.size(); i++){
							if(i == monopolies.size() - 1)
								s = s + monopolies.get(i).getName();
							else s = s + monopolies.get(i).getName() + "&8, ";
						}
						Mineopoly.chat.sendPlayerMessage(player, TacoAPI.getChatUtils().createHeader("&3" + mp.getName() + "&b's Monopolies"));
						Mineopoly.chat.sendPlayerMessage(player, s);
					}
				}
			}
		}else{
			if(Mineopoly.plugin.getGame().isRunning()){
				if(Mineopoly.plugin.getGame().hasPlayer(player) || player.hasPermission(Permissions.VIEW_GAME_STATS)){
					Player p = Mineopoly.plugin.getServer().getPlayer(args[0]);
					if(p ==  null){
						player.sendMessage(new PlayerNotOnlineMessage(args[0]) + "");
					}else{
						if(Mineopoly.plugin.getGame().hasPlayer(p)){
							MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(p);
							if(mp.ownedSections().size() == 0){
								Mineopoly.chat.sendPlayerMessage(player, "&cThis player does not have any monopolies");
							}else{
								String s = "";
								ArrayList<MineopolyColor> monopolies = mp.getMonopolies();
								for(int i=0; i<monopolies.size(); i++){
									if(i == monopolies.size() - 1)
										s = s + monopolies.get(i).getName();
									else s = s + monopolies.get(i).getName() + "&8, ";
								}
								Mineopoly.chat.sendPlayerMessage(player, TacoAPI.getChatUtils().createHeader("&3" + mp.getName() + "&b's Monopolies"));
								Mineopoly.chat.sendPlayerMessage(player, s);
							}
						}
					}
				}else{
					Mineopoly.chat.sendInvalidPermissionsMessage(player);
				}
			}else{
				Mineopoly.chat.sendPlayerMessage(player, new GameNotInProgressMessage());
			}
		}
	}

}
