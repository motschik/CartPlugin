package com.motschik.spigotplugin.station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.motschik.spigotplugin.CartPlugin;

public class StationMessage {
  private Map<String, String> messages;
  private final CartPlugin plg;

  public StationMessage(CartPlugin plg) {
    messages = new HashMap<String, String>();
    this.plg = plg;
  }

  public boolean addMessage(String key, String message) {
    if (messages.containsKey(key)) {
      return false;
    } else {
      messages.put(key, message);
      saveMessages();
      return true;
    }
  }

  public boolean updateMessage(String key, String message) {
    if (messages.containsKey(key)) {
      messages.replace(key, message);
      saveMessages();
      return true;
    } else {
      return false;
    }
  }

  public boolean deleteMessage(String key) {
    if (messages.containsKey(key)) {
      messages.remove(key);
      saveMessages();
      return true;
    } else {
      return false;
    }
  }

  public String getMessage(String key) {
    if (messages.containsKey(key)) {
      return messages.get(key);
    } else {
      return null;
    }
  }

  public Map<String, String> getAllMessages() {
    return messages;
  }

  public boolean saveMessages() {
    ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
    list.add(messages);
    // plg.getConfig().set("stationMessage", list);
    plg.saveConfig();
    return true;
  }

  public void loadMessages() {
    try {
      messages = (Map<String, String>) plg.getConfig().getMapList("stationMessage").get(0);
    } catch (Exception e) {
      // plg.getLogger().info(e.getMessage());
    }
  }
}
