package org.kwstudios.play.commands;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.kwstudios.play.toolbox.ConfigFactory;

public class LobbySetter {
	
	private Player player;
	@SuppressWarnings("unused")
	private Command command;
	@SuppressWarnings("unused")
	private String[] args;
	private FileConfiguration fileConfiguration;
	
	public LobbySetter(Player player, Command command, String[] args, FileConfiguration fileConfiguration){
		this.player = player;
		this.command = command;
		this.args = args;
		this.fileConfiguration = fileConfiguration;
	}
	
	public void setLobby(){
		ConfigFactory.setString("settings.lobby", "world", player.getWorld().getName(), fileConfiguration);
		ConfigFactory.setInt("settings.lobby", "x", player.getLocation().getBlockX(), fileConfiguration);
		ConfigFactory.setInt("settings.lobby", "y", player.getLocation().getBlockY(), fileConfiguration);
		ConfigFactory.setInt("settings.lobby", "z", player.getLocation().getBlockZ(), fileConfiguration);
	}

}
