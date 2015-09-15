package org.kwstudios.play.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerList {
	
	public PlayerList(Player player){
		OfflinePlayer[] offlinePlayer = Bukkit.getOfflinePlayers();
		for(int i = 0; i < offlinePlayer.length; i++){
			String message = Integer.toString(i) + ": " + offlinePlayer[i].getName();
			player.sendMessage(message);
		}
		player.sendMessage("Total: " + Integer.toString(offlinePlayer.length));
	}

}
