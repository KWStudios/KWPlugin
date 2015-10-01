package org.kwstudios.play.api;

import java.io.IOException;
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

			String s = null;
			try {
				while ((s = requester.getReader().readLine()) != null) {
					System.out.println(s);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
