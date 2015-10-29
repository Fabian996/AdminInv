package de.Fabian996.Admin.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class cache {

	  public static File messages = new File("plugins/AdminInv", "messages.yml");
	  public static FileConfiguration msgcfg = YamlConfiguration.loadConfiguration(messages);

	  public static String noPerm = msgcfg.getString("messages.noPerm");
	  public static String bePlayer = msgcfg.getString("messages.bePlayer");
	  public static String noWarp = msgcfg.getString("messages.noWarp");
	  public static String noWorld = msgcfg.getString("messages.noWorld");

	  public static void loadMsgCfg()
	  {
	    msgcfg.set("messages.noPerm", "§cNo Permissions!");
	    msgcfg.addDefault("messages.noPerm", "§cNo Permissions!");

	    msgcfg.set("messages.bePlayer", "§cYou have to be a Player!");
	    msgcfg.addDefault("messages.bePlayer", "§cYou have to be a Player!");

	    msgcfg.set("messages.noWarp", "§cWarp not founded!");
	    msgcfg.addDefault("messages.noWarp", "§cWarp not founded!");

	    msgcfg.set("messages.noWorld", "§cWorld not founded!");
	    msgcfg.addDefault("messages.noWorld", "§cWorld not founded!");
	    try
	    {
	      msgcfg.save(messages);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	}
