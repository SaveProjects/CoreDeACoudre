package fr.edminecoreteam.core.gamemanager.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.core.Core;

public class Difficulty implements Listener
{
	private static Core core = Core.getInstance();
	
	@EventHandler
    public void onDrop(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType() == Material.EYE_OF_ENDER)
        {
        	if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDifficulté §7• Clique"))
        	{
        		e.setCancelled(true);
        	}
        }
    }
	
	@EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        ItemStack it = e.getCurrentItem();
        if (it == null) {
            return;
        }
        if (it.getType() == Material.EYE_OF_ENDER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDifficulté §7• Clique")) { e.setCancelled(true); }
        if (e.getView().getTopInventory().getTitle().equals("§8Difficulté")) {
        	if (it.getType() == Material.STAINED_GLASS_PANE) { e.setCancelled(true); }
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
        	
        	if (it.getType() == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aFacile")) 
        	{
        		e.setCancelled(true);
        		p.closeInventory();
        		core.getEasyJump().remove(p.getName());
        		core.getNormalJump().remove(p.getName());
        		core.getHardJump().remove(p.getName());
        		
        		core.getEasyJump().add(p.getName());
        		p.teleport(easy);
        	}
        	if (it.getType() == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Normal")) 
        	{
        		e.setCancelled(true);
        		p.closeInventory();
        		core.getEasyJump().remove(p.getName());
        		core.getNormalJump().remove(p.getName());
        		core.getHardJump().remove(p.getName());
        		
        		core.getNormalJump().add(p.getName());
        		p.teleport(normal);
        	}
        	if (it.getType() == Material.INK_SACK && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cDifficile")) 
        	{
        		e.setCancelled(true);
        		p.closeInventory();
        		core.getEasyJump().remove(p.getName());
        		core.getNormalJump().remove(p.getName());
        		core.getHardJump().remove(p.getName());
        		
        		core.getHardJump().add(p.getName());
        		p.teleport(hard);
        	}
        }
    }
	
	@EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        ItemStack it = e.getItem();
        if (it == null) {
            return;
        }
        if (it.getType() == Material.EYE_OF_ENDER && it.getItemMeta().hasDisplayName() 
        		&& it.getItemMeta().getDisplayName().equalsIgnoreCase("§6§lDifficulté §7• Clique") 
        		&& (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK || a == Action.LEFT_CLICK_AIR || a == Action.LEFT_CLICK_BLOCK)) {
            e.setCancelled(true);
            gui(p);
        } 
    }
	
	public static void gui(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 45, "§8Difficulté");
        
		new BukkitRunnable() {
            int t = 0;
                
	        public void run() {
	        	
	        	if (!p.getOpenInventory().getTitle().equalsIgnoreCase("§8Difficulté")) { cancel(); }
		            	ItemStack deco = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		                ItemMeta decoM = deco.getItemMeta();
		                decoM.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		                decoM.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		                decoM.setDisplayName("§r");
		                deco.setItemMeta(decoM);
		                inv.setItem(0, deco); inv.setItem(8, deco); inv.setItem(9, deco); inv.setItem(17, deco);
		                inv.setItem(27, deco); inv.setItem(35, deco); inv.setItem(36, deco); inv.setItem(44, deco);
	        		
                ++t;
                if (t == 10) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)core, 0L, 10L);
        
        
        ItemStack Facile = new ItemStack(Material.INK_SACK, 1, (short)10);
        ItemMeta FacileM = Facile.getItemMeta();
        FacileM.setDisplayName("§aFacile");
        ArrayList<String> loreFacile = new ArrayList<String>();
        loreFacile.add("");
        loreFacile.add("§8➡ §fCliquez pour y accéder.");
        FacileM.setLore(loreFacile);
        Facile.setItemMeta(FacileM);
        inv.setItem(20, Facile);
        
        ItemStack Normal = new ItemStack(Material.INK_SACK, 1, (short)14);
        ItemMeta NormalM = Normal.getItemMeta();
        NormalM.setDisplayName("§6Normal");
        ArrayList<String> loreNormal = new ArrayList<String>();
        loreNormal.add("");
        loreNormal.add("§8➡ §fCliquez pour y accéder.");
        NormalM.setLore(loreNormal);
        Normal.setItemMeta(NormalM);
        inv.setItem(22, Normal);
        
        ItemStack Difficile = new ItemStack(Material.INK_SACK, 1, (short)1);
        ItemMeta DifficileM = Difficile.getItemMeta();
        DifficileM.setDisplayName("§cDifficile");
        ArrayList<String> loreDifficile = new ArrayList<String>();
        loreDifficile.add("");
        loreDifficile.add("§8➡ §fCliquez pour y accéder.");
        DifficileM.setLore(loreDifficile);
        Difficile.setItemMeta(DifficileM);
        inv.setItem(24, Difficile);
		
        
        p.openInventory(inv);
        p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 1.0f, 1.0f);
	}
}
