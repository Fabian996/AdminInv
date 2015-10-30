package de.Fabian996.Admin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.Fabian996.Admin.main.AdminMenu;

public class SpawnCommand implements CommandExecutor {
	  
	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
	
	AdminMenu plugin;

	public SpawnCommand(AdminMenu instance) {
		this.plugin = instance;
	}

	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("setspawn")){
			if (!(cs instanceof Player)) {
				cs.sendMessage(Prefix + "§4This command can now Player use");
			}else {
				Player p = (Player)cs;
				if (p.hasPermission("admininv.setspawn")) {
					Location loc = p.getLocation();
					Bukkit.getWorld("world").setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
					p.sendMessage(Prefix + "§7The spawn has been set!");
				}else {
					p.sendMessage(Prefix + "§4You do not have Permission to do that");
				}
			}
		} else if (cmd.getName().equalsIgnoreCase("spawn")){
			if (!(cs instanceof Player)) {
				cs.sendMessage(Prefix + " §4This command can now Player use");
			}else {
				Player p = (Player)cs;
				if (p.hasPermission("admininv.spawn")) {
					p.teleport(p.getWorld().getSpawnLocation());
					cs.sendMessage(Prefix + "§2You are now at the Spawn!");
				}
			}
		}
		return false;
	}
}
