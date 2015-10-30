package de.Fabian996.Admin.Commands;

import de.Fabian996.Admin.main.AdminMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class WarpCommand implements CommandExecutor{
  
	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
  
  AdminMenu plugin;

  public WarpCommand(AdminMenu instance) {
	this.plugin = instance;
}

  @SuppressWarnings("deprecation")
  public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args){
	  if (cmd.getName().equalsIgnoreCase("setwarp")) {
		  if (!(cs instanceof Player)) {
			  if (this.plugin.getConfig().getString("WarpEnabled").equalsIgnoreCase("True"))
				  cs.sendMessage(Prefix + " §4This command can now Player use");
		  } else {
			  Player p = (Player)cs;
			  if (this.plugin.getConfig().getString("WarpEnabled").equalsIgnoreCase("True")) {
				  if (p.hasPermission("admininv.setwarp")) {
					  if (args.length < 1) {
						  if (this.plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
							  cs.sendMessage(Prefix + " §4Too few arguments!");
							  cs.sendMessage(Prefix + " §fUse §3/setwarp §2[WarpName]");
						  }
					  } else if (args.length == 1) {
						  String warp = args[0];
						  this.plugin.getConfig().set("WARPS." + warp + ".world", p.getLocation().getWorld().getName());
						  this.plugin.getConfig().set("WARPS." + warp + ".x", Double.valueOf(p.getLocation().getX()));
						  this.plugin.getConfig().set("WARPS." + warp + ".y", Double.valueOf(p.getLocation().getY()));
						  this.plugin.getConfig().set("WARPS." + warp + ".z", Double.valueOf(p.getLocation().getZ()));
						  this.plugin.getConfig().set("WARPS." + warp + ".yaw", Double.valueOf(p.getLocation().getYaw()));
						  this.plugin.getConfig().set("WARPS." + warp + ".pitch", Double.valueOf(p.getLocation().getPitch()));
						  this.plugin.saveConfig();
						  p.sendMessage(Prefix + " §6Warp saved!");
						  p.sendMessage(Prefix + " §fUse §3/warp §6" + warp + " §fto go there!");
					  } else if ((args.length > 1) && (this.plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True"))) {
						  cs.sendMessage(Prefix + " §4Too few arguments!");
						  cs.sendMessage(Prefix + " §fUse §3/setwarp §2[WarpName]");
					  }
				  } else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True"))
					  cs.sendMessage(Prefix + " §4You do not have permission to do that!");
			  }
		  }
	  }else if (cmd.getName().equalsIgnoreCase("warp")) {
		  if (this.plugin.getConfig().getString("WarpEnabled").equalsIgnoreCase("True")) {
			  if (args.length < 0) {
				  if ((cs instanceof Player)) {
					  Player p = (Player)cs;
					  if (p.hasPermission("admininv.use.warp")) {
						  if (this.plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
							  cs.sendMessage(Prefix + " §4Too few arguments!");
							  p.sendMessage(Prefix + " §cPlease use: §3/warp §2[WarpName]");
						  }
					  } else if (p.hasPermission("admininv.use.warpothers")) {
            	 if (this.plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
            		 cs.sendMessage(Prefix + " §4Too few arguments!");
            		 p.sendMessage(Prefix + " §cPlease use: §3/warp §2[WarpName] §6<Playername>");
            	 }
					  } else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True"))
						  p.sendMessage(Prefix + " §4You do not have permission to do that!");
				  }
				  else if (!(cs instanceof Player)) {
					  cs.sendMessage(Prefix + " §4Too few arguments!");
					  cs.sendMessage(Prefix + " §cPlease use: §3/warp §2[WarpName] §6<Playername>");
				  }
			  } else if (args.length == 1) {
				  if ((cs instanceof Player)) {
					  Player p = (Player)cs;
					  String warp = args[0];
					  if (p.hasPermission("admininv.use.warp")) {
						  if (!this.plugin.getConfig().contains("WARPS." + warp)) {
							  p.sendMessage(Prefix + " §4That warp does not exist!");
						  } else {
							  World w = Bukkit.getServer().getWorld(this.plugin.getConfig().getString("WARPS." + warp + ".world"));
							  double x = this.plugin.getConfig().getDouble("WARPS." + warp + ".x");
							  double y = this.plugin.getConfig().getDouble("WARPS." + warp + ".y");
							  double z = this.plugin.getConfig().getDouble("WARPS." + warp + ".z");
							  int ya = this.plugin.getConfig().getInt("WARPS." + warp + ".yaw");
							  int pi = this.plugin.getConfig().getInt("WARPS." + warp + ".pitch");
							  p.teleport(new Location(w, x, y, z, ya, pi));
							  if (this.plugin.getConfig().getString("NOTIFICATIONS.Warp").equalsIgnoreCase("True"))
								  p.sendMessage(Prefix + " §fYou have been warped to §8[§6" + warp + "§8] §f!");
						  }
					  }
					  else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True"))
						  p.sendMessage(Prefix + " §4You do not have permission to do that!");
				  }
				  else if (!(cs instanceof Player)) {
					  cs.sendMessage(Prefix + " §4Too few arguments!");
					  cs.sendMessage(Prefix + " §cPlease use: §3/warp §2[WarpName] §6<Playername>");
				  }
			  } else if (args.length == 2) {
				  if ((cs instanceof Player)) {
					  Player p = (Player)cs;
					  if (p.hasPermission("admininv.use.warpothers")) {
						  String warp = args[0];
						  Player target = Bukkit.getPlayer(args[1]);
						  if (target == null) {
							  p.sendMessage(Prefix + " §4That player is not online!");
						  } else if (!this.plugin.getConfig().contains("WARPS." + warp)) {
							  p.sendMessage(Prefix + " §4That warp does not exist!");
						  } else {
							  World w = Bukkit.getServer().getWorld(this.plugin.getConfig().getString("WARPS." + warp + ".world"));
							  double x = this.plugin.getConfig().getDouble("WARPS." + warp + ".x");
							  double y = this.plugin.getConfig().getDouble("WARPS." + warp + ".y");
							  double z = this.plugin.getConfig().getDouble("WARPS." + warp + ".z");
							  int ya = this.plugin.getConfig().getInt("WARPS." + warp + ".yaw");
							  int pi = this.plugin.getConfig().getInt("WARPS." + warp + ".pitch");
							  target.teleport(new Location(w, x, y, z, ya, pi));
							  p.sendMessage(Prefix + " §fYou have warped " + target + " §fto §8[§6" + warp + "§8]");
							  if (this.plugin.getConfig().getString("NOTIFICATIONS.Warp").equalsIgnoreCase("True"))
								  target.sendMessage(Prefix + " §fYou have been warped to §8[§6" + warp + "§8] §f!");
    	         }
					  }
					  else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
						  p.sendMessage(Prefix + " §4You do not have permission to do that!");
					  }
	          } else if (!(cs instanceof Player)) {
	            String warp = args[0];
	            Player target = Bukkit.getPlayer(args[1]);
	            if (target == null) {
	            	cs.sendMessage(Prefix + " §4That player is not online!");
	            } else if (!this.plugin.getConfig().contains("WARPS." + warp)) {
	            	cs.sendMessage(Prefix + " §4That warp does not exist!");
	            } else {
	            	World w = Bukkit.getServer().getWorld(this.plugin.getConfig().getString("WARPS." + warp + ".world"));
	            	double x = this.plugin.getConfig().getDouble("WARPS." + warp + ".x");
	            	double y = this.plugin.getConfig().getDouble("WARPS." + warp + ".y");
	            	double z = this.plugin.getConfig().getDouble("WARPS." + warp + ".z");
	            	int ya = this.plugin.getConfig().getInt("WARPS." + warp + ".yaw");
	            	int pi = this.plugin.getConfig().getInt("WARPS." + warp + ".pitch");
	            	target.teleport(new Location(w, x, y, z, ya, pi));
	            	cs.sendMessage(Prefix + " §fYou have warped §6" + target + " §fto §8[§6" + warp + "§8]");
	            	if (this.plugin.getConfig().getString("NOTIFICATIONS.Warp").equalsIgnoreCase("True"))
	            		target.sendMessage(Prefix + " §fYou have been warped to §6" + warp + "§f!");
	            }
	          }
			  }
		  }
	  }else if ((cmd.getName().equalsIgnoreCase("delwarp")) && (this.plugin.getConfig().getString("WarpEnabled").equalsIgnoreCase("True"))) {
		  if (args.length < 1) {
			  if ((cs instanceof Player)) {
				  Player p = (Player)cs;
				  if (p.hasPermission("admininv.delwarp")) {
					  if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
						  p.sendMessage(Prefix + " §4Too few arguments!");
						  p.sendMessage(Prefix + " §fPlease use: §3/delwarp §2[Warpname]");
					  }
				  } else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True"))
					  p.sendMessage(Prefix + "You do not have permission to do that!");
			  }else if (!(cs instanceof Player)) {
				  cs.sendMessage(Prefix + " §4Too few arguments!");
				  cs.sendMessage(Prefix + " §fPlease use: §3/delwarp §2[Warpname]");
			  }
		  } else if (args.length == 1) {
			  if ((cs instanceof Player)) {
				  Player p = (Player)cs;
				  if (p.hasPermission("admininv.delwarp")) {
					  String warp = args[0];
					  if (warp == null) {
						  p.sendMessage(Prefix + " §4That warp does not exist!");
					  } else {
						  this.plugin.getConfig().set("WARPS." + warp, null);
						  this.plugin.saveConfig();
						  p.sendMessage(Prefix + " §4Warp deleted!");
					  }
				  } else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True")) {
					  p.sendMessage(Prefix + "You do not have permission to do that!");
				  }
			  } else if (!(cs instanceof Player)) {
				  String warp = args[0];
				  if (warp == null) {
					  cs.sendMessage(Prefix + " §4That warp does not exist!");
				  } else {
					  this.plugin.getConfig().set("WARPS." + warp, null);
					  this.plugin.saveConfig();
					  cs.sendMessage(Prefix + " §4Warp deleted!");
				  }
			  }
		  } else if ((args.length <= 1) || ((cs instanceof Player))) {
			  Player p = (Player)cs;
			  if (p.hasPermission("admininv.delwarp")) {
				  if (this.plugin.getConfig().getString("NOTIFICATIONS.Args").equalsIgnoreCase("True")) {
					  p.sendMessage(Prefix + " §4Too many arguments!");
					  p.sendMessage(Prefix + " §fPlease use: §3/delwarp §2[Warpname]");
				  }
			  } else if (this.plugin.getConfig().getString("NOTIFICATIONS.Perm").equalsIgnoreCase("True"))
				  p.sendMessage(ChatColor.RED + "You do not have permission to do that!");
		  }
		  else if (!(cs instanceof Player)) {
			  cs.sendMessage(Prefix + " §4Too few arguments!");
			  cs.sendMessage(Prefix + " §fPlease use: §3/delwarp §2[Warpname]");
		  }
	  }
	  return false;
  }
}
