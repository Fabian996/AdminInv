package de.Fabian996.Admin.Funktion;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class AdminFunction implements Listener{
	public static final String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "AdminInv" + ChatColor.DARK_GRAY + "] ";
	
	private ArrayList<Player> vanished = new ArrayList<>();
	
	@EventHandler
	public void Inventory(InventoryClickEvent e)
	{	ItemStack Ghast = new ItemStack(Material.GHAST_TEAR);
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equalsIgnoreCase(ChatColor.RED + "Admin Inventar")){
			e.setCancelled(true);
		
			// Heal Function
			if(e.getCurrentItem().getType() == Material.POTION){
				p.setHealth(20);
				p.setFoodLevel(20);
				p.sendMessage((Prefix + "§7§l%p% §3You are healed").replace("%p%",p.getName()));
				e.getView().close();
			}
			
			//Fly Function
			if(e.getCurrentItem().getType() == Material.FEATHER){
	            if (p.getAllowFlight()) {
		              p.setFlying(false);
		              p.setAllowFlight(false);
		              p.sendMessage((Prefix + "§7%p% §chas Fly disabled").replace("%p%",p.getName()));
		            } else {
		              p.setAllowFlight(true);
		              p.setFlySpeed(0.1F);
		              p.sendMessage((Prefix + "§7%p% §2has Fly Enabled").replace("%p%",p.getName()));		              
		            }
				e.getView().close();
			}
			
			//Gamemode 0
			if(e.getCurrentItem().getType() ==  Material.BED){
				p.setGameMode(GameMode.SURVIVAL);
				p.sendMessage((Prefix + "§7§l%p%§2 You play now in the §lSurvival Mode").replace("%p%",p.getName()));
				e.getView().close();
			}
			//Gamemode 1
			if(e.getCurrentItem().getType() ==  Material.ENDER_PORTAL_FRAME){
				p.getInventory().clear();
				p.getInventory().addItem(Ghast);
				p.setGameMode(GameMode.CREATIVE);
				p.sendMessage((Prefix + "§7§l%p%§6 You play now in the §lCreative Mode").replace("%p%",p.getName()));
				e.getView().close();
			}
			//Gamemode 2
			if(e.getCurrentItem().getType() ==  Material.DISPENSER){
				p.setGameMode(GameMode.ADVENTURE);
				p.sendMessage((Prefix + "§7§l%p%§f You play now in the §lAdventure Mode").replace("%p%",p.getName()));
				e.getView().close();
			}
			//Gamemode 3
			if(e.getCurrentItem().getType() == Material.BONE){
				p.setGameMode(GameMode.SPECTATOR);
				p.sendMessage((Prefix + "§7§l%p%§2 You play now in the §lSpectator Mode").replace("%p%",p.getName()));
				e.getView().close();
			}
			//Day
			if (e.getCurrentItem().getType() == Material.DAYLIGHT_DETECTOR) {
				World world = p.getWorld();
				world.setTime(2000L);
				p.sendMessage(Prefix + "§7The Time changed to §eDay§7 in world " + world.getName());
				e.getView().close();
			}
		      
			//Night
			if (e.getCurrentItem().getType() == Material.NETHER_BRICK) {
				World world = p.getWorld();
				world.setTime(18000L);
				p.sendMessage(Prefix + "§7The Time changed to §8Night§7 in world " + world.getName());
				e.getView().close();
			}
			//Reload
			if(e.getCurrentItem().getType() == Material.BARRIER){
				e.getView().close();
				Bukkit.reload();
				p.sendMessage((Prefix + "§7%p%§2 §6has the Server Reload").replace("%p%",p.getName()));
		      }
			
			//Server Stop
			if(e.getCurrentItem().getType() == Material.SPONGE){
				e.getView().close();
				Bukkit.broadcastMessage(Prefix + "§4The Server stopt");
				Bukkit.shutdown();
			}
			// Toggle downfall <on/off>
			if(e.getCurrentItem().getType() == Material.BLAZE_POWDER){
				World world = p.getWorld();
				p.getWorld().setStorm(false);
				p.getWorld().setThundering(false);
				p.sendMessage(Prefix + "§7In the world §e" + world.getName() + "§7 is the Weather §eClear");
				e.getView().close();
			}
			if(e.getCurrentItem().getType() == Material.WATER_BUCKET){
				World world = p.getWorld();
				p.getWorld().setStorm(true);
				p.getWorld().setThundering(true);
				p.sendMessage(Prefix + "§7In the world §f" + world.getName() + "§7 is the Weather §fBegins");
				e.getView().close();
			}
			
			//Vanish
			if(e.getCurrentItem().getType() == Material.SLIME_BALL){
				if (!this.vanished.contains(p)) {
					for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
						pl.hidePlayer(p);
					}
					this.vanished.add(p);
					p.sendMessage((Prefix + "§7%p% §aYou are now §lVanish").replace("%p%",p.getName()));
					e.getView().close();
				}
					else if(e.getCurrentItem().getType() == Material.SLIME_BALL){
						for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
							pl.showPlayer(p);
						}
						this.vanished.remove(p);
						p.sendMessage((Prefix + "§7%p% §4You are not §lVanish").replace("%p%",p.getName()));
						e.getView().close();
					}
				}
			//Difficult <Peaceful/Easy/Normal/Hard> 
			if(e.getCurrentItem().getType() == Material.APPLE){
				p.getWorld().setDifficulty(Difficulty.PEACEFUL);
				p.sendMessage(Prefix + "§rDifficulty in world §e" + p.getLocation().getWorld().getName() + "§r set to §epeaceful");
				e.getView().close();
			}
			if(e.getCurrentItem().getType() == Material.ARROW){
				p.getWorld().setDifficulty(Difficulty.EASY);
				p.sendMessage(Prefix + "§rDifficulty in world §6" + p.getLocation().getWorld().getName() + "§r set to §6Easy");
				e.getView().close();
			}
			if(e.getCurrentItem().getType() == Material.ENCHANTMENT_TABLE){
				p.getWorld().setDifficulty(Difficulty.NORMAL);
				p.sendMessage(Prefix + "§rDifficulty in world §a" + p.getLocation().getWorld().getName() + "§r set to §aNormal");
				e.getView().close();
			}
			if(e.getCurrentItem().getType() == Material.TNT){
				p.getWorld().setDifficulty(Difficulty.HARD);
				p.sendMessage(Prefix + "§rDifficulty in world §4" + p.getLocation().getWorld().getName() + "§r set to §4Hard");
				e.getView().close();
				
			}
		}
			
	}
}
