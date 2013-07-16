package com.kill3rtaco.mineopoly;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.Server;

import com.kill3rtaco.mineopoly.cmds.JailCommandHandler;
import com.kill3rtaco.mineopoly.cmds.MineopolyCommandHandler;
import com.kill3rtaco.mineopoly.cmds.PropertyCommandHandler;
import com.kill3rtaco.mineopoly.game.MineopolyGame;
import com.kill3rtaco.mineopoly.game.MineopolyQueue;
import com.kill3rtaco.mineopoly.listener.MineopolyListener;
import com.kill3rtaco.mineopoly.saves.MineopolySaveGame;
import com.kill3rtaco.mineopoly.test.MineopolyPluginTester;

import com.kill3rtaco.tacoapi.TacoAPI;
import com.kill3rtaco.tacoapi.api.TacoPlugin;

public class Mineopoly extends TacoPlugin{

	public static MineopolyConfig config;
	public static String M_ALIAS, P_ALIAS, J_ALIAS;
	private MineopolyGame game;
	public static Mineopoly plugin;
	public static Server server;
	private File banned;
	private ArrayList<String> bannedPlayers;
	private MineopolyQueue queue;
	
	public void onStop(){
		chat.out("Disabled");
	}
	
	public void onStart(){
		File file = new File(getDataFolder() + "/config.yml");
		config = new MineopolyConfig(file);
		banned = new File(getDataFolder() + "/banned-players.txt");
		bannedPlayers = getBannedPlayers();
		plugin = this;
		server = getServer();
		queue = new MineopolyQueue();
		restartGame();
		registerCommand(new MineopolyCommandHandler());
		registerCommand(new PropertyCommandHandler());
		registerCommand(new JailCommandHandler());
		registerEvents(new MineopolyListener());
		J_ALIAS = TacoAPI.getServerAPI().getShortestAlias(this, "jail");
		M_ALIAS = TacoAPI.getServerAPI().getShortestAlias(this, "mineopoly");
		P_ALIAS = TacoAPI.getServerAPI().getShortestAlias(this, "property");
		if(config.useMetrics())
			startMetrics();
	}
	
	public MineopolyQueue getQueue(){
		return queue;
	}
	
	public MineopolyGame getGame(){
		return game;
	}
	
	public void restartGame(){
		restartGame(false);
	}
	
	public void restartGame(boolean runTests){
		game = new MineopolyGame();
		if(runTests && game.isRunning()) MineopolyPluginTester.run();
	}
	
	public void resumeGame(MineopolySaveGame save){
		game = new MineopolyGame(save);
		game.setData();
	}
	
	public ArrayList<String> getBannedPlayers(){
		if(bannedPlayers != null){
			return bannedPlayers;
		}else{
			ArrayList<String> bp = new ArrayList<String>();
			try {
				if(!banned.exists()){
					chat.out("[Bans] Bans file not found, creating...");
					banned.createNewFile();
					chat.out("[Bans] File created");
					return bp;
				}else{
					chat.out("[Bans] Bans file found, reloading...");
					Scanner x = new Scanner(banned);
					while(x.hasNextLine()){
						String line = x.nextLine();
						if(!line.isEmpty()){
							String[] parts = line.split("\\s");
							if(parts.length > 0 && !parts[0].isEmpty()){
								bp.add(parts[0]);
								continue;
							}
						}
					}
					x.close();
					chat.out("[Bans] Found " + bp.size() + " banned players");
					chat.out("[Bans] Done!");
				}
			} catch (IOException e) {
				return bp;
			}
			return bp;
		}
	}
	
	public void banPlayer(String name){
		if(!bannedPlayers.contains(name)){
			bannedPlayers.add(name);
			writeBansToFile();
		}
	}
	
	public void unbanPlayer(String name){
		if(bannedPlayers.contains(name)){
			bannedPlayers.remove(name);
			writeBansToFile();
		}
	}
	
	public boolean isBanned(String name){
		for(String s : bannedPlayers){
			if(s.equalsIgnoreCase(name)) return true;
		}
		return false;
	}
	
	private void writeBansToFile(){
		if(banned.exists()) banned.delete();
		try {
			banned.createNewFile();
			FileWriter writer = new FileWriter(banned);
			for(String s : bannedPlayers){
				writer.append(s + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
