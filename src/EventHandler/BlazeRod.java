package de.Fabian996.Admin.EventHandler;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlazeRoad implements Listener {
	
	@EventHandler
	public void onBlazeClick(PlayerInteractEvent e)
	{
		if((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)){
			if(e.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD){
					Player p = e.getPlayer();
					p.playSound(p.getLocation(), Sound.FIREWORK_BLAST2, 1000.0F, 6.0F);
					Bukkit.dispatchCommand(p.getPlayer(), "modinv");	
			}
		}
	}
}
