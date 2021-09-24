package com.motschik.spigotplugin.station;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.util.Vector;

import com.motschik.spigotplugin.MotschikPlugin;

public class MinecartArriveListener implements Listener {
	private final MotschikPlugin plg;
	private final StationMessage sm;

	public MinecartArriveListener(MotschikPlugin plg, StationMessage sm) {
		this.plg = plg;
		this.sm = sm;
	}

	@EventHandler
	public void onArrive(VehicleBlockCollisionEvent event) {
		if(event.getVehicle() instanceof RideableMinecart) {
			RideableMinecart minecart = (RideableMinecart)event.getVehicle();
			//plg.getLogger().info("butukatta!!!");

			Location signLoc = findSign(minecart);

			try {
				if(signLoc != null) {
					//plg.getLogger().info("arrive station");
					BlockState sign = signLoc.getBlock().getState();
					String[] lines = ((Sign)sign).getLines();
					if(minecart.getPassengers().get(0) instanceof Player) {
						Player player = (Player)(minecart.getPassengers().get(0));

						String title;
						String subTitle;

						if(lines[0].startsWith("/")) {
							title = sm.getMessage(lines[0].substring(1));
						}else {
							title = lines[0];
						}
						if(lines[1].startsWith("/")) {
							subTitle = sm.getMessage(lines[1].substring(1));
						}else {
							subTitle = lines[1];
						}

						player.sendTitle(title, subTitle, 5, 70, 10);
						if(lines[2] != null && !lines[2].equals("")) {
							String message1;
							if(lines[2].startsWith("/")) {
								message1 = sm.getMessage(lines[2].substring(1));
							}else {
								message1 = lines[2];
							}
							player.sendMessage(message1);
						}
						if(lines[3] != null && !lines[3].equals("")) {
							String message2;
							if(lines[3].startsWith("/")) {
								message2 = sm.getMessage(lines[3].substring(1));
							}else {
								message2 = lines[3];
							}
							player.sendMessage(message2);
						}
					}

				}
			}catch(Exception e){
				//plg.getLogger().info("pause error");
			}

		}
	}

	public Location findSign(Minecart minecart) {
		List<Vector> vecs = new ArrayList<Vector>();
		vecs.add(new Vector(0,2,0));
		vecs.add(new Vector(0,-2,0));

		for(Vector vec: vecs) {
			Location signLoc = minecart.getLocation().add(vec);
			if(signCheck(signLoc.getBlock().getType())) {
				return signLoc;
			}
		}
		return null;
	}

	public boolean signCheck(Material type) {

		if(type == Material.ACACIA_WALL_SIGN) {
			return true;
		}else if(type == Material.BIRCH_WALL_SIGN) {
			return true;
		}else if(type == Material.CRIMSON_WALL_SIGN) {
			return true;
		}else if(type == Material.DARK_OAK_WALL_SIGN) {
			return true;
		}else if(type == Material.JUNGLE_WALL_SIGN) {
			return true;
		}else if(type == Material.OAK_WALL_SIGN) {
			return true;
		}else if(type == Material.SPRUCE_WALL_SIGN) {
			return true;
		}else if(type == Material.WARPED_WALL_SIGN) {
			return true;
		}
		return false;
	}

}
