package com.kill3rtaco.mineopoly.sections;

import com.kill3rtaco.mineopoly.MineopolyPlayer;

public abstract class OwnableSection extends MineopolySection {

	protected MineopolyPlayer owner;
	protected boolean owned, mortgaged;
	protected int price;
	
	public OwnableSection(int id, String name, char color, int price) {
		super(id, name, color);
		this.price = price;
	}

	public boolean isOwned(){
		return owned;
	}
	
	public boolean isMortgaged(){
		return mortgaged;
	}
	
	public void setMortgaged(boolean mortgage){
		this.mortgaged = mortgage;
	}
	
	public MineopolyPlayer getOwner(){
		return owner;
	}
	
	public void setOwner(MineopolyPlayer player){
		this.owner = player;
		this.owned = true;
		player.addSection(this);
	}
	
	public abstract int getRent();
	
	public int getPrice(){
		return price;
	}
	
	public void reset(){
		owned = false;
		mortgaged = false;
	}
	
}
