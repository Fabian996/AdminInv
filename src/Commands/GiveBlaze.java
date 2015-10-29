package de.Fabian996.Admin.Commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveBlaze implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		Player p = (Player)cs;
		
		ItemStack Blaze = new ItemStack(Material.BLAZE_ROD);
		ItemMeta Blazemeta = Blaze.getItemMeta();
		ArrayList<String > Moderator = new ArrayList<String>();
		Blazemeta.setDisplayName("§aModerator Inventory");
		Moderator.add("Open the Moderator Inventory");
		Blazemeta.setLore(Moderator);
		Blaze.setItemMeta(Blazemeta);
		if(p.isOp() || p.hasPermission("modinv.blaze")){

			p.getInventory().setItem(1, Blaze);
			
		}
		return false;
	}

}
