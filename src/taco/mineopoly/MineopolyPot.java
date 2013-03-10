package taco.mineopoly;

import org.bukkit.entity.Player;

import taco.tacoapi.TacoAPI;

public class MineopolyPot {

	private int money;
	private boolean chanceJailCard;
	private boolean ccJailCard;
	
	public MineopolyPot(){
		money = 0;
		chanceJailCard = false;
		ccJailCard = false;
	}
	
	public int getMoney(){
		return money;
	}
	
	public void give(MineopolyPlayer player){
		MineopolyChatChannel channel = Mineopoly.plugin.getGame().getChannel();
		player.addMoney(money);
		int cards = 0;
		if(chanceJailCard) cards++;
		if(ccJailCard) cards++;
		String isCards = (cards==0 ? "" : (cards==1 ? "&3 and a Get Out Of Jail Free card" : "&3 and two Get Out Of Jail Free cards"));
		String message = "&b" +  player + " &3landed on &4Free Parking and was awarded &2" + money + isCards;
		String message2 = "&3You were awarded &2" + money + isCards + " &3for landing on &4Free Parking";
		channel.sendMessage(message, player);
		player.sendMessage(message2);
		if(hasChanceJailCard()) player.giveChanceJailCard();
		if(hasCommunityChestJailCard()) player.giveCommunityChestJailCard();
		money = 0;
		chanceJailCard = false;
		ccJailCard = false;
	}
	
	public boolean hasChanceJailCard(){
		return chanceJailCard;
	}
	
	public boolean hasCommunityChestJailCard(){
		return ccJailCard;
	}
	
	public void addMoney(int amount){
		money += amount;
	}
	
	public void addChanceJailCard(){
		chanceJailCard = true;
	}
	
	public void addCommunityChestJailCard(){
		ccJailCard = true;
	}
	
	public void getInfo(Player player){
		int cards = 0;
		if(chanceJailCard) cards++;
		if(ccJailCard) cards++;
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, TacoAPI.getChatUtils().createHeader("&3Mineopoly Pot"));
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&2Money&7: &2" + getMoney());
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&2Get Out of Jail Free cards&7: &2" + (cards > 0 ? cards : "none"));
//		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&2");
	}
	
}
