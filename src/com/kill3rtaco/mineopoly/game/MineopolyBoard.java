package com.kill3rtaco.mineopoly.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.game.cards.chance.ChanceCardSet;
import com.kill3rtaco.mineopoly.game.cards.communitychest.CommunityChestCardSet;
import com.kill3rtaco.mineopoly.game.sections.ChanceSection;
import com.kill3rtaco.mineopoly.game.sections.CommunityChestSection;
import com.kill3rtaco.mineopoly.game.sections.OwnableSection;
import com.kill3rtaco.mineopoly.game.sections.Property;
import com.kill3rtaco.mineopoly.game.sections.Railroad;
import com.kill3rtaco.mineopoly.game.sections.SectionType;
import com.kill3rtaco.mineopoly.game.sections.TaxSection;
import com.kill3rtaco.mineopoly.game.sections.Utility;
import com.kill3rtaco.mineopoly.game.sections.squares.*;

import com.kill3rtaco.tacoapi.TacoAPI;
import com.kill3rtaco.tacoapi.obj.WorldEditObject;

public class MineopolyBoard implements Iterable<MineopolySection>{

	private ArrayList<MineopolyPlayer> players;
	private ArrayList<MineopolySection> sections;
	private ChanceCardSet chanceCards;
	private CommunityChestCardSet communityChestCards;
	private MineopolyPot pot = new MineopolyPot();
	
	public MineopolyBoard(){
		Mineopoly.plugin.chat.out("[Game] [Board] Loading game board...");
		Location origin = Mineopoly.config.getBoardOrigin();
		if(Mineopoly.config.getBoolean("mineopoly.schematic.needs_paste")){
			WorldEditObject we = TacoAPI.getWorldEditAPI();
			Mineopoly.plugin.chat.sendGlobalMessage("&ePasting Mineopoly board... There will be some lag");
			we.pasteSchematic(origin.getWorld().getName(), Mineopoly.plugin.getDataFolder() + "/mineopoly.schematic", origin);
			Mineopoly.plugin.chat.sendGlobalMessage("&ePaste Complete");
			Mineopoly.config.setBoolean("mineopoly.schematic.needs_paste", false);
		}
		players = new ArrayList<MineopolyPlayer>();
		sections = new ArrayList<MineopolySection>();
		initSections();
		initCardSets();
		Mineopoly.plugin.chat.out("[Game] [Board] Done loading!");
	}
	
	private void initSections() {
		Mineopoly.plugin.chat.out("[Game] [Board] [Sections] Loading board sections...");
		// property(name, mColor, side, buyPrice, rent[]{site, 1h, 2h, 3h, 4h, hotel})
		sections.add(new GoSquare());
		sections.add(new Property(1, "mediterranean_ave", MineopolyColor.PURPLE, 0, 60, new int[]{2, 10, 30, 90, 160, 250}));
		sections.add(new CommunityChestSection(2, 0));
		sections.add(new Property(3, "baltic_ave", MineopolyColor.PURPLE, 0, 60, new  int[]{4, 20, 60, 180, 320, 450}));
		sections.add(new TaxSection(4, "Income Tax", '7', 0, 200));
		sections.add(new Railroad("reading", 0));
		sections.add(new Property(6, "oriental_ave", MineopolyColor.LIGHT_BLUE, 0, 100, new int[]{6, 30, 90, 270, 400, 550}));
		sections.add(new ChanceSection(7, 0));
		sections.add(new Property(8, "vermont_ave", MineopolyColor.LIGHT_BLUE, 0, 100, new int[]{6, 30, 90, 270, 400, 550}));
		sections.add(new Property(9, "connecticut_ave", MineopolyColor.LIGHT_BLUE, 0, 120, new int[]{8, 40, 100 ,300, 450, 600}));
		sections.add(new JailSquare());
		sections.add(new Property(11, "st_charles_place", MineopolyColor.MAGENTA, 1, 140, new int[]{10, 50, 150, 450, 625, 750}));
		sections.add(new Utility(12, "electric", '4', 1));
		sections.add(new Property(13, "states_ave", MineopolyColor.MAGENTA, 1, 140, new int[]{10, 50, 150, 450, 625, 750}));
		sections.add(new Property(14, "virginia_ave", MineopolyColor.MAGENTA, 1, 160, new int[]{12, 60, 180, 500, 700, 900}));
		sections.add(new Railroad("pennsylvania", 1));
		sections.add(new Property(16, "st_james_place", MineopolyColor.ORANGE, 1, 180, new int[]{14, 70, 200, 550, 750, 950}));
		sections.add(new CommunityChestSection(17, 1));
		sections.add(new Property(18, "tennessee_ave", MineopolyColor.ORANGE, 1, 180, new int[]{14, 70, 200, 550, 750, 950}));
		sections.add(new Property(19, "new_york_ave", MineopolyColor.ORANGE, 1, 200, new int[]{16, 80, 220, 600, 800, 1000}));
		sections.add(new FreeParkingSquare());
		sections.add(new Property(21, "kentucky_ave", MineopolyColor.RED, 2, 220, new int[]{18, 90, 250, 700, 875, 1050}));
		sections.add(new ChanceSection(22, 2));
		sections.add(new Property(23, "indiana_ave", MineopolyColor.RED, 2, 220, new int[]{18, 90, 250, 700, 875, 1050}));
		sections.add(new Property(24, "illinois_ave", MineopolyColor.RED, 2, 240, new int[]{20, 100, 300, 750, 925, 1100}));
		sections.add(new Railroad("b_o", 2));
		sections.add(new Property(26, "atlantic_ave", MineopolyColor.YELLOW, 2, 260, new int[]{22, 110, 330, 800, 975, 1150}));
		sections.add(new Property(27, "ventor_ave", MineopolyColor.YELLOW, 2, 260, new int[]{22, 110, 330, 800, 975, 1150}));
		sections.add(new Utility(28, "water", '1', 2));
		sections.add(new Property(29, "marvin_gardens", MineopolyColor.YELLOW, 2, 280, new int[]{22, 120, 360, 850, 1025, 1200}));
		sections.add(new GoToJailSquare());
		sections.add(new Property(31, "pacific_ave", MineopolyColor.GREEN, 3, 300, new int[]{26, 130, 390, 900, 110, 1275}));
		sections.add(new Property(32, "north_carolina_ave", MineopolyColor.GREEN, 3, 300, new int[]{26, 130, 390, 900, 110, 1275}));
		sections.add(new CommunityChestSection(33, 3));
		sections.add(new Property(34, "pennsylvania_ave", MineopolyColor.GREEN, 3, 320, new int[]{28, 150, 450, 1000, 1200, 1400}));
		sections.add(new Railroad("short_line", 3));
		sections.add(new ChanceSection(36, 3));
		sections.add(new Property(37, "park_place", MineopolyColor.BLUE, 3, 350, new int[]{35, 175, 500, 1100, 1300, 1500}));
		sections.add(new TaxSection(38, "Luxury Tax", '7', 3, 75));
		sections.add(new Property(39, "boardwalk", MineopolyColor.BLUE, 3, 400, new int[]{50, 200, 600, 1400, 1700, 2000}));
		Mineopoly.plugin.chat.out("[Game] [Board] [Sections] Done!");
	}
	
	public void initCardSets(){
		chanceCards = new ChanceCardSet();
		communityChestCards = new CommunityChestCardSet();
	}
	
	public ChanceCardSet getChanceCards(){
		return chanceCards;
	}
	
	public CommunityChestCardSet getCommunityChestCards(){
		return communityChestCards;
	}
	
	public MineopolySection getSection(String name){
		for(MineopolySection ms : this){
			if(ms.getName().replace(" ", "_").equalsIgnoreCase(name)){
				return ms;
			}
		}
		return null;
	}
	
	public MineopolySection getSection(int id){
		if(id >= 40)
			id = id - (40 * (id % 40));
		else if(id < 0)
			return null;
		return sections.get(id);
	}
	
	public ArrayList<MineopolySection> getAllSections(){
		return sections;
	}
	
	public MineopolySection getRandomSection(){
		return getRandomSection("");
	}
	
	public MineopolySection getRandomSection(String exclude){
		MineopolySection section = null;
		Random random = new Random();
		while(section == null){
			int index = random.nextInt(sections.size());
			MineopolySection potential = sections.get(index);
			if(!potential.getName().equalsIgnoreCase(exclude)){
				return potential;
			}
		}
		return null;
	}
	
	public MineopolySection getRandomSectionNotCurrent(){
		MineopolySection current = Mineopoly.plugin.getGame().getPlayerWithCurrentTurn().getCurrentSection();
		return getRandomSection(current.getName());
	}
	
	public ArrayList<OwnableSection> getOwnableSections(){
		ArrayList<OwnableSection> properties = new ArrayList<OwnableSection>();
		for(MineopolySection section : sections){
			if(section instanceof OwnableSection){
				properties.add((OwnableSection) section);
			}
		}
		return properties;
	}
	
	public ArrayList<Railroad> getRailroads(){
		ArrayList<Railroad> railroads = new ArrayList<Railroad>();
		for(MineopolySection section : sections){
			if(section instanceof Railroad){
				railroads.add((Railroad) section);
			}
		}
		return railroads;
	}
	
	public ArrayList<Utility> getUtilities(){
		ArrayList<Utility> utilities = new ArrayList<Utility>();
		utilities.add((Utility) this.sections.get(12));
		utilities.add((Utility) this.sections.get(28));
		return utilities;
	}
	
	public MineopolyPlayer getRandomPlayer(String exclude){
		Random random = new Random();
		MineopolyPlayer player = null;
		if(players.size() == 1) exclude = "";
		while(player == null){
			int index = random.nextInt(players.size());
			MineopolyPlayer potential = players.get(index);
			if(!potential.getName().equalsIgnoreCase(exclude)){
				player = potential;
				break;
			}
		}
		return player;
	}
	
	public MineopolyPlayer getRandomPlayer(){
		return getRandomPlayer("");
	}

	public MineopolyPlayer getRandomPlayerNotCurrent() {
		return getRandomPlayer(Mineopoly.plugin.getGame().getPlayerWithCurrentTurn().getName());
	}

	public ArrayList<MineopolyPlayer> getPlayers(){
		return this.players;
	}
	
	public String[] getPlayerList(){
		String[] players = new String[this.players.size()];
		for(int i=0; i<players.length; i++){
			players[i] = this.players.get(i).getName();
		}
		return players;
	}
	
	public MineopolyPlayer getPlayer(Player p){
		return getPlayer(p.getName());
	}
	
	public MineopolyPlayer getPlayer(String name){
		for(MineopolyPlayer mp : players){
			if(mp.getName().equalsIgnoreCase(name)){
				return mp;
			}
		}
		return null;
	}
	
	public void addPlayer(MineopolyPlayer player){
		this.players.add(player);
	}
	
	public void removePlayer(MineopolyPlayer player){
		for(MineopolyPlayer p : players){
			if(p.getName().equalsIgnoreCase(player.getName())){
				p.getPlayer().setAllowFlight(false);
				players.remove(p);
				break;
			}
		}
		if(Mineopoly.plugin.getGame().getBoard().getPlayers().size() == 0){
			Mineopoly.plugin.getGame().end();
		}
	}
	
	public void removeAllPlayers(){
		for(MineopolyPlayer p : players){
			Player player = p.getPlayer();
			player.setAllowFlight(false);
			TacoAPI.getPlayerAPI().setToLastGameMode(player);
			Location location = Mineopoly.plugin.getGame().locations.get(p.getName());
			TacoAPI.getPlayerAPI().teleport(player, location);
			Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "Game mode reverted");
			Mineopoly.plugin.chat.sendPlayerMessageNoHeader(player, "Teleported to last known location");
		}
		players.clear();
	}
	
	public MineopolyPot getPot(){
		return this.pot;
	}
	
	public Location getOrigin(){
		return Mineopoly.config.getBoardOrigin();
	}
	
	public int getTotalOwnedProperties(){
		return getTotalOwnedSections(SectionType.PROPERTY);
	}
	
	public int getTotalOwnedUtilities(){
		return getTotalOwnedSections(SectionType.UTILITY);
	}
	
	public int getTotalOwnedRailroads(){
		return getTotalOwnedSections(SectionType.RAILROAD);
	}
	
	private  int getTotalOwnedSections(SectionType type){
		int total = 0;
		for(MineopolyPlayer mp : players){
			for(OwnableSection s : mp.ownedSections()){
				if(s.getType() == type){
					total++;
				}
			}
		}
		return total;
	}
	
	public ArrayList<MineopolyPlayer> getJailedPlayers(){
		ArrayList<MineopolyPlayer> jailed = new ArrayList<MineopolyPlayer>();
		for(MineopolyPlayer mp : players){
			if(mp.isJailed()){
				jailed.add(mp);
			}
		}
		return jailed;
	}

	@Override
	public Iterator<MineopolySection> iterator() {
		return sections.iterator();
	}
}
