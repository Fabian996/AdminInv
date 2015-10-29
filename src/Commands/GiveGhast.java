package de.Fabian996.Admin.Commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveGhast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player)cs;
		
		ItemStack Ghast = new ItemStack(Material.GHAST_TEAR);
		ItemMeta Ghastmeta = Ghast.getItemMeta();
		ArrayList<String > Admin = new ArrayList<String>();
		Ghastmeta.setDisplayName("§cAdmin Inventory");
		Admin.add("Open the Admin Inventory");
		Ghastmeta.setLore(Admin);
		Ghast.setItemMeta(Ghastmeta);
		if(p.isOp() || p.hasPermission("admininv.ghast")){

			p.getInventory().setItem(0, Ghast);
			
		}
		return false;
	}

}
