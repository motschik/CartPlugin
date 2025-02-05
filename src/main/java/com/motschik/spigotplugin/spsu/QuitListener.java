package com.motschik.spigotplugin.spsu;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.motschik.spigotplugin.CartPlugin;

public class QuitListener implements Listener {

  private final CartPlugin plg;
  private final TurnPoint tp;

  /**
   * コンストラクタ
   * 
   * @param plg_ プラグインメインクラスのインスタンス
   */
  public QuitListener(CartPlugin plg, TurnPoint tp) {
    this.plg = plg;
    this.tp = tp;
  }

  @EventHandler
  public void onMinecart(PlayerQuitEvent event) {

    Player player = event.getPlayer();
    if (player.getGameMode().equals(GameMode.SURVIVAL)) {
      return;
    }
    Location location = player.getCompassTarget();
    location = tp.getLocation(player);
    // location.setYaw(player.getLocation().getYaw());
    // location.setPitch(player.getLocation().getPitch());
    player.teleport(location);

    player.setGameMode(GameMode.SURVIVAL);
  }


}
