package de.Fabian996.Admin.Listener;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import de.Fabian996.Admin.Utils.PlayerManager;
import de.Fabian996.Admin.Utils.cache;
import de.Fabian996.Admin.main.AdminMenu;

public class warp implements Listener , CommandExecutor{

	  public static File warpsfile = new File("plugins/AdminInv", "warps.yml");
	  public static FileConfiguration warpcfg = YamlConfiguration.loadConfiguration(warpsfile);
	  public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";

	  
	  public warp(AdminMenu adminMenu) {
	  }
	  
	  public warp(){
		  
	  }

	  @Override
	  public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		  if(cs instanceof Player){
			  Player p = (Player)cs;
			  if(args.length == 0){
				  StringBuilder sb = new StringBuilder();
				  sb.append(Prefix + "§rWarps: §e");
				  
				  int i = 0;
				  for (String warp : warpcfg.getKeys(true)){
					  if(warpcfg.contains(warp + ".name")){
						  if(i == 1){
							  sb.append(", ");
						  }else{
							  i = 1;
						  }
						  sb.append(warpcfg.get(warp + ".name"));
					  }
				  }
				  p.sendMessage(sb.toString());
			  }
			  if(args.length == 1){
				  if(p.hasPermission("warp.use")){
					  String warp = args[0].toLowerCase();
					  World world = Bukkit.getWorld(warpcfg.getString(warp + ".name"));
					  
					  if(warpcfg.contains(warp + ".name")){
						  double x = warpcfg.getDouble(warp + ".x"); 
						  double y = warpcfg.getDouble(warp + ".y"); 
						  double z = warpcfg.getDouble(warp + ".z");
						  double yaw = warpcfg.getDouble(warp + ".yaw");
						  double pitch = warpcfg.getDouble(warp + ".pitch");
						  if(world != null){
							  Location loc = new Location(world, x, y, z);
							  loc.setPitch((float)pitch);
							  loc.setYaw((float)yaw);
							  
							  p.teleport(loc);
							  p.sendMessage(Prefix + " §2You are now at the warp §c" + warpcfg.getString(new StringBuilder(String.valueOf(warp)).append(".name").toString()));
						  }else{
							  p.sendMessage(cache.noWorld);
						  }  
					  }else{
						  p.sendMessage(cache.noWarp);
					  }
				  }else{
					  p.sendMessage(cache.noPerm);
				  }
			  }
			  if(args.length == 2){
				  if(args[0].equalsIgnoreCase("set")){
					  if(p.hasPermission("warp.set")){
						  String warp = args[1].toLowerCase();
						  Location loc = p.getLocation();
						  
				           warpcfg.set(warp + ".name", args[1]);
				           warpcfg.set(warp + ".world", loc.getWorld().getName());
				           warpcfg.set(warp + ".x", Double.valueOf(loc.getX()));
				           warpcfg.set(warp + ".y", Double.valueOf(loc.getY()));
				           warpcfg.set(warp + ".z", Double.valueOf(loc.getZ()));
				           warpcfg.set(warp + ".yaw", Float.valueOf(loc.getYaw()));
				           warpcfg.set(warp + ".pitch", Float.valueOf(loc.getPitch()));
				           try{
				        	   warpcfg.save(warpsfile);
				        	   p.sendMessage(Prefix + " §2The warp §c" + warpcfg.getString(new StringBuilder(String.valueOf(warp)).append(".name").toString()) + " §2was created successfully!");
				           }catch (IOException e){
				        	   p.sendMessage(Prefix + " §4Error: §c" + e.getMessage());
				           }
					  }else{
						  p.sendMessage(cache.noPerm);
					  }
				  }else if(args[0].equalsIgnoreCase("del")){
			          if (p.hasPermission("warp.del")) {
			              String warp = args[1].toLowerCase();
			              if (warpcfg.contains(warp + ".name"))
			              {
			                for (String path : warpcfg.getConfigurationSection(warp).getKeys(true)) {
			                  warpcfg.set(warp + "." + path, null);
			                }
			                try
			                {
			                  warpcfg.save(warpsfile);
			                  p.sendMessage(Prefix + " §2The informations of warp §c" + warp + "§2 were removed successfully!");
			                  p.sendMessage(Prefix + " §cPlease remove the name of the warp in the §2warps.yml§c for no errors!");
			                } catch (IOException e) {
			                  p.sendMessage(Prefix + " §4Error: §c" + e.getMessage());
			                }
			              } else {
			                p.sendMessage(cache.noWarp);
			              }
			            } else {
			              p.sendMessage(cache.noPerm);
			            }
				  } else if (p.hasPermission("warp.tpplayer")) {
			          String warp = args[0].toLowerCase();
			          World world = Bukkit.getWorld(warpcfg.getString(warp + ".world"));

			          if (warpcfg.contains(warp + ".name")) {
			            double x = warpcfg.getDouble(warp + ".x");
			            double y = warpcfg.getDouble(warp + ".y");
			            double z = warpcfg.getDouble(warp + ".z");
			            double yaw = warpcfg.getDouble(warp + ".yaw");
			            double pitch = warpcfg.getDouble(warp + ".pitch");

			            if (world != null) {
			              Player t = PlayerManager.getPlayer(args[1]);
			              if (t != null) {
			                Location loc = new Location(world, x, y, z);
			                loc.setPitch((float)pitch);
			                loc.setYaw((float)yaw);

			                t.teleport(loc);
			                t.sendMessage(Prefix + "§2You are now at the warp §4" + warpcfg.getString(new StringBuilder(String.valueOf(warp)).append(".name").toString()) + "\n§2! The Player §4" + p.getName() + " §2teleport you!");
			                p.sendMessage(Prefix + "§2You teleport the Player §4" + t.getName() + "§2 to the warp §4" + warpcfg.getString(new StringBuilder(String.valueOf(warp)).append(".name").toString()) + " §2!");
			              }
			            } else {
			              p.sendMessage(cache.noWorld);
			            }
			          } else {
			            p.sendMessage(cache.noWarp);
			          }
			        } else {
			          p.sendMessage(cache.noPerm);
			        }
			    }
			    return false;
			  }
		return false;
	  }
}
