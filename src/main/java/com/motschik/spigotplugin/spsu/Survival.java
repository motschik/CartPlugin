package com.motschik.spigotplugin.spsu;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.motschik.spigotplugin.CartPlugin;

public class Survival implements CommandExecutor {
  private final CartPlugin plg;
  private final TurnPoint tp;

  /**
   * コンストラクタ
   * 
   * @param plg_ プラグインメインクラスのインスタンス
   */
  public Survival(CartPlugin plg, TurnPoint tp) {
    this.plg = plg;
    this.tp = tp;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {

    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (player.getGameMode().equals(GameMode.SURVIVAL)) {
        return false;
      }
      Location location = player.getCompassTarget();
      location = tp.getLocation(player);
      // location.setYaw(player.getLocation().getYaw());
      // location.setPitch(player.getLocation().getPitch());
      player.teleport(location);

      player.setGameMode(GameMode.SURVIVAL);
      return true;

    }

    return false;
  }
}
