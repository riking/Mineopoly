package com.kill3rtaco.mineopoly.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPlayer;
import com.kill3rtaco.mineopoly.messages.OutsideSectionMessage;
import com.kill3rtaco.mineopoly.sections.CardinalSection;
import com.kill3rtaco.mineopoly.sections.MineopolySection;
import com.kill3rtaco.mineopoly.sections.SpecialSquare;
import com.kill3rtaco.mineopoly.sections.squares.JailSquare;


public class MineopolyListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				Mineopoly.plugin.getGame().kick(mp, "disconnected");
			}else{
				if(Mineopoly.plugin.getQueue().playerIsInQueue(player)){
					Mineopoly.plugin.getQueue().removePlayer(player);
				}
			}
		}else{
			if(Mineopoly.plugin.getQueue().playerIsInQueue(player)){
				Mineopoly.plugin.getQueue().removePlayer(player);
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer mp = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				MineopolySection section = mp.getCurrentSection();
				Location sLocation = section.getLocation();
				Location nLocation = event.getTo();
				if(section instanceof SpecialSquare){
					if(section instanceof JailSquare){
						JailSquare js = (JailSquare) section;
						if(mp.isJailed()){
							if((nLocation.getX() >= js.getJailCellLocation().getX() + 6 || nLocation.getX() <= js.getJailCellLocation().getX() - 5) ||
									(nLocation.getY() >= js.getJailCellLocation().getY() + 4 || nLocation.getY() <= js.getJailCellLocation().getY() - 1) ||
									(nLocation.getZ() >= js.getJailCellLocation().getZ() + 5.5 || nLocation.getZ() <= js.getJailCellLocation().getZ() - 6)){
//								player.teleport(js.getJailCellLocation());
								player.teleport(event.getFrom());
								mp.sendMessage("&cYou are &1jailed&c, do not try to escape. You can use a &6Get out of Jail Free &ccard if you need to." +
										" (&6/jail card&c)");
							}
						}else{
							if((nLocation.getX() >= js.getJustVisitingLocation().getX() + 10.5 || nLocation.getX() <= js.getJustVisitingLocation().getX() - 7.5) ||
									(nLocation.getZ() >= js.getJustVisitingLocation().getZ() + 2.5 || nLocation.getZ() <= js.getJustVisitingLocation().getZ() - 15.5)){
//								player.teleport(js.getJustVisitingLocation());
								player.teleport(event.getFrom());
								mp.sendMessage(new OutsideSectionMessage());
							}
						}
					}else{
						if((nLocation.getX() >= sLocation.getX() + 9.5 || nLocation.getX() <= sLocation.getX() - 8.5) ||
								(nLocation.getZ() >= sLocation.getZ() + 8.5 || nLocation.getZ() <= sLocation.getZ() - 9.5)){
//							player.teleport(sLocation);
							player.teleport(event.getFrom());
							mp.sendMessage(new OutsideSectionMessage());
						}
					}
				}else{
					CardinalSection cs = (CardinalSection) section;
					if(cs.getSide() == 0){
						if((nLocation.getX() >= sLocation.getX() + 4.5 || nLocation.getX() <= sLocation.getX() - 4.5) ||
								(nLocation.getZ() >= sLocation.getZ() + 8.5 || nLocation.getZ() <= sLocation.getZ() - 9.5)){
//							player.teleport(sLocation);
							player.teleport(event.getFrom());
							mp.sendMessage(new OutsideSectionMessage());
						}
					}else if(cs.getSide() == 1){
						if((nLocation.getX() >= sLocation.getX() + 9.5 || nLocation.getX() <= sLocation.getX() - 8.5) ||
								(nLocation.getZ() >= sLocation.getZ() + 4.5 || nLocation.getZ() <= sLocation.getZ() - 4.5)){
//							player.teleport(sLocation);
							player.teleport(event.getFrom());
							mp.sendMessage(new OutsideSectionMessage());
						}
					}else if(cs.getSide() == 2){
						if((nLocation.getX() >= sLocation.getX() + 4.5 || nLocation.getX() <= sLocation.getX() - 4.5) ||
								(nLocation.getZ() >= sLocation.getZ() + 9.5 || nLocation.getZ() <= sLocation.getZ() - 8.5)){
//							player.teleport(sLocation);
							player.teleport(event.getFrom());
							mp.sendMessage(new OutsideSectionMessage());
						}
					}else if(cs.getSide() == 3){
						if((nLocation.getX() >= sLocation.getX() + 8.5 || nLocation.getX() <= sLocation.getX() - 9.5) ||
								(nLocation.getZ() >= sLocation.getZ() + 4.5 || nLocation.getZ() <= sLocation.getZ() - 4.5)){
//							player.teleport(sLocation);
							player.teleport(event.getFrom());
							mp.sendMessage(new OutsideSectionMessage());
						}
					}
				}
			}
		}
	}
}
