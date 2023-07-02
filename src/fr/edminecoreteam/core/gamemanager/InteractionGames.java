package fr.edminecoreteam.core.gamemanager;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.edminecoreteam.core.Core;
import fr.edminecoreteam.core.State;
import fr.edminecoreteam.core.data.Data;
import fr.edminecoreteam.core.listeners.Instance;
import fr.edminecoreteam.core.utils.MessageUtils;

public class InteractionGames implements Listener 
{
	private static Core core = Core.getInstance();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
	    Player player = event.getPlayer();
	    Location to = event.getTo();
	    if (to.getBlock().getType() == Material.WATER || to.getBlock().getType() == Material.STATIONARY_WATER) {
	    	if (core.isState(State.INGAME)) 
            {
		    	if (core.getInJump().contains(player.getName()))
		    	{
		    		Block waterBlock = to.getBlock();
		    		Material material = core.blockManager.getPlayerBlock(player);
		    		player.setGameMode(GameMode.SPECTATOR);
		    		if (material != null) {
		    		    waterBlock.setType(material);
		    		} else {
		    		    waterBlock.setType(Material.BEDROCK);
		    		}
			        Instance.respawnInGame(player);
			        Instance.checkTours();
			        Instance.addPoint(player);
			        MessageUtils.pointsMessage(player);
			        core.addBlocs(player, 1);
		    	}
            }
	    }
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntityType() != EntityType.PLAYER) { return; }
    	if(e.getEntity() instanceof Player) {
    		Player p = (Player)e.getEntity();
            if (core.isState(State.INGAME)) 
            {
                e.setCancelled(true);
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) 
                {
                	if (core.getInJump().contains(p.getName()))
                	{
                		Instance.killPlayer(p);
                		core.getInJump().remove(p.getName());
                		Instance.checkToursButIsDead();
                		Data data = new Data(p.getName());
                		data.addDefaites(1);
                		data.addPlayedGames(1);
                		data.addBlocsPlaces(core.getBlocsPlace(p));
                	}
                }
            }
    	}
	}
}
