package com.motschik.spigotplugin.sleep;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import com.motschik.spigotplugin.CartPlugin;

public class SleepListener implements Listener {
  private final CartPlugin plg;

  public SleepListener(CartPlugin plg) {
    this.plg = plg;
  }

  @EventHandler
  public void onSleep(PlayerBedEnterEvent sleep) {
    if (sleep.getBedEnterResult() == BedEnterResult.OK) {
      sleep.getPlayer().chat("zzz");
    }
  }
}
