package com.motschik.spigotplugin.spsu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TurnPoint {
	private final Map<UUID, Location> data;

	public TurnPoint() {
		this.data = new HashMap<UUID, Location>();
	}

	public void setLocation(Player player, Location location) {
		UUID playerId = player.getUniqueId();
		if(data.containsKey(playerId)) {
			data.replace(playerId, location);
		}else {
			data.put(playerId, location);
		}
	}

	public Location getLocation(Player player) {
		return data.get(player.getUniqueId());
	}
}
