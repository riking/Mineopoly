package taco.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.TacoPlayer;
import taco.tacoapi.api.TacoMessage;

public class MineopolyChannelListener extends TacoPlayer{
	private String rawHeader = "&6[&7Mineopoly&6]&f";
	
	public MineopolyChannelListener(Player player) {
		super(player);
	}

	public void sendMessage(String message){
		Player player = Mineopoly.plugin.getServer().getPlayer(getName());
		player.sendMessage(Mineopoly.getChatUtils().formatMessage(rawHeader + " " +  message));
	}
	
	public void sendMessage(TacoMessage message){
		Player player = Mineopoly.plugin.getServer().getPlayer(getName());
		player.sendMessage(message.getMessage());
	}
	
}
