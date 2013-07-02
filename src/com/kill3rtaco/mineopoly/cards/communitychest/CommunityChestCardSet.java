package com.kill3rtaco.mineopoly.cards.communitychest;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.cards.MineopolyCard;
import com.kill3rtaco.mineopoly.cards.MineopolyCardSet;


public class CommunityChestCardSet extends MineopolyCardSet{

	@Override
	protected void initCards() {
		Mineopoly.plugin.chat.out("Loading Community Chest cards...");
		cards = new ArrayList<MineopolyCard>();
		File container = new File(Mineopoly.plugin.getDataFolder() + "/cards/communitychest/");
		int count = 0;
		if(container.isDirectory()){
			for(File f : container.listFiles()){
				YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
				if(c != null){
					if(c.contains("card.description") && c.contains("card.action")){
						if(!c.getString("card.description").equalsIgnoreCase("") && !c.getString("card.action").equalsIgnoreCase("")){
							CommunityChestCard card = new CommunityChestCard(c.getString("card.description"), c.getString("card.action"));
							if(card.isValid()){
								addCard(card);
								count++;
							}else{
								Mineopoly.plugin.chat.out("[CommunityChestCards] Card is invalid: " + f.getName() + ", skipping...");
							}
						}else{
							Mineopoly.plugin.chat.out("[CommunityChestCards] Card is invalid: " + f.getName() + ", skipping...");
						}
					}else{
						Mineopoly.plugin.chat.out("[CommunityChestCards] Card is invalid: " + f.getName() + ", skipping...");
					}
				}
			}
		}
		Mineopoly.plugin.chat.out("[CommunityChestCards] " + count + " cards loaded!");
	}

	@Override
	protected void addJailCard() {
		addCard(new CommunityChestJailCard());
	}

}
