package de.Fabian996.Admin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Fabian996.Admin.main.AdminMenu;

public class LobbyCommand implements CommandExecutor {
	
	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
	
	AdminMenu plugin;

	public LobbyCommand(AdminMenu instance){
		this.plugin = instance;
	}
	
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("setlobby")){
			if (!(cs instanceof Player)){
				cs.sendMessage(Prefix + " §4This command can now Player use");
		      }else {
		    	  Player p = (Player)cs;
		    	  if ((p.hasPermission("admininv.setlobby")) && (this.plugin.getConfig().getString("LobbyEnabled").equalsIgnoreCase("True"))){
		    		  this.plugin.getConfig().set("Lobby.world", p.getLocation().getWorld().getName());
		    		  this.plugin.getConfig().set("Lobby.x", Double.valueOf(p.getLocation().getX()));
		    		  this.plugin.getConfig().set("Lobby.y", Double.valueOf(p.getLocation().getY()));
		    		  this.plugin.getConfig().set("Lobby.z", Double.valueOf(p.getLocation().getZ()));
		    		  this.plugin.getConfig().set("Lobby.yaw", Double.valueOf(p.getLocation().getYaw()));
		    		  this.plugin.getConfig().set("Lobby.pitch", Double.valueOf(p.getLocation().getPitch()));
		    		  this.plugin.saveConfig();
		    		  p.sendMessage(Prefix + "§7The Lobby has been set!");
		        }
		        else {
		          cs.sendMessage(Prefix + "§4You do not have permission to do that!");
		        }
		      }

		    }
		    else if (cmd.getName().equalsIgnoreCase("lobby"))
		    {
		      if (!(cs instanceof Player)) {
		    	  cs.sendMessage(Prefix + "§4This command can now Player use");
		      }
		      else {
		        Player p = (Player)cs;
		        if ((p.hasPermission("spawnjoin.lobby")) && (this.plugin.getConfig().getString("LobbyEnabled").equalsIgnoreCase("True"))) {
		          World w = Bukkit.getServer().getWorld(this.plugin.getConfig().getString("Lobby.world"));
		          double x = this.plugin.getConfig().getDouble("Lobby.x");
		          double y = this.plugin.getConfig().getDouble("Lobby.y");
		          double z = this.plugin.getConfig().getDouble("Lobby.z");
		          int ya = this.plugin.getConfig().getInt("Lobby.yaw");
		          int pi = this.plugin.getConfig().getInt("Lobby.pitch");
		          p.teleport(new Location(w, x, y, z, ya, pi));
		          p.sendMessage(Prefix + "§3You have been warped to the Lobby!");
		        }
		      }
		    }
		    return false;
		  }
		}
