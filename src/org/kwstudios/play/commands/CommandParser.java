package org.kwstudios.play.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandParser {
	
	private Player player;
	private Command command;
	private String label;
	private String[] args;
	private FileConfiguration fileConfiguration;
	
	private boolean isCommand = false;
	
	public CommandParser(Player player, Command command, String label, String[] args, FileConfiguration fileConfiguration){
		this.player = player;
		this.command = command;
		this.label = label;
		this.args = args;
		this.fileConfiguration = fileConfiguration;
		checkCommand();
	}
	
	private void checkCommand(){
		switch (label.toLowerCase()) {
		case "setlobby":
			LobbySetter lobbySetter = new LobbySetter(player, command, args, fileConfiguration);
			lobbySetter.setLobby();
			player.sendMessage("The Lobby was set " + ChatColor.DARK_AQUA + "successfully!");
			isCommand = true;
			break;
			
		case "lobby":
			onLobbyCommandIssued();
			break;
			
		case "l":
			onLobbyCommandIssued();
			break;
			
		case "spawn":
			onLobbyCommandIssued();
			break;
			
		case "kwlist":
			new PlayerList(player);
			break;

		default:
			isCommand = false;
			break;
		}
	}
	
	private void onLobbyCommandIssued(){
		LobbyTeleporter lobbyTeleporter = new LobbyTeleporter(player, fileConfiguration);
		if(!lobbyTeleporter.isLobbySet()){
			player.sendMessage(ChatColor.BLUE + "The lobby was not set yet! Set the lobby with " + ChatColor.DARK_RED + "/setlobby");
		}else{
			lobbyTeleporter.teleportToLobby();
			player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "-------Welcome to the Lobby-------");
		}
	}

	public boolean isCommand() {
		return isCommand;
	}

}
