package com.motschik.spigotplugin.cart;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.util.Vector;

import com.motschik.spigotplugin.MotschikPlugin;

public class MinecartListener implements Listener  {
	private final MotschikPlugin plg;

	public MinecartListener(MotschikPlugin plg) {
		this.plg = plg;
	}

//	@EventHandler
//	public void moveMinecart(VehicleMoveEvent event) {
//		// 乗り物の種類をログへ
//		if(event.getVehicle() instanceof RideableMinecart) {
//			RideableMinecart minecart = (RideableMinecart)event.getVehicle();
//			//plg.getLogger().info("minecart speed:"+minecart.getVelocity());
//			try {
//				if(minecart.getPassengers().get(0) instanceof Player) {
//					Player player = (Player)(minecart.getPassengers().get(0));
//					if(!player.isOp())return;
//					double x = Math.abs(minecart.getVelocity().getX());
//					double y = Math.abs(minecart.getVelocity().getZ());
//					//plg.getLogger().info("x:"+x + " y:"+y);
//					double max = x > y ? x : y;
//					double r = Math.sqrt(x*x+y*y);
//					double speed;
//					if(x <= 0.001 || y <= 0.001) {
//						speed = max;
//					}else {
//						speed = r;
//					}
//					speed = speed * 100.0;
//					String maxString = "";
//					if(speed >= 100 * minecart.getMaxSpeed()) {
//						maxString = "  <keeping max speed>";
//					}
//					//player.sendMessage("minecart velocity:r = " + String.format("%.0f", speed) + maxString);
//				}
//			}catch(Exception e){
//
//			}
//		}
//	}

	@EventHandler
	public void onMinecart(VehicleMoveEvent event) {
		if(event.getVehicle() instanceof RideableMinecart) {
			RideableMinecart minecart = (RideableMinecart)event.getVehicle();
			Location signLoc = findSign(minecart);

			try {
				if(signLoc != null) {
					//plg.getLogger().info("found sign!!!");
					BlockState sign = signLoc.getBlock().getState();
					String[] lines = ((Sign)sign).getLines();

					//plg.getLogger().info("lines[0]:"+lines[0]);
					//plg.getLogger().info("lines[1]:"+lines[1]);
					if(lines[0].equals("[speedlimit]") || lines[0].equals("[speed limit]")) {
						double speed = Double.parseDouble(lines[1]);
						minecart.setMaxSpeed(speed);

					}

					if(lines[0].equals("[fly]")) {
						minecart.setGravity(false);
						minecart.setFlyingVelocityMod(new Vector(1,1,1));
					}

					if(lines[0].equals("[!fly]")) {
						minecart.setGravity(true);
						minecart.setFlyingVelocityMod(new Vector(0.95,0.95,0.95));
					}
				}
			}catch(Exception e){
				//plg.getLogger().info("pause error");
			}

//			if(minecart.getPassengers().get(0) instanceof Player) {
//				Player player = (Player)(minecart.getPassengers().get(0));
//				if(player.isOp()) {
//					//player.sendTitle(String.format("%.0f", getSpeed(minecart)) + " km/h", "制限速度:" + String.format("%.0f", minecart.getMaxSpeed()*100.0)+" km/h", 0, 70, 10);
//				}
//			}

		}
	}

	public double getSpeed(Minecart minecart) {
		double x = Math.abs(minecart.getVelocity().getX());
		double y = Math.abs(minecart.getVelocity().getZ());
		//plg.getLogger().info("x:"+x + " y:"+y);
		double max = x > y ? x : y;
		double r = Math.sqrt(x*x+y*y);
		double speed;
		if(x <= 0.001 || y <= 0.001) {
			speed = max;
		}else {
			speed = r;
		}
		speed = speed * 100.0;
		String maxString = "";
		if(speed >= 100 * minecart.getMaxSpeed()) {
			maxString = "  <keeping max speed>";
		}
		return speed;
	}

	public Location findSign(Minecart minecart) {
		List<Vector> vecs = new ArrayList<Vector>();
		vecs.add(new Vector(-1,0,-1));
		vecs.add(new Vector(-1,0,0));
		vecs.add(new Vector(-1,0,1));
		vecs.add(new Vector(0,0,-1));
		vecs.add(new Vector(0,0,1));
		vecs.add(new Vector(1,0,-1));
		vecs.add(new Vector(1,0,0));
		vecs.add(new Vector(1,0,1));

		for(Vector vec: vecs) {
			Location signLoc = minecart.getLocation().add(vec);
			if(signCheck(signLoc.getBlock().getType())) {
				return signLoc;
			}
		}
		return null;
	}

	public boolean signCheck(Material type) {

		if(type == Material.ACACIA_SIGN) {
			return true;
		}else if(type == Material.BIRCH_SIGN) {
			return true;
		}else if(type == Material.CRIMSON_SIGN) {
			return true;
		}else if(type == Material.DARK_OAK_SIGN) {
			return true;
		}else if(type == Material.JUNGLE_SIGN) {
			return true;
		}else if(type == Material.OAK_SIGN) {
			return true;
		}else if(type == Material.SPRUCE_SIGN) {
			return true;
		}else if(type == Material.WARPED_SIGN) {
			return true;
		}
		return false;
	}

}
