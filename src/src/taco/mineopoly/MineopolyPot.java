package taco.mineopoly;

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
	
}
