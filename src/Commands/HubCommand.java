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

public class HubCommand implements CommandExecutor {

	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";

	  AdminMenu plugin;
	  
	  public HubCommand(AdminMenu instance)
	  {
	    this.plugin = instance;
	  }

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("sethub")){
			if(!(cs instanceof Player)){
				cs.sendMessage(Prefix + " §4This command can now Player use");
			}else{
				Player p = (Player)cs;
				if((p.hasPermission("admininv.sethub")) && (this.plugin.getConfig().getString("HubEnabled").equalsIgnoreCase("True"))){
					this.plugin.getConfig().set("Hub.world", p.getLocation().getWorld().getName());
					this.plugin.getConfig().set("Hub.x", Double.valueOf(p.getLocation().getX()));
					this.plugin.getConfig().set("Hub.y", Double.valueOf(p.getLocation().getY()));
					this.plugin.getConfig().set("Hub.z", Double.valueOf(p.getLocation().getZ()));
					this.plugin.getConfig().set("Hub.yaw", Double.valueOf(p.getLocation().getYaw()));
					this.plugin.getConfig().set("Hub.pitch", Double.valueOf(p.getLocation().getPitch()));
					this.plugin.saveConfig();
					p.sendMessage(Prefix + "§7The Hub has been set!");
				}else{
					p.sendMessage(Prefix + "§4You do not have Permission to do that");
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("hub")){
			if (!(cs instanceof Player)) {
				cs.sendMessage("Only players can do this!");
			}else {
				Player p = (Player)cs;
				if ((p.hasPermission("admininv.hub")) && (this.plugin.getConfig().getString("HubEnabled").equalsIgnoreCase("True"))) {
					World w = Bukkit.getServer().getWorld(this.plugin.getConfig().getString("Hub.world"));
		            double x = this.plugin.getConfig().getDouble("Hub.x");
		            double y = this.plugin.getConfig().getDouble("Hub.y");
		            double z = this.plugin.getConfig().getDouble("Hub.z");
		            int ya = this.plugin.getConfig().getInt("Hub.yaw");
		            int pi = this.plugin.getConfig().getInt("Hub.pitch");
		            p.teleport(new Location(w, x, y, z, ya, pi));
		            p.sendMessage(Prefix + "§3You have been warped to the Hub!");
				}
			}
		}
		return false;
	}

}
