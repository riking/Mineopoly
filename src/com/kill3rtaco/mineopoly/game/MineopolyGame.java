package com.kill3rtaco.mineopoly.game;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.game.chat.MineopolyChatChannel;
import com.kill3rtaco.mineopoly.game.sections.OwnableSection;
import com.kill3rtaco.mineopoly.game.sections.Property;
import com.kill3rtaco.mineopoly.saves.MineopolySaveGame;
import com.kill3rtaco.mineopoly.tasks.MineopolySessionTask;
import com.kill3rtaco.tacoapi.TacoAPI;

public class MineopolyGame {

	private MineopolyBoard board;
	private MineopolyChatChannel channel;
	private MineopolySaveGame save;
	private int index = 0, sessionTaskId;
	private boolean running, loadedFromSave = false;
	public HashMap<String, Location> locations = new HashMap<String, Location>();
	
	public MineopolyGame(){
		if(canStart()){
			Mineopoly.plugin.chat.out("[Game] Loading new Mineopoly game...");
			board = new MineopolyBoard();
			channel = new MineopolyChatChannel();
			addPlayers();
			nextPlayer();
			this.sessionTaskId = Mineopoly.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Mineopoly.plugin, new MineopolySessionTask(), 0L, 20 * 5);
			this.running = true;
			Mineopoly.plugin.chat.out("[Game] Done loading!");
		}else{
			this.running = false;
		}
	}
	
	public MineopolyGame(MineopolySaveGame save){
		if(canStart(save)){
			Mineopoly.plugin.chat.out("[Game] Loading game from file " + save.getFilename());
			this.save = save;
			board = new MineopolyBoard();
			channel = new MineopolyChatChannel();
			this.loadedFromSave = true;
			this.running = true;
			Mineopoly.plugin.chat.out("[Game] Done loading!");
		}
	}
	
	public void setData(){
		if(loadedFromSave){
			addPlayers();
			channel.sendMessage("&aSetting turn order...");
			String currentTurn = save.getString("game.current-turn");
			String turnOrder = save.getString("game.turn-order");
			String[] names = turnOrder.split(" ");
			channel.sendMessage("&aSetting up MineopolyPot contents...");
			MineopolyPot pot = board.getPot();
			pot.setMoney(save.getInt("board.pot.amount"));
			if(save.getBoolean("board.pot.card_chance")){
				pot.addChanceJailCard();
			}
			if(save.getBoolean("board.path.card_community-chest")){
				pot.addCommunityChestJailCard();
			}
			for(int i=0; i<names.length; i++){
				if(names[i].equalsIgnoreCase(currentTurn)){
					index = i;
				}
			}
			
			channel.sendMessage("&aGiving properties and money to players...");
			for(MineopolyPlayer mp : board.getPlayers()){
				String playerRoot = "players." + mp.getName();
				List<Integer> properties = save.getIntList(playerRoot + ".properties.owned");
				//assign properties
				for(int i : properties){
					String propertyRoot = playerRoot + "properties." + i;
					MineopolySection section = board.getSection(i);
					if(section instanceof OwnableSection){
						OwnableSection ownable = (OwnableSection) section;
						if(ownable instanceof Property){
							Property property = (Property) ownable;
							if(save.getBoolean(propertyRoot + ".hotel")){
								property.addHotel();
							}else{
								property.setHouses(save.getInt(propertyRoot + ".houses"));
							}
							mp.addSection(property);
						}else{
							mp.addSection(ownable);
						}
					}
				}
				boolean jailed = save.getBoolean(playerRoot + ".jailed");
				if(!jailed){
					MineopolySection dest = board.getSection(save.getInt(playerRoot + ".section"));
					mp.setCurrentSection(dest, false, false);
				}
				
				mp.setBalance(save.getInt(playerRoot + ".balance"));
				if(save.getBoolean(playerRoot + ".card_chance")){
					mp.giveChanceJailCard();
				}
				
				if(save.getBoolean(playerRoot + ".card_community-chest")){
					mp.giveCommunityChestJailCard();
				}
				
				if(jailed){
					mp.setJailed(true, true);
				}
				
				mp.setTotalRolls(save.getInt(playerRoot + ".rolls"));
			}
			channel.sendMessage("&aGame setup finished! Starting game...");
			nextPlayer();
			this.sessionTaskId = Mineopoly.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(Mineopoly.plugin, new MineopolySessionTask(), 0L, 20 * 5);
		}
	}
	
	private void addPlayers(){
		Mineopoly.plugin.chat.out("[Game] [Players] Adding Players...");
		if(loadedFromSave){
			String turnOrder = save.getString("game.turn-order");
			String[] names = turnOrder.split(" ");
			for(String s : names){
				Player player = Mineopoly.plugin.getServer().getPlayer(s);
				MineopolyPlayer mp = new MineopolyPlayer(player);
				board.addPlayer(mp);
				channel.addPlayer(mp);
				Mineopoly.plugin.chat.out("[Game] [Players] Player added: " + mp.getName());
				player.setGameMode(GameMode.ADVENTURE);
				player.setAllowFlight(true);
				locations.put(player.getName(), TacoAPI.getPlayerAPI().getLastLocation(player.getName()));
			}
		}else{
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
					Mineopoly.plugin.chat.out("[Game] [Players] Player added: " + player.getName());
					Mineopoly.plugin.getQueue().removePlayer(index);
					player.setCurrentSection(board.getSection(0), false);
					p.setGameMode(GameMode.ADVENTURE);
					p.setAllowFlight(true);
					locations.put(p.getName(), TacoAPI.getPlayerAPI().getLastLocation(p.getName()));
				}else{
					break;
				}
			}
		}
		Mineopoly.plugin.chat.out("[Game] [Players] Done!");
	}
	
	public void nextPlayer(){
		if(index > board.getPlayers().size() - 1)
			index = 0;
		board.getPlayers().get(index).setTurn(true, false);
		MineopolyPlayer currPlayer = getPlayerWithCurrentTurn();
		channel.sendMessage("&3It is &b" + currPlayer.getName() + "&3's turn", currPlayer);
		if(currPlayer != null){
			currPlayer.sendMessage("&3It is your turn");
			if(currPlayer.isJailed())
				currPlayer.sendMessage("&3Use &b/" + Mineopoly.J_ALIAS + " roll&3 to roll the dice!");
			else
				currPlayer.sendMessage("&3Use &b/" + Mineopoly.M_ALIAS + " roll&3 to roll the dice!");
		}
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
		return this.board;
	}
	
	public MineopolyChatChannel getChannel(){
		return channel;
	}
	
	public boolean hasPlayer(Player player){
		if(!running) return false;
		for(MineopolyPlayer p : board.getPlayers()){
			if(p.getName().equalsIgnoreCase(player.getName()))
				return true;
		}
		return false;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public boolean isLoadedFromSave(){
		return loadedFromSave;
	}
	
	public int getSessionTaskId(){
		return sessionTaskId;
	}
	
	public void end(){
		this.running = false;
		Mineopoly.plugin.getServer().getScheduler().cancelTask(sessionTaskId);
		MineopolyPlayer winner = determineWinner();
		board.removeAllPlayers();
		Mineopoly.plugin.chat.sendGlobalMessage("&eThe Mineopoly game has ended");
		Mineopoly.plugin.chat.sendGlobalMessage("&3" + winner.getName() + " &bis the winner!");
		if(TacoAPI.isEconAPIOnline()){
			double reward = Mineopoly.config.getWinReward();
			if(reward > 0){
				String singular = TacoAPI.getEconAPI().currencyName();
				String plural = TacoAPI.getEconAPI().currencyNamePlural();
				Mineopoly.plugin.chat.sendGlobalMessage("&a" + winner.getName() + " &ewins &2" + reward + " " + (reward == 1 ? singular : plural) + " &efor winning");
				TacoAPI.getEconAPI().deposit(winner.getName(), reward);
			}
		}
		if(Mineopoly.plugin.getGame().canStart()){
			Mineopoly.plugin.chat.sendGlobalMessage("&eThe next game will start soon");
		}else{
			Mineopoly.plugin.chat.sendGlobalMessage("&eThe next game will start when there are enough players in the queue");
		}
	}
	
	public MineopolyPlayer determineWinner(){
		WinMethod wm = Mineopoly.config.getWinMethod();
		if(wm == WinMethod.MONEY){
			MineopolyPlayer max = null;
			for(MineopolyPlayer mp : board.getPlayers()){
				if(max == null){
					max = mp;
				}else if(mp.getBalance() > max.getBalance()){
					max = mp;
				}
			}
			return max;
		}else if(wm == WinMethod.PROPERTY_AMOUNT){
			MineopolyPlayer max = null;
			for(MineopolyPlayer mp : board.getPlayers()){
				if(max == null){
					max = mp;
				}else if(mp.ownedSections().size() > max.ownedSections().size()){
					max = mp;
				}
			}
			return max;
		}else{ //property value
			MineopolyPlayer max = null;
			for(MineopolyPlayer mp : board.getPlayers()){
				if(max == null){
					max = mp;
				}else if(mp.valueOfOwnedSections() > max.valueOfOwnedSections()){
					max = mp;
				}
			}
			return max;
		}
	}
	
	public boolean canStart(){
		return Mineopoly.plugin.getQueue().getSize() >= Mineopoly.config.getMinPlayers();
	}
	
	public boolean canStart(MineopolySaveGame save){
		String[] players = save.getString("game.turn-order").split("\\s+");
		for(String s : players){
			Player player = Mineopoly.plugin.getServer().getPlayer(s);
			if(player == null || !player.isOnline()){
				return false;
			}
		}
		return true;
	}
	
	public void kick(MineopolyPlayer player, String reason){
		for(MineopolySection section : player.ownedSections()){
			if(section instanceof OwnableSection){
				((OwnableSection) section).reset();
			}
		}
		
		if(player.hasMoney(1))
			getBoard().getPot().addMoney(player.getBalance());
		Mineopoly.plugin.chat.sendGlobalMessage("&3" + player.getName() + " &bhas been removed from the game (&3" + reason + "&b)");
		MineopolyPlayer current = Mineopoly.plugin.getGame().getPlayerWithCurrentTurn();
		boolean next = player.getName().equalsIgnoreCase(current.getName());
		board.removePlayer(player);
		if(board.getPlayers().size() == 1){
			Mineopoly.plugin.chat.sendGlobalMessage("&3" + board.getPlayers().get(0).getName() + " &bis the winner!");
			end();
			return;
		}
		if(next){
			nextPlayer();
		}
	}
	
	public String getTurnOrder(){
		String turnOrder = "";
		for(int i=0; i<board.getPlayers().size(); i++){
			turnOrder += board.getPlayers().get(i).getName() + " ";
		}
		return turnOrder;
	}
}
