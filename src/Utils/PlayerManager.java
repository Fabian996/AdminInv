package de.Fabian996.Admin.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerManager {

	  public static Player getPlayer(String playerName)
	  {
	    Object[] playerObjects = Bukkit.getServer().getOnlinePlayers().toArray();

	    for (Object playerObject : playerObjects) {
	      Player player = (Player)playerObject;
	      if (player.getName().toLowerCase().equals(playerName.toLowerCase())) {
	        return player;
	      }
	    }

	    return null;
	  }

	  public static OfflinePlayer getOfflinePlayer(String playerName) {
	    OfflinePlayer[] offlinePlayers = Bukkit.getServer().getOfflinePlayers();

	    for (OfflinePlayer nowPlayer : offlinePlayers) {
	      if (nowPlayer.getName().toLowerCase().equals(playerName.toLowerCase())) {
	        return nowPlayer;
	      }
	    }

	    return null;
	  }

	  public static UUID getUUID(String playerName) {
	    Player p = getPlayer(playerName);

	    if (p != null) {
	      return getPlayer(playerName).getUniqueId();
	    }

	    return null;
	  }
	}
