package com.kill3rtaco.mineopoly.cmds.mineopoly;

import java.io.File;

import org.bukkit.entity.Player;

import com.kill3rtaco.mineopoly.Mineopoly;
import com.kill3rtaco.mineopoly.MineopolyPermissions;
import com.kill3rtaco.mineopoly.saves.MineopolySaveGame;
import com.kill3rtaco.tacoapi.api.TacoCommand;

public class MineopolySaveCommand extends TacoCommand {

	public MineopolySaveCommand() {
		super("save", new String[]{}, "<name>", "Save the current game", MineopolyPermissions.SAVE_GAME);
	}

	@Override
	public void onPlayerCommand(Player player, String[] args) {
		if(args.length == 0){
			Mineopoly.plugin.chat.sendPlayerMessage(player, "&cSpecify a name for the save");
			return;
		}else{
			String name = args[0];
			Mineopoly.plugin.chat.sendPlayerMessage(player, "&aSaving game as &e" + name + "&a...");
			save(name);			
			Mineopoly.plugin.chat.sendPlayerMessage(player, "&aDone!");
		}
	}

	@Override
	public boolean onConsoleCommand(String[] args) {
		if(args.length == 0){
			Mineopoly.plugin.chat.out("Specify a name for the save");
			return true;
		}else{
			String name = args[0];
			Mineopoly.plugin.chat.out("Saving game as '" + name + "'...");
			save(name);			
			Mineopoly.plugin.chat.out("Done!");
		}
		return true;
	}
	
	private void save(String name){
		File file = new File(Mineopoly.plugin.getDataFolder() + "/saves/" + name + ".yml");
		MineopolySaveGame save = new MineopolySaveGame(file);
		save.setData(Mineopoly.plugin.getGame());
	}

}
