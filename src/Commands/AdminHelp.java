package de.Fabian996.Admin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminHelp implements CommandExecutor {

	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("adminhelp")){
			Player p = (Player)cs;
			p.sendMessage("\n" + Prefix + "§n§7AdminInv §fmade by §4Fabian996. \n\n");
			if((p.hasPermission("admininv.adminhelp")) || ((p.isOp()))){
				p.sendMessage("\n\n" + Prefix + " §6§nAdmin Commands");
				//GUI Command
				p.sendMessage(Prefix + " §3/admininv §f- Open the Admin Invenotry");
				p.sendMessage(Prefix + " §3/modinv §f- Open the Moderator Inventory \n");
				
				//Give Commands
				p.sendMessage("\n" + Prefix + " §3/giveghast §f- Give Admin Item in the Slot§6 1");
				p.sendMessage(Prefix + " §3/giveblaze §f- Give Moderator Item in the Slot§6 2");
				
				//Help Commands
				p.sendMessage("\n" + Prefix + " §3/moderatorhelp §f- Show all Moderator commands on");
					//Warp Commands
				p.sendMessage(Prefix + " §3/warphelp §f- Show all Warp commands");
				
				//Lobby | Spawn | Hub System Commands
					//Spawn
				p.sendMessage("\n" + Prefix + " §3/setspawn §f- Set the Spawn");
				p.sendMessage(Prefix + " §3/spawn §f- Teleport to the Spawn \n");
					//Lobby
				p.sendMessage("\n" + Prefix + " §3/setlobby §f- Set the Lobby");
				p.sendMessage(Prefix + " §3/lobby §f- Teleport to the Lobby \n");
					//Hub
				p.sendMessage("\n" + Prefix + " §3/sethub §f- Set the Hub");
				p.sendMessage(Prefix + " §3/hub §f- Teleport to the Hub \n");
			}
		}
		return false;
	}
}
