package fr.edminecoreteam.core.gamemanager;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BlockManager 
{
	private HashMap<Player, Material> playerBlocks;
    
    public BlockManager() {
        playerBlocks = new HashMap<>();
    }
    
    public void assignBlock(Player player, Material block) {
        playerBlocks.put(player, block);
    }
    
    public void reuseBlock(Player player) {
    	Material block = playerBlocks.get(player);
        
    }
    
    public Material getPlayerBlock(Player player) {
        return playerBlocks.get(player);
    }
}
