package taco.mineopoly;

import java.io.File;

import org.bukkit.Server;

import taco.tacoapi.api.TacoPlugin;
import taco.mineopoly.cmds.JailCommandHandler;
import taco.mineopoly.cmds.MineopolyCommandHandler;
import taco.mineopoly.cmds.PropertyCommandHandler;
import taco.mineopoly.listener.MineopolyListener;

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
