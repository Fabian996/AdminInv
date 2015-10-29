package de.Fabian996.Admin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {

	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("adminhelp")){
			Player p = (Player)cs;
			p.sendMessage(Prefix + "§n§7AdminInv §8made by Fabian996. \n\n");
			if((p.hasPermission("admin.help")) || ((p.isOp()))){
				p.sendMessage("\n\n" + Prefix + " §6§nAdmin Commands");
				p.sendMessage(Prefix + " §3/admininv §f- Open the Admin Invenotry");
				p.sendMessage(Prefix + " §3/modinv §f- Open the Moderator Inventory \n");
				
				p.sendMessage(Prefix + " §3/giveghast §f- Give Admin Item in the Slot§6 1");
				p.sendMessage(Prefix + " §3/giveblaze §f- Give Moderator Item in the Slot§6 2");
				
				p.sendMessage(Prefix + " §3/warp §6<set/del> §f- Create and Delete Warp's");
			}else if(p.hasPermission("moderator.help")){
				p.sendMessage(Prefix + " §3/modinv §f- Open the Moderator Inventory \n");
				
				p.sendMessage(Prefix + " §3/giveblaze §f- Give Moderator Item in the Slot§6 2");
				
				p.sendMessage(Prefix + " §3/warp §6<set/del> §f- Create and Delete Warp's");
				}
			}
		return false;
	}
}
