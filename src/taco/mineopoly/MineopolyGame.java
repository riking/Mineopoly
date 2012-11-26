package taco.mineopoly;

import java.util.Random;

import org.bukkit.entity.Player;

import taco.mineopoly.sections.Ownable;

public class MineopolyGame {

	private MineopolyBoard board;
	private MineopolyChatChannel channel;
	private int index = 0;
	private boolean running;
	
	public MineopolyGame(){
		if(canStart()){
			board = new MineopolyBoard();
			channel = new MineopolyChatChannel();
			addPlayers();
			nextPlayer();
			this.running = true;
		}else{
			this.running = false;
		}
	}
	
	private void addPlayers(){
		for(int i=0; i<Mineopoly.plugin.getQueue().getSize(); i++){
			if(i < Mineopoly.config.getMaxPlayers() && Mineopoly.plugin.getQueue().getSize() > 0){
				Random random = new Random();
				int index = random.nextInt(Mineopoly.plugin.getQueue().getSize());
				MineopolyPlayer player = new MineopolyPlayer(Mineopoly.plugin.getQueue().getPlayer(index));
				board.addPlayer(player);
				channel.addPlayer(player);
				Mineopoly.plugin.getQueue().removePlayer(index);
				player.setCurrentSection(board.getSection(0));
			}else{
				break;
			}
		}
	}
	
	public void nextPlayer(){
		if(index >= board.getPlayers().size() - 1) //will never be greater than size unless there is only one player, used for testing
			index = 0;
		board.getPlayers().get(index).setTurn(true, false);
		index++;
		MineopolyPlayer currPlayer = getPlayerWithCurrentTurn();
		channel.sendMessage("&3It is &3" + currPlayer.getName() + "&3's turn", currPlayer);
		currPlayer.sendMessage("&3It is your turn");
	}
	
	public MineopolyPlayer getPlayerWithCurrentTurn(){
		for(MineopolyPlayer p : board.getPlayers()){
			if(p.hasTurn()){
				return p;
			}
		}
		return null;
	}
	
	public MineopolyBoard getBoard(){
		return board;
	}
	
	public MineopolyChatChannel getChannel(){
		return channel;
	}
	
	public boolean hasPlayer(Player player){
		for(MineopolyPlayer p : board.getPlayers()){
			if(p.getName().equalsIgnoreCase(player.getName()))
				return true;
		}
		return false;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void end(){
		this.running = false;
	}
	
	public boolean canStart(){
		return Mineopoly.plugin.getQueue().getSize() >= Mineopoly.config.getMinPlayers();
	}
	
	public void kick(MineopolyPlayer player, String reason){
		for(Ownable section : player.ownedSections()){
			section.reset();
		}
		
		if(player.hasMoney(1))
			getBoard().getPot().addMoney(player.getMoney());
		Mineopoly.plugin.broadcast("&3" + player.getName() + " &bhas been removed from the game (&3" + reason + "&b)");
		board.removePlayer(player);
		if(board.getPlayers().size() == 1){
			end();
			Mineopoly.plugin.broadcast("&3" + board.getPlayers().get(0).getName() + " &bis the winner!");
		}
	}
}
