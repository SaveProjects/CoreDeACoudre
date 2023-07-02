package fr.edminecoreteam.core.listeners;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.core.Core;
import fr.edminecoreteam.core.State;
import fr.edminecoreteam.core.data.Data;
import fr.edminecoreteam.core.purchase.StoreData;
import fr.edminecoreteam.core.utils.ChangeHubInfo;
import fr.edminecoreteam.core.utils.ItemStackSerializer;
import fr.edminecoreteam.core.utils.MessageUtils;

public class Instance implements Listener
{
	
	private static Core core = Core.getInstance();
	
    public static void joinWAITING(final Player p) {
        Location spawn = new Location(Bukkit.getWorld("hub"), 2.5, 31.0, 0.5, -90.5f, -4.0f);
        PlayerInventory pi = p.getInventory();
        pi.setHelmet(null);
        pi.setChestplate(null);
        pi.setLeggings(null);
        pi.setBoots(null);
        p.setAllowFlight(false);
		p.setFlying(false);
        
        p.getInventory().clear();
        p.setLevel(0);
        p.setFoodLevel(20);
        p.setHealth(20.0);
        p.setGameMode(GameMode.ADVENTURE);
        p.teleport(spawn);
        ItemJoinListener.joinItem(p);
        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 0.5f);
    }
    
    @SuppressWarnings("deprecation")
	public static void joinInGame(Player p) {
    	Location spawn = new Location(Bukkit.getWorld("game"), 
				core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.x")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.y")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.z")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.f")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.t"));
        
    	if (!core.getPlayersName().contains(p.getName()))
    	{
    		PlayerInventory pi = p.getInventory();
            pi.setHelmet(null);
            pi.setChestplate(null);
            pi.setLeggings(null);
            pi.setBoots(null);
            p.setAllowFlight(false);
    		p.setFlying(false);
            
            p.getInventory().clear();
            p.setLevel(0);
            p.setFoodLevel(20);
            p.setHealth(20.0);
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(spawn);
            ItemJoinListener.spectacleItem(p);
            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 0.5f);
    	}
    	else
    	{
    		p.sendTitle("§e§lBon Retour !", "§7Vous nous avez manquez...");
    	}
    }
    
	@SuppressWarnings("null")
	public static void defineBloc(Player p)
    {
		Data data = new Data(p.getName());
		StoreData sData = new StoreData(data.getBlocSelect());
		ItemStack itemStack = ItemStackSerializer.deserialize(sData.getArticleItem());
		
		if (data.getBlocSelect() != 0)
		{
			core.removeBlocSelect(p);
			core.addBlocSelect(p, data.getBlocSelect());
			Material material = itemStack.getType();
			core.blockManager.assignBlock(p, material);
		}
    }
    
	public static void startGame() 
	{
    	for (Player pS : core.getPlayers())
    	{
    		core.getIsLiving().add(pS.getName());
    		if (core.getPlayers().get(0) == pS)
    		{
    			setSauteur(pS);
    		}
    		else
    		{
    			setWait(pS);
    		}
    	}
    }
    
    public static void respawnInGame(Player p) {
    	if (core.getInJump().contains(p.getName()))
    	{
    		setWait(p);
    	}
    }
    
    public static void addPoint(Player p) 
    {
    	if (!core.getEasyJump().contains(p.getName()) && !core.getNormalJump().contains(p.getName()) && !core.getHardJump().contains(p.getName()))
    	{
    		core.addPoints(p, 5);
    	}
    	else if (core.getEasyJump().contains(p.getName()))
    	{
    		core.addPoints(p, 5);
    	}
    	else if (core.getNormalJump().contains(p.getName()))
    	{
    		core.addPoints(p, 10);
    	}
    	else if (core.getHardJump().contains(p.getName()))
    	{
    		core.addPoints(p, 20);
    	}
    }
    
    public static void checkTours() {
    	for (String s : core.getInJump())
		{
			core.getInJump().remove(s);
		}
		for (String s : core.getWaitForJump())
		{
			Player p = Bukkit.getPlayer(s);
			if (core.getWaitForJump().get(0) == p.getName())
			{
				setSauteur(p);
				return;
			}
		}
	}
    
    public static void checkToursButIsDead() {
		for (String s : core.getWaitForJump())
		{
			Player p = Bukkit.getPlayer(s);
			if (core.getWaitForJump().get(0) == p.getName())
			{
				setSauteur(p);
				return;
			}
		}
	}
    
    @SuppressWarnings("deprecation")
	public static void setSauteur(Player p)
    {
    	p.getInventory().clear();
    	Location easy = new Location(Bukkit.getWorld("game"), 
				core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".easy.x")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".easy.y")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".easy.z")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".easy.f")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".easy.t"));
    	Location normal = new Location(Bukkit.getWorld("game"), 
				core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".normal.x")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".normal.y")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".normal.z")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".normal.f")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".normal.t"));
    	Location hard = new Location(Bukkit.getWorld("game"), 
				core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".hard.x")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".hard.y")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".hard.z")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".hard.f")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".hard.t"));
    	
    	p.setGameMode(GameMode.ADVENTURE);
    	if (core.getWaitForJump().contains(p.getName()))
    	{
    		core.getWaitForJump().remove(p.getName());
    	}
    	core.getInJump().add(p.getName());
    	if (!core.getEasyJump().contains(p.getName()) && !core.getNormalJump().contains(p.getName()) && !core.getHardJump().contains(p.getName()))
    	{
    		p.teleport(easy);
    	}
    	else if (core.getEasyJump().contains(p.getName()))
    	{
    		p.teleport(easy);
    	}
    	else if (core.getNormalJump().contains(p.getName()))
    	{
    		p.teleport(normal);
    	}
    	else if (core.getHardJump().contains(p.getName()))
    	{
    		p.teleport(hard);
    	}
    	p.sendTitle("§a§lC'est votre tour !", "§7Bonne chance !");
    	ItemJoinListener.jumpItem(p);
    	core.timerJump = 15;
    	new BukkitRunnable() {
            int t = core.timerJump;
                
			public void run() {
				if (core.isState(State.INGAME))
				{
					if (core.getInJump().contains(p.getName()))
		        	{
		        		core.timerJump(t);
		        		p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0f, 1.0f);
		        		if (t == 0)
		        		{
		        			killPlayer(p);
	                		core.getInJump().remove(p.getName());
	                		checkToursButIsDead();
		        		}
		        	}
		        	else
		        	{
		        		cancel();
		        	}
				}
				else
				{
					cancel();
				}
	        	--t;
            }
        }.runTaskTimer((Plugin)core, 0L, 20L);
    }
    
    public static void setWait(Player p)
    {
    	p.getInventory().clear();
    	Location spec = new Location(Bukkit.getWorld("game"), 
				core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.x")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.y")
				, core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.z")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.f")
				, (float) core.getConfig().getDouble("Cartes." + core.getGameWorldName + ".specSpawn.t"));
    	p.setGameMode(GameMode.SPECTATOR);
    	p.getInventory().clear();
    	if (core.getInJump().contains(p.getName()))
    	{
    		core.getInJump().remove(p.getName());
    	}
    	core.getWaitForJump().add(p.getName());
    	p.teleport(spec);
    }
    
    @SuppressWarnings("deprecation")
	public static void killPlayer(Player p)
    {
    	core.getIsLiving().remove(p.getName());
    	core.getDeathPlayer().add(p.getName());
    	core.getSpecPlayer().add(p.getName());
    	core.getPlayers().remove(p);
    	p.setGameMode(GameMode.SPECTATOR);
    	p.sendTitle("§c§lVous êtes mort !", "§7Fin de la partie...");
    	ItemJoinListener.spectacleItem(p);
    	MessageUtils.deathMessage(p);
    }
    
    
    
    @SuppressWarnings("deprecation")
	public static void endGame(Player p, String winorlose) {
    	
    	if (winorlose == "win")
    	{
    		PlayerInventory pi = p.getInventory();
            pi.setHelmet(null);
            pi.setChestplate(null);
            pi.setLeggings(null);
            pi.setBoots(null);
    		pi.clear();
    		
    		Location spawn = new Location(Bukkit.getWorld("hub"), 2.5, 31.0, 0.5, -90.5f, -4.0f);
    		p.setGameMode(GameMode.ADVENTURE);
    		p.teleport(spawn);
    		p.setAllowFlight(true);
    		p.setFlying(true);
    		ItemJoinListener.endItem(p);
    		p.sendTitle("§e§lVictoire !", "§7Vous avez remporté la partie !");
    		Data data = new Data(p.getName());
    		data.addVictoires(1);
    		data.addPlayedGames(1);
    		data.addBlocsPlaces(core.getBlocsPlace(p));
    	}
    	
    	if (winorlose == "lose")
    	{
    		PlayerInventory pi = p.getInventory();
            pi.setHelmet(null);
            pi.setChestplate(null);
            pi.setLeggings(null);
            pi.setBoots(null);
            pi.clear();
            
            Location spawn = new Location(Bukkit.getWorld("hub"), 2.5, 31.0, 0.5, -90.5f, -4.0f);
            p.setGameMode(GameMode.ADVENTURE);
    		p.teleport(spawn);
    		p.setAllowFlight(true);
    		p.setFlying(true);
    		ItemJoinListener.endItem(p);
    		p.sendTitle("§c§lPerdu...", "§7Peut-être une prochaine fois !");
    	}
    }

	public static void quitWAITING(Player p) {
		// TODO Auto-generated method stub
		
	} 
	
	public static boolean checkWin()
	{
		if (core.getIsLiving().size() == 1)
		{
			return true;
		}
		return false;
	}
	
	public static boolean checkServersForStart()
	{
		String srvName = "DeACoudre";
		ChangeHubInfo servers = new ChangeHubInfo(srvName);
		for (String srvs : servers.getServer())
		{
			ChangeHubInfo finalservers = new ChangeHubInfo(srvs);
			if (finalservers.getServerMotd().equalsIgnoreCase("WAITING"))
			{
				return true;
			}
		}
		return false;
	}
    
}
