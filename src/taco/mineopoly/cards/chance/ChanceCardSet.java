package taco.mineopoly.cards.chance;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.YamlConfiguration;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.cards.MineopolyCard;
import taco.mineopoly.cards.MineopolyCardSet;

public class ChanceCardSet extends MineopolyCardSet{

	@Override
	protected void initCards() {
		Mineopoly.chat.out("Loading Chance cards...");
		cards = new ArrayList<MineopolyCard>();
		File container = new File(Mineopoly.plugin.getDataFolder() + "/cards/chance/");
		if(container.isDirectory()){
			for(File f : container.listFiles()){
				YamlConfiguration c = YamlConfiguration.loadConfiguration(f);
				if(c != null){
					if(c.contains("card.description") && c.contains("card.action")){
						if(!c.getString("card.description").equalsIgnoreCase("") && !c.getString("card.action").equalsIgnoreCase("")){
							ChanceCard card = new ChanceCard(c.getString("card.description"), c.getString("card.action"));
							if(card.isValid()){
								addCard(card);
							}else{
								Mineopoly.chat.out("[ChanceCards] Card is invalid: " + f.getName() + ", skipping...");
							}
						}else{
							Mineopoly.chat.out("[ChanceCards] Card is invalid: " + f.getName() + ", skipping...");
						}
					}else{
						Mineopoly.chat.out("[ChanceCards] Card is invalid: " + f.getName() + ", skipping...");
					}
				}
			}
		}
	}

	@Override
	protected void addJailCard() {
		addCard(new ChanceJailCard());
	}

}
