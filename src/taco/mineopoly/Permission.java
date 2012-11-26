package taco.mineopoly;

public enum Permission {
	
//	BAN_PLAYER_FROM_GAME("Mineopoly.admin.ban"),
	CHANNEL_CHAT("Mineopoly"),
	JOIN_GAME("Mineopoly.general.game"),
	KICK_PLAYER_FROM_GAME("Mineopoly.admin.kick"),
	VIEW_PLAYER_QUEUE("Mineopoly.admin.queue"),
	VIEW_PLAYER_STATS("Mineopoly.general.stats"),
	VIEW_PROPERTY_STATS("Mineopoly.general.property-stats");
	
	private String perm;
	
	private Permission(String perm){
		this.perm = perm;
	}
	
	public String toString(){
		return this.perm;
	}
	
}
