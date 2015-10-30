package de.Fabian996.Admin.Listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class Blocken implements Listener {
	
	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";

	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onUnknow(PlayerCommandPreprocessEvent e){
		if(!(e.isCancelled())){
			Player p = e.getPlayer();
			String msg = e.getMessage().split(" ") [0];
			HelpTopic top = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
			if(top == null){
				p.playSound(p.getLocation(), Sound.ITEM_BREAK, 2F, 1F);
				p.sendMessage(Prefix + "§8[ §b§l! §8] §cWrong command ");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onplugin(PlayerCommandPreprocessEvent e){
		String args[] = e.getMessage().split(" ");
		if((args[0].equalsIgnoreCase("/pl")) || (args[0].equalsIgnoreCase("/plugins"))){
			Player p = e.getPlayer();
			if(p.isOp()){
				
			}else{
				e.setCancelled(true);
				p.sendMessage(Prefix + " §cYou do not have permission");
			}
		}
	
	}
}
