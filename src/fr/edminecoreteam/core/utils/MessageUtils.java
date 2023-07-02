package fr.edminecoreteam.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import fr.edminecoreteam.core.Core;

public class MessageUtils {
	
	private static Core core = Core.getInstance();
	
	public static void messageStarting(Player p) {
		p.sendMessage("");
		p.sendMessage(" §7Bienvenue dans le §6§lDé à coudre §7!");
		p.sendMessage(" §c§lInformations:");
		p.sendMessage(" §7• §fL'objectif est de §asurvivre");
		p.sendMessage(" §7• §fle plus longtemps §fpossible !");
		p.sendMessage("");
		p.sendMessage(" §d§lAstuces:");
		p.sendMessage(" §7• §fGardez toujours un §9œil");
		p.sendMessage(" §7• §fsur la §bposition §fdu dé.");
		p.sendMessage(" §7• §fSoyez prêt à §elancer§f.");
		p.sendMessage(" §7• §fUtilisez la touche §9sneak");
		p.sendMessage(" §7• §fpour §désactiver§f les déplacements brusques.");
		p.sendMessage("");
	}
	
	public static void deathMessage(Player victim)
	{
		Random rand = new Random();
        int min = 1;
        int max = 5;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (randomNum == 1)
        {
        	core.getServer().broadcastMessage("§c§l" + victim.getName() + " §fa raté son saut de chat.");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_DEATH, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 2)
        {
        	core.getServer().broadcastMessage("§fLa cape de §c§l" + victim.getName() + " §fn'a pas suffi.");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_DEATH, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 3)
        {
        	core.getServer().broadcastMessage("§c§l" + victim.getName() + " §fa fait un pas de trop vers le vide.");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_DEATH, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 4)
        {
        	core.getServer().broadcastMessage("§fLe sol était plus dur que prévu pour §c§l" + victim.getName() + "§f...");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_DEATH, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 5)
        {
        	core.getServer().broadcastMessage("§c§l" + victim.getName() + " §fa découvert qu'il n'était définitivement pas un oiseau.");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_DEATH, 1.0f, 1.0f);
        	}
        }
	}
	
	public static void pointsMessage(Player player)
	{
		Random rand = new Random();
        int min = 1;
        int max = 5;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (randomNum == 1)
        {
        	core.getServer().broadcastMessage("§a§l" + player.getName() + " §ffait des trous dans le tissu !");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 2)
        {
        	core.getServer().broadcastMessage("§fLa chance est de son côté, §a§lMamie " + player.getName() + " §fmarque un point !");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 3)
        {
        	core.getServer().broadcastMessage("§a§l" + player.getName() + " §fprouve que le dé à coudre n'est pas seulement pour les mamies !");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 4)
        {
        	core.getServer().broadcastMessage("§fLe dé à coudre roule parfaitement pour §a§l" + player.getName() + "§f !");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
        	}
        }
        if (randomNum == 5)
        {
        	core.getServer().broadcastMessage("§a§l" + player.getName() + " §fa envoie le dé à coudre dans les airs et ajoute un point à leur score, impressionnant !");
        	for (Player p : Bukkit.getOnlinePlayers())
        	{
        		p.playSound(p.getLocation(), Sound.VILLAGER_YES, 1.0f, 1.0f);
        	}
        }
	}
	
	public static void messageEnd(Player p) {
		
		List<Player> mostcoins = new ArrayList<Player>();
		for (Player pls : core.getPlayers())
		{
			mostcoins.add(pls);
		}
		Player mostPoints = core.getPlayerWithMostPoints(mostcoins);
		
		p.sendMessage("");
		p.sendMessage("  §d§lCompte rendu:");
		p.sendMessage("   §7• §7Vitoire: " + mostPoints.getName());
		p.sendMessage("");
		p.sendMessage(" §8➡ §fVisionnez vos statistiques sur votre profil.");
		p.sendMessage("");
	}
	

}
