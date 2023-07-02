// 
// Decompiled by Procyon v0.5.36
// 

package fr.edminecoreteam.core.listeners;

import org.bukkit.inventory.meta.ItemMeta;

import fr.edminecoreteam.core.utils.SkullNBT;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;

public class ItemJoinListener implements Listener
{
	private static ItemStack getSkull(String url) {
		return SkullNBT.getSkull(url);
	}
	
    public static void joinItem(Player p) {
        ItemStack team = getSkull("http://textures.minecraft.net/texture/d4965fd69dd67203b46812f42319f43a24837ef3a2476ed0401ed3ff96cc33ab");
        ItemMeta teamM = team.getItemMeta();
        teamM.setDisplayName("§f§lChoix de votre bloc §7• Clique");
        team.setItemMeta(teamM);
        p.getInventory().setItem(0, team);
        
        ItemStack leave = new ItemStack(Material.BED, 1);
        ItemMeta leaveM = leave.getItemMeta();
        leaveM.setDisplayName("§c§lQuitter §7• Clique");
        leave.setItemMeta(leaveM);
        p.getInventory().setItem(8, leave);
    }
    
    public static void jumpItem(Player p) {
        ItemStack team = new ItemStack(Material.EYE_OF_ENDER, 1);
        ItemMeta teamM = team.getItemMeta();
        teamM.setDisplayName("§6§lDifficulté §7• Clique");
        team.setItemMeta(teamM);
        p.getInventory().setItem(4, team);
        
    }
    
    public static void spectacleItem(Player p) {
        ItemStack map = new ItemStack(Material.COMPASS, 1);
        ItemMeta mapM = map.getItemMeta();
        mapM.setDisplayName("§a§lJoueurs §7• Clique");
        map.setItemMeta(mapM);
        p.getInventory().setItem(0, map);
        
        ItemStack replay = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta replayM = replay.getItemMeta();
        replayM.setDisplayName("§d§lRejouer §7• Clique");
        replay.setItemMeta(replayM);
        p.getInventory().setItem(4, replay);
        
        ItemStack leave = new ItemStack(Material.BED, 1);
        ItemMeta leaveM = leave.getItemMeta();
        leaveM.setDisplayName("§c§lQuitter §7• Clique");
        leave.setItemMeta(leaveM);
        p.getInventory().setItem(8, leave);
    }
    
    public static void endItem(Player p) {
    	ItemStack replay = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta replayM = replay.getItemMeta();
        replayM.setDisplayName("§d§lRejouer §7• Clique");
        replay.setItemMeta(replayM);
        p.getInventory().setItem(0, replay);
    	
        ItemStack leave = new ItemStack(Material.BED, 1);
        ItemMeta leaveM = leave.getItemMeta();
        leaveM.setDisplayName("§c§lQuitter §7• Clique");
        leave.setItemMeta(leaveM);
        p.getInventory().setItem(8, leave);
    }
}
