package com.motschik.spigotplugin.spsu;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.motschik.spigotplugin.CartPlugin;

public class Spectator implements CommandExecutor {
  private final CartPlugin plg;
  private final TurnPoint tp;

  /**
   * コンストラクタ
   * 
   * @param plg_ プラグインメインクラスのインスタンス
   */
  public Spectator(CartPlugin plg, TurnPoint tp) {
    this.plg = plg;
    this.tp = tp;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String arg2, String[] arg3) {

    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (player.getGameMode().equals(GameMode.SPECTATOR)) {
        return false;
      }
      Location location = player.getLocation();

      player.setCompassTarget(location);
      tp.setLocation(player, location);
      player.setGameMode(GameMode.SPECTATOR);
      return true;
    }

    return false;
  }

}
