package taco.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.api.TacoMessage;

public class MineopolyChannelListener{
	
	private Player player;
	
	public MineopolyChannelListener(Player player) {
		this.player = player;
	}

	public void sendMessage(String message){
		Player player = Mineopoly.server.getPlayer(getName());
		Mineopoly.plugin.chat.sendPlayerMessage(player, message);
	}
	
	public void sendMessage(TacoMessage message){
		Player player = Mineopoly.server.getPlayer(getName());
		Mineopoly.plugin.chat.sendPlayerMessage(player, message.getMessage());
	}
	
	public String getName(){
		return player.getName();
	}
	
	public Player getPlayer(){
		return player;
	}
	
}
