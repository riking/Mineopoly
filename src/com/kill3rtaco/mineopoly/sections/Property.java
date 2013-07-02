package com.kill3rtaco.mineopoly.sections;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyColor;


/**
 * Represents a property space on the board. Implements {@link taco.mineopoly.sections.Ownable Ownable}
 * @author Taco
 *
 */
public class Property extends OwnableSection implements  CardinalSection{

	protected int side;
	private int housePrice = (getSide() + 1) * 50;
	private int hotelPrice = housePrice * 5;
	private int houses;
	private int r, h, h2, h3, h4, hotel;
	protected boolean hasHotel = false;
	protected MineopolyColor mColor;

	public Property(int id, String name, MineopolyColor color, int side, int price, int[] rent) {
		super(id, Mineopoly.config.getPropertyName(name), color.getChar(), price);
		this.mColor = color;
		this.side = side;
		setRent(rent[0], rent[1], rent[2], rent[3], rent[4], rent[5]);
	}
	
	public int getRent(){
		if(isOwned()){
			if(!hasHotel){
				switch(houses){
					case 0:
						return r;
					case 1:
						return h;
					case 2:
						return h2;
					case 3:
						return h3;
					case 4:
						return h4;
					default:
						return r;
				}
			}else{
				return hotel;
			}
		}else return 0;
	}
	
	public void reset(){
		this.mortgaged = false;
		this.houses = 0;
		this.owned = false;
		this.hasHotel = false;
	}
	
	public MineopolyColor getColor(){
		return mColor;
	}
	
	protected void setRent(int r, int h, int h2, int h3, int h4, int hotel){
		this.r = r;
		this.h = h;
		this.h2 = h2;
		this.h3 = h3;
		this.h4 = h4;
		this.hotel = hotel;
	}
	
	public void getInfo(Player player){
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "&6---[" + getColorfulName() + "&b(&3" + getId() + "&b)&6]---");
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, mColor + "Owner&7:&b " + (isOwned() ? owner.getName() : "none"));
		Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, mColor + (isOwned() ? "Rent&7: &2" + getRent() : "Price&7: &2" + getPrice()));
		if(getHouses() > 0 && !hasHotel()) Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, mColor + "Houses&7:&b " + getHouses());
		if(hasHotel) Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, mColor + "Hotel&7:&b " + hasHotel());
	}
	
	public boolean hasHotel(){
		return this.hasHotel;
	}
	
	public int getHouses(){
		return this.houses;
	}
	
	public void setMortgaged(boolean mortgaged){
		this.mortgaged = mortgaged;
		if(mortgaged){
			int refund = 0;
			for(Property p : owner.getPropertiesInMineopoly(mColor)){
				if(!p.getName().equalsIgnoreCase(getName())){
					if(p.getHouses() == 0 && !p.hasHotel){
						break;
					}else{
						refund += (p.getHousePrice() * p.getHouses()) / 2;
						refund += ((p.hasHotel() ? 1 : 0) * p.getHotelPrice()) / 2;
						p.removeAllHouses();
						p.removeHotel();
					}
				}
			}
			owner.addMoney(refund);
		}
	}
	
	public int getHousePrice(){
		return this.housePrice;
	}
	
	public int getHotelPrice(){
		return this.hotelPrice;
	}
	
	public void addHotel(){
		owner.takeMoney(hotelPrice);
		this.hasHotel = true;
		this.houses = 0;
	}
	
	public void addHouse(){
		owner.takeMoney(housePrice);
		this.houses += 1;
	}
	
	public void removeHouse(){
		houses--;
		owner.addMoney(housePrice/2);
	}
	
	public void removeAllHouses(){
		houses = 0;
	}
	
	public void removeHotel(){
		hasHotel = false;
	}

	@Override
	public int getSide() {
		return side;
	}
}
