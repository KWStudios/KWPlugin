package org.kwstudios.play.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.kwstudios.play.toolbox.ConfigFactory;

public class LobbyTeleporter {

	private static final String LOBBY_PATH = "settings.lobby";

	private Player player;
	private FileConfiguration fileConfiguration;
	private String world;
	private int lobbyX;
	private int lobbyY;
	private int lobbyZ;
	private boolean isLobbySet = false;

	public LobbyTeleporter(Player player, FileConfiguration fileConfiguration) {
		this.player = player;
		this.fileConfiguration = fileConfiguration;
		getLobbyLocation();
	}

	public boolean isLobbySet() {
		return isLobbySet;
	}
	
	public void teleportToLobby(){
		Location location = new Location(Bukkit.getWorld(world), lobbyX, lobbyY, lobbyZ);
		player.teleport(location);
	}
	
	private void getLobbyLocation() {

		if (fileConfiguration.isSet(LOBBY_PATH + ".x") && fileConfiguration.isSet(LOBBY_PATH + ".y")
				&& fileConfiguration.isSet(LOBBY_PATH + ".z") && fileConfiguration.isSet(LOBBY_PATH + ".world")) {
			world = ConfigFactory.getString(LOBBY_PATH, "world", fileConfiguration);
			lobbyX = ConfigFactory.getInt(LOBBY_PATH, "x", fileConfiguration);
			lobbyY = ConfigFactory.getInt(LOBBY_PATH, "y", fileConfiguration);
			lobbyZ = ConfigFactory.getInt(LOBBY_PATH, "z", fileConfiguration);
			isLobbySet = true;
		}else{
			isLobbySet = false;
		}

	}

}
