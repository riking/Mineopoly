package com.kill3rtaco.mineopoly;

import java.io.File;

import org.bukkit.Server;

import com.kill3rtaco.mineopoly.cmds.JailCommandHandler;
import com.kill3rtaco.mineopoly.cmds.MineopolyCommandHandler;
import com.kill3rtaco.mineopoly.cmds.PropertyCommandHandler;
import com.kill3rtaco.mineopoly.listener.MineopolyListener;

import com.kill3rtaco.tacoapi.api.TacoPlugin;

public class Mineopoly extends TacoPlugin{

	public static MineopolyConfig config;
	private MineopolyGame game;
	public static Mineopoly plugin;
	public static Server server;
	private MineopolyQueue queue;
	
	public void onStop(){
		chat.out("Disabled");
	}
	
	public void onStart(){
		File file = new File(getDataFolder() + "/config.yml");
		config = new MineopolyConfig(file);
		plugin = this;
		server = getServer();
		queue = new MineopolyQueue();
		restartGame();
		registerCommand(new MineopolyCommandHandler());
		registerCommand(new PropertyCommandHandler());
		registerCommand(new JailCommandHandler());
		registerEvents(new MineopolyListener());
		if(config.useMetrics())
			startMetrics();
		chat.out("Enabled");
	}
	
	public MineopolyQueue getQueue(){
		return queue;
	}
	
	public MineopolyGame getGame(){
		return game;
	}
	
	public void restartGame(){
		game = new MineopolyGame();
	}
	
}
