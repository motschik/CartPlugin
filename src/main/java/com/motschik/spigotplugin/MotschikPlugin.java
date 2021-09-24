package com.motschik.spigotplugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.motschik.spigotplugin.cart.MinecartListener;
import com.motschik.spigotplugin.sleep.SleepListener;
import com.motschik.spigotplugin.spsu.QuitListener;
import com.motschik.spigotplugin.spsu.Spectator;
import com.motschik.spigotplugin.spsu.Survival;
import com.motschik.spigotplugin.spsu.TurnPoint;
import com.motschik.spigotplugin.station.MessageCommand;
import com.motschik.spigotplugin.station.MinecartArriveListener;
import com.motschik.spigotplugin.station.StationMessage;

public class MotschikPlugin extends JavaPlugin{

	TurnPoint tp;

	StationMessage sm;

	@Override
	public void onEnable() {

		tp = new TurnPoint();
		sm = new StationMessage(this);

		FileConfiguration config = getConfig();
		getLogger().info("config sta msg: " + config.getString("stationMessage.efw"));
		sm.loadMessages();

		getLogger().info("MotschikPlugin Running");
		getServer().getPluginManager().registerEvents(new MinecartListener(this), this);
		getServer().getPluginManager().registerEvents(new MinecartArriveListener(this, sm), this);
		getServer().getPluginManager().registerEvents(new SleepListener(this), this);
		getServer().getPluginManager().registerEvents(new QuitListener(this, tp), this);
		getCommand("sp").setExecutor(new Spectator(this, tp));
		getCommand("su").setExecutor(new Survival(this, tp));
		getCommand("message").setExecutor(new MessageCommand(this, sm));
	}
}
