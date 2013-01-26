package taco.mineopoly.cmds.jail;

import java.util.Random;

import org.bukkit.entity.Player;

import taco.mineopoly.Mineopoly;
import taco.mineopoly.MineopolyPlayer;
import taco.mineopoly.messages.CannotPerformActionMessage;
import taco.mineopoly.messages.InvalidTurnMessage;
import taco.mineopoly.messages.NotPlayingGameMessage;
import taco.tacoapi.api.command.TacoCommand;

public class JailRollCommand extends TacoCommand{

	@Override
	protected String[] getAliases() {
		return new String[]{"roll"};
	}

	@Override
	public boolean onConsoleCommand(String[] arg0) {
		return false;
	}

	@Override
	public boolean onPlayerCommand(Player player, String[] args) {
		if(Mineopoly.plugin.getGame().isRunning()){
			if(Mineopoly.plugin.getGame().hasPlayer(player)){
				MineopolyPlayer p = Mineopoly.plugin.getGame().getBoard().getPlayer(player);
				if(p.hasTurn()){
					if(!p.isJailed()){
						p.sendMessage("&cYou cannot use that command because you aren't jailed. Please use &6/mineopoly roll &6instead");
					}else{
						if(p.canRoll()){
							Random random = new Random();
							int roll1 = random.nextInt(6) + 1;
							int roll2 = random.nextInt(6) + 1;
							if(roll1 == roll2){
								p.setJailed(false);
								Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + p.getName() + "&3rolled doubles and was let out of jail", p);
								p.sendMessage("&3You rolled doubles and were let out of jail");
								p.sendMessage("&3You can now use &b/mineopoly roll &3on your next turn");
								p.setTurn(false, true);
							}else{
								if(p.getJailRolls() == 2 && p.getMoney() >= 50){
									p.setJailed(false);
									Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + p.getName() + " &3rolled three times without rolling doubles and was let out of jail", p);
									p.sendMessage("&3You were let out of jail because you rolled 3 times without rolling doubles");
									p.sendMessage("&3You can now use &b/mineopoly roll &3on your next turn");
									p.setTurn(false, true);
								}else if(!(p.getMoney() >= 50)){
									Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + p.getName() + " &3rolled three times, but cannot make bail (&250&3) and was not let out of jail", p);
									p.sendMessage("&3You were not let out of jail because you cannot make bail (&250&3)");
									p.sendMessage("&3You must stay until you roll doubles, use a &bGet out of Jail Free &3card, or have &250");
								}else{
									Mineopoly.plugin.getGame().getChannel().sendMessage("&b" + p.getName() + " &3rolled and was not let out of jail", p);
									p.sendMessage("&3You were not let of jail");
									p.setJailRolls(p.getJailRolls() + 1);
									p.setTurn(false, false);
								}
							}
						}else{
							p.sendMessage(new CannotPerformActionMessage() + "");
						}
					}
				}else{
					p.sendMessage(new InvalidTurnMessage() + "");
				}
			}else{
				player.sendMessage(new NotPlayingGameMessage() + "");
			}
		}else{
			player.sendMessage(new NotPlayingGameMessage() + "");
		}
		return true;
	}

}
