package com.kill3rtaco.mineopoly.game.chat;

import java.util.ArrayList;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.game.MineopolyPlayer;

public class MineopolyChatChannel {

	private ArrayList<MineopolyChannelListener> listeners = new ArrayList<MineopolyChannelListener>();
	
	public void sendMessage(String message){
		for(MineopolyChannelListener p : listeners){
			Mineopoly.plugin.chat.sendPlayerMessage(p.getPlayer(), message);
		}
	}
	
	public void sendMessage(String message, MineopolyPlayer exempt){
		for(MineopolyChannelListener p : listeners){
			if(!p.getName().equalsIgnoreCase(exempt.getName()))
				Mineopoly.plugin.chat.sendPlayerMessage(p.getPlayer(), message);
		}
	}
	
	public void addPlayer(MineopolyChannelListener player){
		listeners.add(player);
	}
	
	public void removePlayer(String name){
		for(MineopolyChannelListener p : listeners){
			if(p.getName().equalsIgnoreCase(name)){
				listeners.remove(p);
			}
		}
	}
	
	public boolean isListeningToChannel(String name){
		for(MineopolyChannelListener p : listeners){
			if(p.getName().equalsIgnoreCase(name)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<MineopolyChannelListener> getListeners(){
		return listeners;
	}
	
}
