package com.motschik.spigotplugin.cart;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;
import com.motschik.spigotplugin.MotschikPlugin;

public class MinecartListener implements Listener {
  private final MotschikPlugin plg;

  public MinecartListener(MotschikPlugin plg) {
    this.plg = plg;
  }

  @EventHandler
  public void onMinecart(VehicleMoveEvent event) {
    if (event.getVehicle() instanceof RideableMinecart) {
      RideableMinecart minecart = (RideableMinecart) event.getVehicle();
      Location signLoc = findSign(minecart);

      if (signLoc != null) {
        BlockState sign = signLoc.getBlock().getState();
        String[] lines = ((Sign) sign).getSide(Side.FRONT).getLines();

        evaluateLines(minecart, lines);
        
        lines = ((Sign) sign).getSide(Side.BACK).getLines();
        
        evaluateLines(minecart, lines);
      }

    }
  }

  private void evaluateLines(RideableMinecart minecart, String[] lines) {
    if (lines[0].equals("[speedlimit]")
        || lines[0].equals("[speed limit]")
            || lines[0].equals("[sl]")) {
      double speed = Double.parseDouble(lines[1]);
      minecart.setMaxSpeed(speed);
    }

    if (lines[0].equals("[fly]")) {
      minecart.setGravity(false);
      minecart.setFlyingVelocityMod(new Vector(1, 1, 1));
    }

    if (lines[0].equals("[!fly]")) {
      minecart.setGravity(true);
      minecart.setFlyingVelocityMod(new Vector(0.95, 0.95, 0.95));
    }
  }

  public double getSpeed(Minecart minecart) {
    double x = Math.abs(minecart.getVelocity().getX());
    double y = Math.abs(minecart.getVelocity().getZ());
    // plg.getLogger().info("x:"+x + " y:"+y);
    double max = x > y ? x : y;
    double r = Math.sqrt(x * x + y * y);
    double speed;
    if (x <= 0.001 || y <= 0.001) {
      speed = max;
    } else {
      speed = r;
    }
    speed = speed * 100.0;
    String maxString = "";
    if (speed >= 100 * minecart.getMaxSpeed()) {
      maxString = "  <keeping max speed>";
    }
    return speed;
  }

  public Location findSign(Minecart minecart) {
    List<Vector> vecs = new ArrayList<Vector>();
    vecs.add(new Vector(-1, 0, -1));
    vecs.add(new Vector(-1, 0, 0));
    vecs.add(new Vector(-1, 0, 1));
    vecs.add(new Vector(0, 0, -1));
    vecs.add(new Vector(0, 0, 1));
    vecs.add(new Vector(1, 0, -1));
    vecs.add(new Vector(1, 0, 0));
    vecs.add(new Vector(1, 0, 1));

    for (Vector vec : vecs) {
      Location signLoc = minecart.getLocation().add(vec);
      if (signLoc.getBlock().getState() instanceof Sign) {
        return signLoc;
      }
    }
    return null;
  }

}
