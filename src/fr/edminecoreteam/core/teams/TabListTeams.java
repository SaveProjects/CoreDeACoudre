package fr.edminecoreteam.core.teams;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.edminecoreteam.core.Core;


public class TabListTeams implements Listener
{
	private static Core core = Core.getInstance();
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) 
	{
        Player p = e.getPlayer();
        refreshTablist(p);
    }
	
	private void refreshTablist(Player p) {
		
		new BukkitRunnable() {
            int t = 0;   
	        public void run() {
	        	
	        	if (!p.isOnline()) { cancel(); }
	        	
	        	if (core.getInJump().contains(p.getName()))
	        	{
	        		TeamsTagsManager.setNameTag(p, Teams.powerToTeam(1).getOrderTeam(), Teams.powerToTeam(1).getDisplayName(), Teams.powerToTeam(1).getSuffix());
	        	}
	        	else
	        	{
	        		TeamsTagsManager.setNameTag(p, Teams.powerToTeam(0).getOrderTeam(), Teams.powerToTeam(0).getDisplayName(), Teams.powerToTeam(0).getSuffix());
	        	}
	        	
		        
		        
		        ++t;
                if (t == 50) {
                    run();
                }
            }
        }.runTaskTimer((Plugin)core, 0L, 50L);

	}
}
