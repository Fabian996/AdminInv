package de.Fabian996.Admin.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import de.Fabian996.Admin.Commands.GiveBlaze;
import de.Fabian996.Admin.Commands.GiveGhast;
import de.Fabian996.Admin.Commands.AdminHelp;
import de.Fabian996.Admin.Commands.HubCommand;
import de.Fabian996.Admin.Commands.LobbyCommand;
import de.Fabian996.Admin.Commands.ModeratorHelp;
import de.Fabian996.Admin.Commands.SpawnCommand;
import de.Fabian996.Admin.Commands.WarpCommand;
import de.Fabian996.Admin.Commands.WarpHelp;
import de.Fabian996.Admin.EventHandler.BlazeRoad;
import de.Fabian996.Admin.EventHandler.GhastTear;
import de.Fabian996.Admin.Function.AdminFunction;
import de.Fabian996.Admin.Function.ModeratorFunction;
import de.Fabian996.Admin.Listener.Blocken;


public class AdminMenu extends JavaPlugin{
	
	public Inventory inv = null;
	public Inventory inv1 = null;
	public Inventory inv2 = null;
	
	Logger log = getLogger();
	
	public void onEnable()	{	
	System.out.println("[AdminInv] =================================");
	System.out.println("[AdminInv] Author: " + getDescription().getAuthors());
	System.out.println("[AdminInv] Version: v" + getDescription().getVersion());
	System.out.println("[AdminInv] Status: Aktiviert");
	System.out.println("[AdminInv] =================================");
	    
	    
	Metrics();
	    
	registerCommands();
	registerListener();
	registerConfig();
	
	//Report System [Up Version 1.4]
		
	    
	//Warning System [Up Version 1.4]
		
	    
	//Kick System [Up Version 1.4]
	/*  getCommand("kick").setExecutor(new KickSystem());
	* 
	*/
	    
	//TempBan System [Up Version 1.4]
	/*  getCommand("temban").setExecutor(new TemBanSystem());
	* 
	*/
	    
	//PermaBan System [Up Version 1.4]
	/*  getCommand("ban").setExecutor(new BanSystem());
	* 
	*/
	    
	//Unban System
	/*  getCommand("unban").setExecutor(new UnbanSystem());
	* 
	*/
	    
	//Teleport System [Up Version 1.4]
	    
	    
	//AutoBroadcast [Up Version 1.5]
	/*  getCommand("ab").setExecutor(new AutoBroadcast());
	* 
	*/
	   
	}


	public void  Metrics(){
		if (getConfig().getBoolean("Metrics")) {
            try {
                Metrics metrics = new Metrics(this);
                metrics.start();
            } catch (IOException e) {
                e.printStackTrace();
                log.severe("[AdminInv] Metrics Failed To Start!");
            }
        } else {
            getLogger()
                    .info("Metrics wasn't started because it is disabled in the config!");
        }
    }
	

	public void onDisable()
	{
	System.out.println("[AdminInv] =================================");
	System.out.println("[AdminInv] Author: " + getDescription().getAuthors());
	System.out.println("[AdminInv] Version: v" + getDescription().getVersion());
	System.out.println("[AdminInv] Status: Deaktiviert");
	System.out.println("[AdminInv] =================================");
	}


	private void registerConfig() {
	
	File file = new File(getDataFolder() + File.separator + "config.yml");
	if (!file.exists()){
	getLogger().info("Generating config.yml");

	getConfig().options().header("#		   AdminInv\n# 	  Version 1.3\n#    Author: Fabian996\n#   Developer: Fabian996");
	getConfig().addDefault("ConfigVersion", "3.1");
	getConfig().addDefault("HubEnabled", "True");
	getConfig().addDefault("LobbyEnabled", "True");
	getConfig().addDefault("WarpEnabled", "True");
	getConfig().addDefault("Join.Spawn", "True");
	getConfig().addDefault("Join.Hub", "False");
	getConfig().addDefault("Join.Lobby", "False");
	getConfig().addDefault("NOTIFICATIONS.Warp", "True");
	getConfig().addDefault("NOTIFICATIONS.Perm", "True");
	getConfig().addDefault("NOTIFICATIONS.Args", "True");
	       
	if(getConfig().getInt("ConfigVersion") !=3.1){
		if (!getConfig().contains("Metrics")) {
			getConfig().set("Metrics", true);
		}
	}
	        
	getConfig().options().copyDefaults(true);
	saveConfig();

	getConfig().options().copyDefaults(true);
	saveConfig();
	}
	if (!getConfig().getString("ConfigVersion").equalsIgnoreCase("3.1")){
		getLogger().warning("Config Outdated!!!! Plugin will not work properly!! Please copy your settings, delete the config and restart your server!!");
	}
}

	
	public void registerCommands(){
	//AdminInv Commands [Up Version 1.2]
	getCommand("giveghast").setExecutor(new GiveGhast());
	getCommand("giveblaze").setExecutor(new GiveBlaze());
	getCommand("adminhelp").setExecutor(new AdminHelp());
	getCommand("modhelp").setExecutor(new ModeratorHelp());
		
	//Warp Command [Up Version 1.3]
	getCommand("warp").setExecutor(new WarpCommand(this));
	getCommand("delwarp").setExecutor(new WarpCommand(this));
	getCommand("setwarp").setExecutor(new WarpCommand(this));
	getCommand("warphelp").setExecutor(new WarpHelp());
		
	//Spawn System [Up Version 1.3]
	getCommand("setspawn").setExecutor(new SpawnCommand(this));
	getCommand("spawn").setExecutor(new SpawnCommand(this));
		
	//Lobby System [Up Version 1.3]
	getCommand("setlobby").setExecutor(new LobbyCommand(this));
	getCommand("lobby").setExecutor(new LobbyCommand(this));
	    
	//Hub System [Up Version 1.3]
	getCommand("sethub").setExecutor(new HubCommand(this));
	getCommand("hub").setExecutor(new HubCommand(this));
	    
	}
	
	public void registerListener(){
	//Admin Inventory [Up Version 1.0]
	getServer().getPluginManager().registerEvents(new AdminFunction(), this);
		
	//Moderator Inventory [Up Version 1.1]
	getServer().getPluginManager().registerEvents(new ModeratorFunction(), this);
		
	//Menü Open [Up Version 1.0 | Up Version 1.1]
	getServer().getPluginManager().registerEvents(new GhastTear(), this); // Admin
	getServer().getPluginManager().registerEvents(new BlazeRoad(), this); // Moderator
	
	//Blocken [Up Version 1.3]
	getServer().getPluginManager().registerEvents(new Blocken(), this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("admininv")){
			if(p.hasPermission("admininv.admininv")){
				p.playSound(p.getLocation(), Sound.FIREWORK_BLAST, 1000.0F, 6.0F);
				inv = p.getPlayer().getServer().createInventory(null, 45, ChatColor.RED + "Admin Inventory");
				//	|0 |1 |2 |3 |4 |5 |6 |7 |8 |
				// 	|9 |10|11|12|13|14|15|16|17|
				// 	|18|19|20|21|22|23|24|25|26|
				// 	|27|28|29|30|31|32|33|34|35|
				//	|36|37|38|39|40|41|42|43|44|
				// Feautre: MonsterSpawn Inventory
				
				ItemStack Heal = new ItemStack(Material.POTION);
				ItemMeta Healmeta = Heal.getItemMeta();
				ArrayList<String> Heal1 = new ArrayList<String>();
				Healmeta.setDisplayName(ChatColor.DARK_RED + "Heal");
				Heal1.add(ChatColor.WHITE + "You Heal by a Admin");
				Healmeta.setLore(Heal1);
				Heal.setItemMeta(Healmeta);
			
				ItemStack Fly = new ItemStack(Material.FEATHER);
				ItemMeta Flymeta = Fly.getItemMeta();
				ArrayList<String> Flyi = new ArrayList<String>();
				Flymeta.setDisplayName(ChatColor.DARK_AQUA + "Fly <on/off>");
				Flyi.add(ChatColor.DARK_AQUA + "You can Fly in the high");
				Flymeta.setLore(Flyi);
				Fly.setItemMeta(Flymeta);

				ItemStack SM = new ItemStack(Material.BED);
				ItemMeta SMmeta = SM.getItemMeta();
				ArrayList<String> SuM = new ArrayList<String>();
				SMmeta.setDisplayName(ChatColor.GOLD + "Survival Mode");
				SuM.add(ChatColor.DARK_GREEN + "You can whit this Item you change your Mode to Survival");
				SMmeta.setLore(SuM);
				SM.setItemMeta(SMmeta);
				
				ItemStack CM = new ItemStack(Material.ENDER_PORTAL_FRAME);
				ItemMeta CMmeta = CM.getItemMeta();
				ArrayList<String> CrM = new ArrayList<String>();
				CMmeta.setDisplayName(ChatColor.DARK_RED + "Creative Mode");
				CrM.add(ChatColor.GOLD + "You can whit this Item you change your Mode to Creative");
				CMmeta.setLore(CrM);
				CM.setItemMeta(CMmeta);

				ItemStack AM = new ItemStack(Material.DISPENSER);
				ItemMeta AMmeta = AM.getItemMeta();
				ArrayList<String> AdM = new ArrayList<String>();
				AMmeta.setDisplayName(ChatColor.DARK_AQUA + "Adventure Mode");
				AdM.add(ChatColor.YELLOW + "You can whit this Item you change your Mode to Adventure");
				AMmeta.setLore(AdM);
				AM.setItemMeta(AMmeta);

				ItemStack SPM = new ItemStack(Material.BONE);
				ItemMeta SPMmeta = SPM.getItemMeta();
				ArrayList<String> SPeM = new ArrayList<String>();
				SPMmeta.setDisplayName(ChatColor.BLUE + "Spectator Mode");
				SPeM.add(ChatColor.BLUE + "You can whit this Item you change your Mode to Spectator");
				SPMmeta.setLore(SPeM);
				SPM.setItemMeta(SPMmeta);
		
				ItemStack Day = new ItemStack(Material.DAYLIGHT_DETECTOR);
				ItemMeta Daymeta = Day.getItemMeta();
				ArrayList<String> Sun = new ArrayList<String>();
				Daymeta.setDisplayName(ChatColor.YELLOW + "Day");
				Sun.add(ChatColor.GRAY + "You make Day of the World");
				Daymeta.setLore(Sun);
				Day.setItemMeta(Daymeta);

				ItemStack Night = new ItemStack(Material.NETHER_BRICK);
				ItemMeta Nightmeta = Night.getItemMeta();
				ArrayList<String> Nacht = new ArrayList<String>();
				Nightmeta.setDisplayName(ChatColor.DARK_GRAY + "Night");
				Nacht.add(ChatColor.GRAY + "You make Night of the World");
				Nightmeta.setLore(Nacht);
				Night.setItemMeta(Nightmeta);
				
				ItemStack Reload = new ItemStack(Material.BARRIER);
				ItemMeta Reloadmeta = Reload.getItemMeta();
				ArrayList<String> RL = new ArrayList<String>();
				Reloadmeta.setDisplayName(ChatColor.GREEN + "Reload the Server");
				RL.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Please use with caution for Reload");
				Reloadmeta.setLore(RL);
				Reload.setItemMeta(Reloadmeta);
				
				ItemStack Stop = new ItemStack(Material.SPONGE);
				ItemMeta Stopmeta = Stop.getItemMeta();
				ArrayList<String> Admin = new ArrayList<String>();
				Stopmeta.setDisplayName(ChatColor.RED + "Stop the Server");
				Admin.add(ChatColor.BLUE + "The item only as a admin to use for the server Stop ");
				Stopmeta.setLore(Admin);
				Stop.setItemMeta(Stopmeta);
				
				//Vanish
				ItemStack Vanish = new ItemStack(Material.SLIME_BALL);
				ItemMeta Vanishmeta = Vanish.getItemMeta();
				ArrayList<String> V = new  ArrayList<String>();
				Vanishmeta.setDisplayName(ChatColor.DARK_PURPLE + "Vanish <on/off>");
				V.add(ChatColor.DARK_AQUA + "You can change to Vanish and Spy the Player");
				Vanishmeta.setLore(V);
				Vanish.setItemMeta(Vanishmeta);
								
				//Weather
				ItemStack WeatherCl = new ItemStack(Material.WATER_BUCKET);
				ItemMeta WeatherClmeta= WeatherCl.getItemMeta();
				ArrayList<String> Weather = new ArrayList<String>();
				WeatherClmeta.setDisplayName(ChatColor.GREEN + "Toggledownfall on");
				Weather.add(ChatColor.DARK_PURPLE + "Weather on");
				WeatherClmeta.setLore(Weather);
				WeatherCl.setItemMeta(WeatherClmeta);
				
				ItemStack ClearW = new ItemStack(Material.BLAZE_POWDER);
				ItemMeta ClearWmeta = ClearW.getItemMeta();
				ArrayList<String> Weather1 = new ArrayList<String>();
				ClearWmeta.setDisplayName(ChatColor.DARK_GREEN + "Toggledownfall off");
				Weather1.add(ChatColor.DARK_PURPLE + "Weather off");
				ClearWmeta.setLore(Weather1);
				ClearW.setItemMeta(ClearWmeta);
				
				//Difficult <Peaceful/Easy/Normal/Hard>
				ItemStack Peaceful = new ItemStack(Material.APPLE);
				ItemMeta Peacefulmeta = Peaceful.getItemMeta();
				ArrayList<String> P = new ArrayList<String>();
				Peacefulmeta.setDisplayName(ChatColor.YELLOW + "Peaceful ");
				P.add(ChatColor.AQUA + "Change to Peaceful Mode");
				Peacefulmeta.setLore(P);
				Peaceful.setItemMeta(Peacefulmeta);
				
				ItemStack Easy = new ItemStack(Material.ARROW);
				ItemMeta Easymeta = Easy.getItemMeta();
				ArrayList<String> E = new ArrayList<String>();
				Easymeta.setDisplayName(ChatColor.GOLD + "Easy ");
				E.add(ChatColor.AQUA + "Change to Easy Mode");
				Easymeta.setLore(E);
				Easy.setItemMeta(Easymeta);
				
				ItemStack Normal = new ItemStack(Material.ENCHANTMENT_TABLE);
				ItemMeta Normalmeta = Normal.getItemMeta();
				ArrayList<String> N = new ArrayList<String>();
				Normalmeta.setDisplayName(ChatColor.GREEN + "Normal ");
				N.add(ChatColor.AQUA + "Change to Normal Mode");
				Normalmeta.setLore(N);
				Normal.setItemMeta(Normalmeta);
				
				ItemStack Hard = new ItemStack(Material.TNT);
				ItemMeta Hardmeta = Hard.getItemMeta();
				ArrayList<String> H = new ArrayList<String>();
				Hardmeta.setDisplayName(ChatColor.DARK_RED + "Hard ");
				H.add(ChatColor.AQUA + "Change to Hard Mode");
				Hardmeta.setLore(H);
				Hard.setItemMeta(Hardmeta);
				
				//Clear Inventory
				ItemStack Clear = new ItemStack(Material.CACTUS);
				ItemMeta Clearmeta = Clear.getItemMeta();
				ArrayList<String> CI = new ArrayList<String>();
				Clearmeta.setDisplayName(ChatColor.DARK_GRAY + "Clear Inventory");
				CI.add("You can clear Inventory");
				Clearmeta.setLore(CI);
				Clear.setItemMeta(Clearmeta);
				
				
				//Inventory Line 1
				inv.setItem(0, Heal);
				inv.setItem(3, SM);
				inv.setItem(4, CM);
				inv.setItem(5, AM);
				inv.setItem(8, Fly);							
				inv.setItem(13, SPM);		
				
				//Inventory Line 2

				//Inventory Line 3
				inv.setItem(18, Day);
				inv.setItem(19, Night);
				inv.setItem(23, Peaceful);
				inv.setItem(24, Easy);
				inv.setItem(25, Normal);
				inv.setItem(26, Hard);
				
				//Inventory Line 4
				inv.setItem(28, WeatherCl);
				inv.setItem(27, ClearW);
				inv.setItem(35, Clear);

				
				//Inventory Line 5
				inv.setItem(39, Reload);
				inv.setItem(40, Vanish);
				inv.setItem(41, Stop);

				p.getPlayer().openInventory(inv);
				
				return true;
				}
			}else if(cmd.getName().equalsIgnoreCase("modinv")){
				if(p.hasPermission("admininv.modinv")){
					p.playSound(p.getLocation(), Sound.FIREWORK_LAUNCH, 1000.0F, 6.0F);
					inv1 = p.getPlayer().getServer().createInventory(null, 45, ChatColor.DARK_GREEN +"Moderator Inventory");
					//	|0 |1 |2 |3 |4 |5 |6 |7 |8 |
					// 	|9 |10|11|12|13|14|15|16|17|
					// 	|18|19|20|21|22|23|24|25|26|
					// 	|27|28|29|30|31|32|33|34|35|
					//	|36|37|38|39|40|41|42|43|44|
					
					ItemStack Heal = new ItemStack(Material.POTION);
					ItemMeta Healmeta = Heal.getItemMeta();
					ArrayList<String> Heal1 = new ArrayList<String>();
					Healmeta.setDisplayName(ChatColor.DARK_RED + "Heal");
					Heal1.add(ChatColor.WHITE + "You Heal by a Admin");
					Healmeta.setLore(Heal1);
					Heal.setItemMeta(Healmeta);
					
					ItemStack Fly = new ItemStack(Material.FEATHER);
					ItemMeta Flymeta = Fly.getItemMeta();
					ArrayList<String> Flyi = new ArrayList<String>();
					Flymeta.setDisplayName(ChatColor.DARK_AQUA + "Fly <on/off>");
					Flyi.add(ChatColor.DARK_AQUA + "You can Fly in the high");
					Flymeta.setLore(Flyi);
					Fly.setItemMeta(Flymeta);

					ItemStack SM = new ItemStack(Material.BED);
					ItemMeta SMmeta = SM.getItemMeta();
					ArrayList<String> SuM = new ArrayList<String>();
					SMmeta.setDisplayName(ChatColor.GOLD + "Survival Mode");
					SuM.add(ChatColor.DARK_GREEN + "You can whit this Item you change your Mode to Survival");
					SMmeta.setLore(SuM);
					SM.setItemMeta(SMmeta);
					
					ItemStack CM = new ItemStack(Material.ENDER_PORTAL_FRAME);
					ItemMeta CMmeta = CM.getItemMeta();
					ArrayList<String> CrM = new ArrayList<String>();
					CMmeta.setDisplayName(ChatColor.DARK_RED + "Creative Mode");
					CrM.add(ChatColor.GOLD + "You can whit this Item you change your Mode to Creative");
					CMmeta.setLore(CrM);
					CM.setItemMeta(CMmeta);
					
					ItemStack AM = new ItemStack(Material.DISPENSER);
					ItemMeta AMmeta = AM.getItemMeta();
					ArrayList<String> AdM = new ArrayList<String>();
					AMmeta.setDisplayName(ChatColor.DARK_AQUA + "Adventure Mode");
					AdM.add(ChatColor.YELLOW + "You can whit this Item you change your Mode to Adventure");
					AMmeta.setLore(AdM);
					AM.setItemMeta(AMmeta);
					
					ItemStack Day = new ItemStack(Material.DAYLIGHT_DETECTOR);
					ItemMeta Daymeta = Day.getItemMeta();
					ArrayList<String> Sun = new ArrayList<String>();
					Daymeta.setDisplayName(ChatColor.YELLOW + "Day");
					Sun.add(ChatColor.GRAY + "You make Day of the World");
					Daymeta.setLore(Sun);
					Day.setItemMeta(Daymeta);
					
					ItemStack Night = new ItemStack(Material.NETHER_BRICK);
					ItemMeta Nightmeta = Night.getItemMeta();
					ArrayList<String> Nacht = new ArrayList<String>();
					Nightmeta.setDisplayName(ChatColor.DARK_GRAY + "Night");
					Nacht.add(ChatColor.GRAY + "You make Night of the World");
					Nightmeta.setLore(Nacht);
					Night.setItemMeta(Nightmeta);
					
					//Weather
					ItemStack WeatherClear = new ItemStack(Material.WATER_BUCKET);
					ItemMeta WeatherClearmeta= WeatherClear.getItemMeta();
					ArrayList<String> Weather = new ArrayList<String>();
					WeatherClearmeta.setDisplayName(ChatColor.GREEN + "Toggledownfall on");
					Weather.add(ChatColor.DARK_PURPLE + "Weather on");
					WeatherClearmeta.setLore(Weather);
					WeatherClear.setItemMeta(WeatherClearmeta);
					
					ItemStack ClearWeather = new ItemStack(Material.BLAZE_POWDER);
					ItemMeta ClearWeathermeta = ClearWeather.getItemMeta();
					ArrayList<String> Weather1 = new ArrayList<String>();
					ClearWeathermeta.setDisplayName(ChatColor.DARK_GREEN + "Toggledownfall off");
					Weather1.add(ChatColor.DARK_PURPLE + "Weather off");
					ClearWeathermeta.setLore(Weather1);
					ClearWeather.setItemMeta(ClearWeathermeta);
					
					//Difficult <Peaceful/Easy/Normal/Hard>
					ItemStack Peaceful = new ItemStack(Material.APPLE);
					ItemMeta Peacefulmeta = Peaceful.getItemMeta();
					ArrayList<String> P = new ArrayList<String>();
					Peacefulmeta.setDisplayName(ChatColor.YELLOW + "Peaceful ");
					P.add(ChatColor.AQUA + "Change to Peaceful Mode");
					Peacefulmeta.setLore(P);
					Peaceful.setItemMeta(Peacefulmeta);
					
					ItemStack Easy = new ItemStack(Material.ARROW);
					ItemMeta Easymeta = Easy.getItemMeta();
					ArrayList<String> E = new ArrayList<String>();
					Easymeta.setDisplayName(ChatColor.GOLD + "Easy ");
					E.add(ChatColor.AQUA + "Change to Easy Mode");
					Easymeta.setLore(E);
					Easy.setItemMeta(Easymeta);
					
					ItemStack Normal = new ItemStack(Material.ENCHANTMENT_TABLE);
					ItemMeta Normalmeta = Normal.getItemMeta();
					ArrayList<String> N = new ArrayList<String>();
					Normalmeta.setDisplayName(ChatColor.GREEN + "Normal ");
					N.add(ChatColor.AQUA + "Change to Normal Mode");
					Normalmeta.setLore(N);
					Normal.setItemMeta(Normalmeta);
					
					ItemStack Hard = new ItemStack(Material.TNT);
					ItemMeta Hardmeta = Hard.getItemMeta();
					ArrayList<String> H = new ArrayList<String>();
					Hardmeta.setDisplayName(ChatColor.DARK_RED + "Hard ");
					H.add(ChatColor.AQUA + "Change to Hard Mode");
					Hardmeta.setLore(H);
					Hard.setItemMeta(Hardmeta);
					
					//Vanish
					ItemStack Vanish = new ItemStack(Material.SLIME_BALL);
					ItemMeta Vanishmeta = Vanish.getItemMeta();
					ArrayList<String> V = new  ArrayList<String>();
					Vanishmeta.setDisplayName(ChatColor.DARK_PURPLE + "Vanish <on/off>");
					V.add(ChatColor.DARK_AQUA + "You can change to Vanish and Spy the Player");
					Vanishmeta.setLore(V);
					Vanish.setItemMeta(Vanishmeta);
					
					//Clear Inventory
					ItemStack Clear = new ItemStack(Material.CACTUS);
					ItemMeta Clearmeta = Clear.getItemMeta();
					ArrayList<String> CI = new ArrayList<String>();
					Clearmeta.setDisplayName(ChatColor.DARK_GRAY + "Clear Inventory");
					CI.add("You can clear Inventory");
					Clearmeta.setLore(CI);
					Clear.setItemMeta(Clearmeta);
					
					
					//Inventory Line 1
					inv1.setItem(0, Heal);
					inv1.setItem(3, SM);
					inv1.setItem(4, CM);
					inv1.setItem(5, AM);
					inv1.setItem(8, Fly);							
					
					//Inventory Line 2
					
					
					//Inventory Line 3
					inv1.setItem(18, Day);
					inv1.setItem(19, Night);
					inv1.setItem(23, Peaceful);
					inv1.setItem(24, Easy);
					inv1.setItem(25, Normal);
					inv1.setItem(26, Hard);
					
					//Inventory Line 4
					inv1.setItem(27, WeatherClear);
					inv1.setItem(28, ClearWeather);
					inv1.setItem(35, Clear);
					
					//Inventory Line 5
					
					inv1.setItem(40, Vanish);

					p.getPlayer().openInventory(inv1);
					return true;
				}
			}
		return false;
	}
}
