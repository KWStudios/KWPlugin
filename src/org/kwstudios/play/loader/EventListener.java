package org.kwstudios.play.loader;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.kwstudios.play.toolbox.ConfigFactory;
import org.kwstudios.play.toolbox.ConstantHolder;
import org.kwstudios.play.toolbox.MotdListGetter;

public final class EventListener implements Listener {

	private static final String WORLD_COMMANDS_PATH = "settings.CommandsOnWorldChanged";
	private static final String COMMAND_STRING = "command";

	private FileConfiguration fileConfiguration;
	private boolean isConfiguredForCommand = false;

	public EventListener(PluginLoader plugin, FileConfiguration fileConfiguration) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.fileConfiguration = fileConfiguration;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onWorldChange(PlayerChangedWorldEvent event) {
		String originWorld = event.getFrom().getName();
		isConfiguredForCommand = false;
		boolean isPathSet = false;
		try {
			isPathSet = fileConfiguration.getConfigurationSection(WORLD_COMMANDS_PATH).isSet(originWorld);
		} catch (Exception e) {
			System.out.println("The " + originWorld + " key was not set yet in the configs! Skipping that...");
			return;
		}

		if (isPathSet) {
			// Set<String> worldList =
			// ConfigFactory.getKeysUnderPath(WORLD_COMMANDS_PATH, false,
			// fileConfiguration);
			boolean isCommandSet = false;
			try {
				isCommandSet = fileConfiguration.getConfigurationSection(WORLD_COMMANDS_PATH + "." + originWorld)
						.isSet(COMMAND_STRING);
			} catch (Exception e) {
				System.out.println("The command key inside the " + originWorld
						+ " key was not set yet in the configs! Skipping that...");
				return;
			}
			if (isCommandSet) {
				isConfiguredForCommand = true;
			}

			// for (String world : worldList) {
			// if (world.equalsIgnoreCase(originWorld)) {
			// isConfiguredForCommand = true;
			// break;
			// }
			// }
		}

		if (isConfiguredForCommand) {
			String command = ConfigFactory.getString(WORLD_COMMANDS_PATH + "." + originWorld, COMMAND_STRING,
					fileConfiguration);
			event.getPlayer().performCommand(command);
		}

	}

	@EventHandler
	public void onPingEvent(ServerListPingEvent event) {
		Random random = new Random();
		try {
			if (MotdListGetter.getMotdList() != null) {
				int number = random.nextInt(MotdListGetter.getMotdList().size() - 1);
				String motd = MotdListGetter.getMotdList().get(number);
				if (motd != "") {
					event.setMotd(ConstantHolder.MOTD_PREFIX + motd);
				}
			}else{
				
			}
		} catch (Exception e) {
			
		}
	}

}
