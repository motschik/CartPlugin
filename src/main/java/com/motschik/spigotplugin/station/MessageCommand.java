package com.motschik.spigotplugin.station;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.motschik.spigotplugin.MotschikPlugin;

public class MessageCommand implements CommandExecutor {

	private final MotschikPlugin plg;
	private final StationMessage sm;



	public MessageCommand(MotschikPlugin plg, StationMessage sm) {
		this.plg = plg;
		this.sm = sm;
	}



	@Override
	public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
		try {
			if("add".equals(args[0])) {
				sm.addMessage(args[1], args[2]);
				return true;

			}else if("addjson".equals(args[0])) {
				sm.addMessage(args[1], args[2]);
				return true;

			}else if("update".equals(args[0])) {
				sm.updateMessage(args[1], args[2]);
				return true;
			}else if("updatejson".equals(args[0])) {
				sm.updateMessage(args[1], args[2]);
				return true;
			}else if("delete".equals(args[0])) {
				sm.deleteMessage(args[1]);
				return true;
			}else if("get".equals(args[0])) {
				String msg = sm.getMessage(args[1]);
				sender.sendMessage("message:" + msg);
				return true;
			}else if("list".equals(args[0])) {
				Map<String, String> map = sm.getAllMessages();
				for(Map.Entry<String, String> entry : map.entrySet()) {
					sender.sendMessage("key:" + entry.getKey() + "  message:" + entry.getValue());
				}
				return true;
			}else if("save".equals(args[0])) {
				sm.saveMessages();
				return true;
			}

		}catch(Exception e) {

		}

		return false;
	}

}
