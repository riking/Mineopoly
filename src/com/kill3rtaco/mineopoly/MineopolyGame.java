package com.kill3rtaco.mineopoly;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.sections.MineopolySection;
import com.kill3rtaco.mineopoly.sections.OwnableSection;


public class MineopolyGame {

	private MineopolyBoard board;
	private MineopolyChatChannel channel;
	private int index = 0;
	private boolean running;
	
	public MineopolyGame(){
		if(canStart()){
			board = new MineopolyBoard();
			channel = new MineopolyChatChannel();
			addPlayers();
			nextPlayer();
			this.running = true;
		}else{
			this.running = false;
		}
	}
	
	private void addPlayers(){
		int queueSize = Mineopoly.plugin.getQueue().getSize();
		int maxPlayers = Mineopoly.config.getMaxPlayers();
		for(int i=1; i<=queueSize; i++){
			if(i < maxPlayers && queueSize > 0){
				Random random = new Random();
				int index = random.nextInt(Mineopoly.plugin.getQueue().getSize());
				Player p = Mineopoly.plugin.getQueue().getPlayer(index);
				MineopolyPlayer player = new MineopolyPlayer(p);
				board.addPlayer(player);
				channel.addPlayer(player);
				Mineopoly.plugin.getQueue().removePlayer(index);
				player.setCurrentSection(board.getSection(0), false);
				p.setGameMode(GameMode.ADVENTURE);
				p.setAllowFlight(true);
			}else{
				break;
			}
		}
	}
	
	public void nextPlayer(){
		if(index > board.getPlayers().size() - 1)
			index = 0;
		board.getPlayers().get(index).setTurn(true, false);
		MineopolyPlayer currPlayer = getPlayerWithCurrentTurn();
		channel.sendMessage("&3It is &b" + currPlayer.getName() + "&3's turn", currPlayer);
		currPlayer.sendMessage("&3It is your turn");
		if(currPlayer.isJailed())
			currPlayer.sendMessage("&3Use &b/mjail roll&3 to roll the dice!");
		else
			currPlayer.sendMessage("&3Use &b/mgame roll&3 to roll the dice!");
		index++;
	}
	
	public MineopolyPlayer getPlayerWithCurrentTurn(){
		for(MineopolyPlayer p : board.getPlayers()){
			if(p.hasTurn()){
				return p;
			}
		}
		return null;
	}
	
	public MineopolyBoard getBoard(){
		return board;
	}
	
	public MineopolyChatChannel getChannel(){
		return channel;
	}
	
	public boolean hasPlayer(Player player){
		for(MineopolyPlayer p : board.getPlayers()){
			if(p.getName().equalsIgnoreCase(player.getName()))
				return true;
		}
		return false;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void end(){
		this.running = false;
		board.removeAllPlayers();
	}
	
	public boolean canStart(){
		return Mineopoly.plugin.getQueue().getSize() >= Mineopoly.config.getMinPlayers();
	}
	
	public void kick(MineopolyPlayer player, String reason){
		for(MineopolySection section : player.ownedSections()){
			if(section instanceof OwnableSection){
				((OwnableSection) section).reset();
			}
		}
		
		if(player.hasMoney(1))
			getBoard().getPot().addMoney(player.getMoney());
		Mineopoly.plugin.chat.sendGlobalMessage("&3" + player.getName() + " &bhas been removed from the game (&3" + reason + "&b)");
		board.removePlayer(player);
		if(board.getPlayers().size() == 1){
			Mineopoly.plugin.chat.sendGlobalMessage("&3" + board.getPlayers().get(0).getName() + " &bis the winner!");
			end();
		}
	}
}
