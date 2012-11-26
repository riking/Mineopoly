package taco.mineopoly;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

import taco.mineopoly.sections.ActionProvoker;
import taco.mineopoly.sections.MineopolySection;
import taco.mineopoly.sections.Ownable;
import taco.mineopoly.sections.Property;
import taco.mineopoly.sections.Railroad;
import taco.mineopoly.sections.SpecialSquare;
import taco.mineopoly.sections.Utility;
import taco.mineopoly.sections.squares.JailSquare;

public class MineopolyPlayer extends MineopolyChannelListener{

	private boolean jailed = false;
	private boolean canRoll;
	private boolean hasRolled = false;
	private boolean hasTurn = false;
	private boolean needsGoMoney = false;
	private int money = 1500;
	private int roll1;
	private int roll2;
	private int totalRolls = 0;
	private int doubleRolls = 0;
	private int jailRolls = 0;
	private boolean chanceJailCard;
	private boolean ccJailCard;
	private MineopolySection sectionOn;
	private ArrayList<Ownable> ownedSections = new ArrayList<Ownable>();
	private ArrayList<MineopolyColor> monopolies = new ArrayList<MineopolyColor>();

	public MineopolyPlayer(Player player) {
		super(player);
	}
	
	public void setTurn(boolean turn, boolean supressDoubles){
		this.hasTurn = turn;
		this.canRoll = turn;
		this.hasRolled = !turn;
		if(!turn){
			if(roll1 == roll2 && !supressDoubles){
				this.hasTurn = true;
				this.canRoll = true;
				Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + getName() + "&3 has ended their turn but rolled doubles, rolling again...");
				roll();
			}else{
				Mineopoly.plugin.getGame().nextPlayer();
			}
		}
	}
	
	/**
	 * Gets the amount of money that this player has. Note that this money is separate from any money
	 * earned in the server economy, as the game uses a separate economy to play the game.
	 * @return The amount of money this player has
	 */
	public int getMoney(){
		return this.money;
	}
	
	public boolean hasRolled(){
		return this.hasRolled;
	}
	
	public boolean hasTurn(){
		return this.hasTurn;
	}
	
	private void payRent(){
		if(sectionOn instanceof Ownable){
			Ownable section = (Ownable)sectionOn;
			if(section.isOwned()){
				if(section.getOwner().isJailed()){
					Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + section.getOwner() + " &3cannot collect rent from &b" + getName() + 
							" &3for landing on " + sectionOn.getColorfulName() + " &3because they are &1jailed", section.getOwner());
					section.getOwner().sendMessage("&3You cannot collect rent from &b" + getName() + " &3for landing on " + sectionOn.getColorfulName() +
							" &3because you are &1jailed");
				}else{
					if(!section.getOwner().getName().equalsIgnoreCase(getName())){
						takeMoney(section.getRent());
						section.getOwner().addMoney(section.getRent());
						Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + getName() + " &3paid &b" + section.getOwner().getName() +
								" &2" + section.getRent() + " &3 for landing on " + sectionOn.getColorfulName(), this);
						sendMessage("&You paid &b" + section.getOwner() + " &2 " + section.getRent() + " &3for landing on " + sectionOn.getColorfulName());
					}
				}
			}
		}
	}
	
	public boolean canBuy(Ownable section){
		return getMoney() >= section.getPrice();
	}
	
	public boolean canAddHouse(Property section){
		if(section.getColor() == MineopolyColor.BLUE || section.getColor() == MineopolyColor.PURPLE){
			Property p1 = (Property) Mineopoly.plugin.getGame().getBoard().getSection(section.getColor().getIds()[0]);
			Property p2 = (Property) Mineopoly.plugin.getGame().getBoard().getSection(section.getColor().getIds()[1]);
			boolean first = false;
			boolean second = false;
			if(p1.getHouses() > p2.getHouses())
				second = true;
			else if(p1.getHouses() < p2.getHouses())
				first = true;
			else{
				first = true;
				second = true;
			}
			if(section.getId() == p1.getId())
				return first;
			else
				return second;
		}else{
			Property p1 = (Property) Mineopoly.plugin.getGame().getBoard().getSection(section.getColor().getIds()[0]);
			Property p2 = (Property) Mineopoly.plugin.getGame().getBoard().getSection(section.getColor().getIds()[1]);
			Property p3 = (Property) Mineopoly.plugin.getGame().getBoard().getSection(section.getColor().getIds()[2]);
			boolean first = false;
			boolean second = false;
			boolean third = false;
			if(p1.getHouses() > p2.getHouses()){
				second = true;
				if(p2.getHouses() > p3.getHouses()){
					second = false;
					third = true;
				}else if(p3.getHouses() == p2.getHouses()){
					third = true;
				}
			}else if(p1.getHouses() < p2.getHouses()){
				first = true;
				if(p2.getHouses() > p3.getHouses()){
					first = false;
					third = true;
				}else if(p1.getHouses() == p3.getHouses()){
					third = true;
				}
			}else if(p1.getHouses() == p2.getHouses()){
				first = true;
				second = true;
				if(p2.getHouses() > p3.getHouses()){
					first = false;
					second = false;
					third = true;
				}else if(p2.getHouses() == p3.getHouses()){
					third = true;
				}
			}
			if(section.getId() == p1.getId())
				return first;
			else if(section.getId() == p2.getId())
				return second;
			else
				return third;
		}
	}
	
	public void getInfo(Player p){
		p.sendMessage(Mineopoly.getChatUtils().createHeader("&3Monopoly&7: &b" + getName()));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage("&3Money&7: &2" + getMoney()));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage("&3Rolls&7: &2" + getTotalRolls() + " &3On space: " + getCurrentSection().getColorfulName()));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage("&3Properties&7: &b" + ownedPropertiesSize()));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage("&3Monopolies&7: &b" + monopolySize()));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage(""));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage("&8My properties: &7/mineopoly properties " + getName()));
		p.sendMessage(Mineopoly.getChatUtils().formatMessage("&8My monopolies: &7/mineopoly monopolies " + getName()));
	}
	
	public boolean hasMonopoly(MineopolyColor color){
		if(monopolies.contains(color))
			return true;
		else
			return false;
	}
	
	public void addMoney(int amount){
		this.money += amount;
	}
	
	public void takeMoney(int amount){
		this.money -= amount;
	}
	
	public void payPot(int amount){
		this.money -= amount;
		Mineopoly.plugin.getGame().getBoard().getPot().addMoney(amount);
	}
	
	public boolean hasMoney(int amount){
		return getMoney() >= amount;
	}
	
	public void payPlayer(MineopolyPlayer player, int amount){
		takeMoney(amount);
		player.addMoney(amount);
	}
	
	/**
	 * Invokes the player to roll the dice. If they rolled doubles, the variable {@code canRoll} is set to true.
	 */
	public void roll(){
		this.totalRolls++;
		this.canRoll = false;
		this.hasRolled = true;
		Random random = new Random();
		this.roll1 = random.nextInt(6) + 1;
		this.roll2 = random.nextInt(6) + 1;
		int sum = roll1 + roll2 + getCurrentSection().getId();
		if(sum > 39){
			sum -= 39;
		}
		
		if(roll1 == roll2){
			doubleRolls++;
			if(doubleRolls == 3){
				setJailed(true);
				Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + getName() + "&3was jailed because they rolled doubles 3 times in a row", this);
				sendMessage("&3You were jailed because you rolled doubles 3 times in a row");
			}else{
				move(sum);
			}
		}else{
			doubleRolls = 0;
			move(sum);
		}
	}
	
	private void move(int forward){
		MineopolySection next = Mineopoly.plugin.getGame().getBoard().getSection(forward);
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + getName() + " &3rolled a &b" + roll1 + "&3 and a &b" + roll2, this);
		Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + getName() + " &3 landed on " + next.getColorfulName(), this);
		sendMessage("&3You rolled a &b" + roll1 + "&3 and a &b" + roll2);
		sendMessage("&3You landed on " + next.getColorfulName());
		setCurrentSection(next);
	}
	
	public void setJailRolls(int rolls){
		jailRolls = rolls;
	}
	
	public int getJailRolls(){
		return jailRolls;
	}
	
	public int getTotalRolls(){
		return totalRolls;
	}
	
	public boolean canRoll(){
		return this.canRoll;
	}
	
	/**
	 * Sets the {@link MineopolySection} that this player is on. If the section that they are
	 * on implements {@link Ownable}, then they player will pay the rent required, if it is owned.
	 * @param section
	 */
	public void setCurrentSection(MineopolySection section){
		int lastId = 0;
		if(this.sectionOn != null)
			lastId = this.sectionOn.getId();
		this.sectionOn = section;
		
		if(section.getId() < lastId && section.getId() != 0){
			if(section instanceof JailSquare){
				if(isJailed()){
					needsGoMoney = false;
				}else{
					needsGoMoney = true;
				}
			}else{
				needsGoMoney = true;
			}
		}
		
		if(needsGoMoney){
			addMoney(200);
			Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + getName() + " &3passed &6Go &3and was given &2200", this);
			Mineopoly.plugin.getGame().getChannel().sendMessage("&3You passed &6Go &3and were given &2200");
			needsGoMoney = false;
		}
		
		if(section instanceof SpecialSquare){
			if(section instanceof JailSquare){
				JailSquare js = (JailSquare) section;
				if(isJailed())
					Mineopoly.plugin.getServer().getPlayer(getName()).teleport(js.getJailCellLocation());
				else
					Mineopoly.plugin.getServer().getPlayer(getName()).teleport(js.getJustVisitingLocation());
			}else{
				Mineopoly.plugin.getServer().getPlayer(getName()).teleport(section.getLocation());
				SpecialSquare square = (SpecialSquare) section;
				square.provokeAction(this);
			}
		}else if(section instanceof ActionProvoker){
			Mineopoly.plugin.getServer().getPlayer(getName()).teleport(section.getLocation());
			ActionProvoker ss = (ActionProvoker) section;
			ss.provokeAction(this);
		}else if(section instanceof Ownable){
			Mineopoly.plugin.getServer().getPlayer(getName()).teleport(section.getLocation());
			this.payRent();
		}
	}
	
	public int getOwnedPropertiesWithColor(MineopolyColor color){
		int amount = 0;
		for(Ownable o : ownedSections()){
			if(o instanceof Property){
				Property p = (Property) o;
				if(p.getColor() == color)
					amount++;
			}
		}
		return amount;
	}
	
	public boolean ownsCurrentSection(){
		if(getCurrentSection() instanceof Ownable){
			Ownable section = (Ownable) getCurrentSection();
			return ownsSection(section);
		}else{
			return false;
		}
	}
	
	public int ownedRailRoads(){
		int rr = 0;
		for(Railroad r : Mineopoly.plugin.getGame().getBoard().getRailroads()){
			if(this.ownsSection((Ownable) r))
				rr++;
		}
		return rr;
	}
	
	public int ownedUtilities(){
		int utils = 0;
		for(Utility u : Mineopoly.plugin.getGame().getBoard().getUtilities()){
			if(this.ownsSection((Ownable) u))
					utils++;
		}
		return utils;
	}
	
	public void setJailed(boolean jailed){
		this.jailRolls = 0;
		this.jailed = jailed;
		setCurrentSection(Mineopoly.plugin.getGame().getBoard().getSection(10));
		setTurn(false, true);
	}
	
	public boolean isJailed(){
		return this.jailed;
	}
	
	public void giveChanceJailCard(){
		chanceJailCard = true;
	}
	
	public void takeChanceJailCard(){
		chanceJailCard = false;
	}
	
	public boolean hasChanceJailCard(){
		return chanceJailCard;
	}
	
	public void giveCommunityChestJailCard(){
		ccJailCard = true;
	}
	
	public void takeCommunityChestJailCard(){
		ccJailCard = false;
	}
	
	public boolean hasCommunityChestJailCard(){
		return ccJailCard;
	}
	
	public MineopolySection getCurrentSection(){
		return this.sectionOn;
	}
	
	public boolean ownsSection(Ownable section){
		return ownedSections.contains(section);
	}
	
	public void addSection(Ownable section){
		this.ownedSections.add(section);
		if(section instanceof Property){
			Property p = (Property) section;
			if(p.getColor() == MineopolyColor.BLUE || p.getColor() == MineopolyColor.PURPLE){
				if(getOwnedPropertiesWithColor(p.getColor()) == 2)
					monopolies.add(p.getColor());
			}else{
				if(getOwnedPropertiesWithColor(p.getColor()) == 3)
					monopolies.add(p.getColor());
			}
		}
	}
	
	public ArrayList<Ownable> ownedSections(){
		return ownedSections;
	}
	
	public int ownedPropertiesSize(){
		return ownedSections.size();
	}
	
	public int monopolySize(){
		return monopolies.size();
	}
	
	public ArrayList<MineopolyColor> getMonopolies(){
		return monopolies;
	}
	
	public String toString(){ return getName(); }
	
}
