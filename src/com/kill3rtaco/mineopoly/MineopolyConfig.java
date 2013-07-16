package com.kill3rtaco.mineopoly;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.World;

import com.kill3rtaco.mineopoly.game.WinMethod;
import com.kill3rtaco.tacoapi.api.TacoConfig;

public class MineopolyConfig extends TacoConfig{

	public MineopolyConfig(File file) {
		super(file);
	}

	@Override
	public void setDefaults() {
		addDefaultValue("mineopoly.game.automatic-turn-ending", false);
		addDefaultValue("mineopoly.game.add-even-when-banned", false);
		addDefaultValue("mineopoly.game.win-method", "MONEY");
		addDefaultValue("mineopoly.game.min_players", 2);
		addDefaultValue("mineopoly.game.max_players", 8);
		addDefaultValue("mineopoly.game.reward", 0);
		addDefaultValue("mineopoly.sessions.session-timeout-minutes", 2);
		addDefaultValue("mineopoly.schematic.world", "world");
		addDefaultValue("mineopoly.schematic.needs_paste", true);
		addDefaultValue("mineopoly.schematic.origin-x", 0);
		addDefaultValue("mineopoly.schematic.origin-y", 64);
		addDefaultValue("mineopoly.schematic.origin-z", 0);
		addDefaultValue("mineopoly.metrics.send_data", true);
		addDefaultValue("mineopoly.names.properties.mediterranean_ave", "Nether Ave"); 			//brown
		addDefaultValue("mineopoly.names.properties.baltic_ave", "Slimey Street");
		addDefaultValue("mineopoly.names.properties.oriental_ave", "Temple Road"); 				//light_blue
		addDefaultValue("mineopoly.names.properties.vermont_ave", "Stronghold Lane");
		addDefaultValue("mineopoly.names.properties.connecticut_ave", "Dungeon Ave");
		addDefaultValue("mineopoly.names.properties.st_charles_place", "St. Blaze Place"); 		//magenta
		addDefaultValue("mineopoly.names.properties.states_ave", "Zombe Gardens");
		addDefaultValue("mineopoly.names.properties.virginia_ave", "Spider Lane");
		addDefaultValue("mineopoly.names.properties.st_james_place", "Enderman Place"); 		//orange
		addDefaultValue("mineopoly.names.properties.tennessee_ave", "Notch Place");
		addDefaultValue("mineopoly.names.properties.new_york_ave", "Skeleton Ave");
		addDefaultValue("mineopoly.names.properties.kentucky_ave", "Bed Place"); 				//red
		addDefaultValue("mineopoly.names.properties.indiana_ave", "Bow and Arrow Place");
		addDefaultValue("mineopoly.names.properties.illinois_ave", "Wolf Lane");
		addDefaultValue("mineopoly.names.properties.atlantic_ave", "Good Street"); 				//yellow
		addDefaultValue("mineopoly.names.properties.ventor_ave", "Bad Street");
		addDefaultValue("mineopoly.names.properties.marvin_gardens", "Ugly Street");
		addDefaultValue("mineopoly.names.properties.pacific_ave", "Cookie Drive"); 				//green
		addDefaultValue("mineopoly.names.properties.north_carolina_ave", "Watermelon Ave"); 
		addDefaultValue("mineopoly.names.properties.pennsylvania_ave", "Pumpkin Road");
		addDefaultValue("mineopoly.names.properties.park_place", "Harbor Ave"); 				//dark_blue
		addDefaultValue("mineopoly.names.properties.boardwalk", "Marshmellow Drive");
		addDefaultValue("mineopoly.names.railroads.reading", "Enchanted");						//railroads
		addDefaultValue("mineopoly.names.railroads.pennsylvania", "Creeper");
		addDefaultValue("mineopoly.names.railroads.b_o", "TNT");
		addDefaultValue("mineopoly.names.railroads.short_line", "The End");
		addDefaultValue("mineopoly.names.companies.electric", "Redstone");						//utilities
		addDefaultValue("mineopoly.names.companies.water", "Water");
		save();
	}
	
	public Location getBoardOrigin(){
		World mWorld = Mineopoly.server.getWorld(getString("mineopoly.schematic.world"));
		return new Location(mWorld, getSchematicInt("x"), getSchematicInt("y"), getSchematicInt("z"));
	}
	
	private int getSchematicInt(String xyz){
		return getInt("mineopoly.schematic.origin-" + xyz);
	}
	
	public String getPropertyName(String orig){
		return getString("mineopoly.names.properties." + orig);
	}
	
	public String getRailroadName(String orig){
		return getString("mineopoly.names.railroads." + orig);
	}
	
	public String getCompanyName(String orig){
		return getString("mineopoly.names.companies." + orig);
	}
	
	public boolean useMetrics(){
		return getBoolean("mineopoly.metrics.send_data");
	}

	public int getMinPlayers(){
		return getInt("mineopoly.game.min_players");
	}

	public int getMaxPlayers(){
		return getInt("mineopoly.game.max_players");
	}
	
	public void setBoardOrigin(Location location){
		setString("mineopoly.schematic.world", location.getWorld().getName());
		setInt("mineopoly.schematic.origin-x", location.getBlockX());
		setInt("mineopoly.schematic.origin-y", location.getBlockY());
		setInt("mineopoly.schematic.origin-z", location.getBlockZ());
		save();
		reload();
	}
	
	public double getSessionTimeout(){
		return getDouble("mineopoly.sessions.session-timeout-minutes");
	}
	
	public boolean getAllowAutomaticTurnEnding(){
		return getBoolean("mineopoly.game.automatic-turn-ending");
	}
	
	public WinMethod getWinMethod(){
		return WinMethod.getWinMethod(getString("mineopoly.game.win-method"));
	}
	
	public double getWinReward(){
		return getDouble("mineopoly.game.reward");
	}
	
	public boolean getAddEvenWhenBanned(){
		return getBoolean("mineopoly.game.add-even-when-banned");
	}
	
}
