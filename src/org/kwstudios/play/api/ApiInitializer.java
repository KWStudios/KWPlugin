package org.kwstudios.play.api;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.kwstudios.play.loader.PluginLoader;
import org.kwstudios.play.toolbox.ConstantHolder;

import com.google.gson.Gson;

public class ApiInitializer {

	public ApiInitializer() {
		init();
	}

	private void init() {
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			PlayerJSON playerJSON = new PlayerJSON(player.getName(), player.getUniqueId().toString(),
					player.getFirstPlayed(), player.getLastPlayed(), player.isOnline(), player.isBanned());
			Gson gson = new Gson();
			String jsonParam = gson.toJson(playerJSON);
			String url = ConstantHolder.API_URL.replace(":server", "play.kwstudios.org").replace(":player",
					player.getName());
			HashMap<String, String> parameters = new HashMap<String, String>();
			parameters.put("data", jsonParam);
			Requester requester = new Requester(url, PluginLoader.getHeaders(), parameters);
			if (requester.getReader() != null) {
				System.out.println(requester.getReader());
			}

		}
	}

	public class PlayerJSON {
		private String name;
		private String uuid;
		private long first_played;
		private long last_played;
		private boolean is_online;
		private boolean is_banned;

		public PlayerJSON(String name, String uuid, long first_played, long last_played, boolean is_online,
				boolean is_banned) {
			super();
			this.name = name;
			this.uuid = uuid;
			this.first_played = first_played;
			this.last_played = last_played;
			this.is_online = is_online;
			this.is_banned = is_banned;
		}

		public String getName() {
			return name;
		}

		public String getUuid() {
			return uuid;
		}

		public long getFirst_played() {
			return first_played;
		}

		public long getLast_played() {
			return last_played;
		}

		public boolean isIs_online() {
			return is_online;
		}

		public boolean isIs_banned() {
			return is_banned;
		}

	}

}
