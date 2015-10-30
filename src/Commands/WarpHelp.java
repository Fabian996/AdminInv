package de.Fabian996.Admin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpHelp implements CommandExecutor {

	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("warphelp")){
			Player p = (Player)cs;
			if(p.hasPermission("admin.warphelp")){
				p.sendMessage("\n" + Prefix + "§n§7AdminInv §fmade by §4Fabian996. \n");
			
				p.sendMessage("\n"+ Prefix + " §6§nWarp Commands");
				p.sendMessage(Prefix + " §3/setwarp §2[Warpname] §f- Set a Warp Point");
				p.sendMessage(Prefix + " §3/delwarp §2[Warpname] §f- Del a Warp Point");
				p.sendMessage(Prefix + " §3/warp §2[Warpname] §6<Player> §f- Teleport a Player to the Warp point");
				p.sendMessage(Prefix + " §3/warp §2[Warpname] §f- Teleport to the Warp point");
			}
		}	
		return false;
	}

}
