package fr.edminecoreteam.core.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.edminecoreteam.core.Core;
import fr.edminecoreteam.core.State;
import fr.edminecoreteam.core.api.PlayerInfo;

import java.util.UUID;
 
/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class PersonalScoreboard {
	
	private static Core core = Core.getInstance();
    private Player player;
    private static int getLang(Player p) { UUID id = p.getUniqueId(); return PlayerInfo.getLang(id); }
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;
 
    PersonalScoreboard(Player player){
        this.player = player;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "Edmine");
 
        reloadData();
        objectiveSign.addReceiver(player);
    }
    
    public String convertTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02dm %02ds", minutes, seconds);
    }
    
 
    public void reloadData(){}
 
    public void setLines(String ip){
    	if (core.isState(State.WAITING)) 
    	{
    		if(getLang(player) == 0)
    		{
    			objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
    	       	 
                objectiveSign.setLine(0, "§1");
                objectiveSign.setLine(1, " §f➡ §dInformations:");
                    objectiveSign.setLine(2, "  §8• §7Statut: §fAttente...");
                    objectiveSign.setLine(3, "  §8• §7Joueurs: §a" + core.getPlayers().size() + "/§a8");
                    objectiveSign.setLine(4, "§2");
                    objectiveSign.setLine(5, "  §8• §7Carte: §e" + core.getGameWorldName.replace("_", " "));
                    objectiveSign.setLine(6, "  §8• §7Mode: §9SOLO");
                    objectiveSign.setLine(7, "§3");
                    objectiveSign.setLine(8, " §8➡ " + ip);
    			}
    			if(getLang(player) == 1)
    			{
    				objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
    	       		 
                    objectiveSign.setLine(0, "§1");
                    objectiveSign.setLine(1, " §f➡ §dInformations:");
                    objectiveSign.setLine(2, "  §8• §7State: §fWaiting...");
                    objectiveSign.setLine(3, "  §8• §7Players: §a" + core.getPlayers().size() + "/§a8");
                    objectiveSign.setLine(4, "§2");
                    objectiveSign.setLine(5, "  §8• §7Map: §e" + core.getGameWorldName.replace("_", " "));
                    objectiveSign.setLine(6, "  §8• §7Mode: §9SOLO");
                    objectiveSign.setLine(7, "§3");
                    objectiveSign.setLine(8, " §8➡ " + ip);
    			}
    			if(getLang(player) == 2)
    			{
    				objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
    	       		 
                    objectiveSign.setLine(0, "§1");
                    objectiveSign.setLine(1, " §f➡ §dInformación:");
                    objectiveSign.setLine(2, "  §8• §7Estado: §fEsperando...");
                    objectiveSign.setLine(3, "  §8• §7Jugadores: §a" + core.getPlayers().size() + "/§a8");
                    objectiveSign.setLine(4, "§2");
                    objectiveSign.setLine(5, "  §8• §7Carta: §e" + core.getGameWorldName.replace("_", " "));
                    objectiveSign.setLine(6, "  §8• §7Moda: §9SOLO");
                    objectiveSign.setLine(7, "§3");
                    objectiveSign.setLine(8, " §8➡ " + ip);
    			}
    			if(getLang(player) == 3)
    			{
    				objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
    	       		 
                    objectiveSign.setLine(0, "§1");
                    objectiveSign.setLine(1, " §f➡ §dInformation:");
                    objectiveSign.setLine(2, "  §8• §7Status: §fWarten...");
                    objectiveSign.setLine(3, "  §8• §7Spieler: §a" + core.getPlayers().size() + "/§a8");
                    objectiveSign.setLine(4, "§2");
                    objectiveSign.setLine(5, "  §8• §7Karte: §e" + core.getGameWorldName.replace("_", " "));
                    objectiveSign.setLine(6, "  §8• §7Mode: §9SOLO");
                    objectiveSign.setLine(7, "§3");
                    objectiveSign.setLine(8, " §8➡ " + ip);
    			}
    			
    		}
    		if (core.isState(State.STARTING)) 
    		{
    			objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
       		 
                objectiveSign.setLine(0, "§1");
                objectiveSign.setLine(1, " §f➡ §dInformations:");
                objectiveSign.setLine(2, "  §8• §7Statut: §eDémarrage...");
                objectiveSign.setLine(3, "  §8• §7Joueurs: §a" + core.getPlayers().size() + "/§a8");
                objectiveSign.setLine(4, "§2");
                objectiveSign.setLine(5, "  §8• §7Carte: §e" + core.getGameWorldName);
                objectiveSign.setLine(6, "  §8• §7Lancement du jeu: §a" + core.timerStart + "§as");
                objectiveSign.setLine(7, "  §8• §7Mode: §9SOLO");
                objectiveSign.setLine(8, "§3");
                objectiveSign.setLine(9, " §8➡ " + ip);
    		}
    		if (core.isState(State.INGAME)) 
    		{
    			if (core.getPlayersName().contains(player.getName()))
    			{
    				objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
       	       		 
                    objectiveSign.setLine(0, "§1");
                    objectiveSign.setLine(1, " §f➡ §dInformations:");
                    objectiveSign.setLine(2, "  §8• §7Points: §e" + core.getPoints(player) + "§e✸");
                    if (!core.getInJump().contains(player.getName()))
                    {
                     	objectiveSign.setLine(3, "  §8• §7Prochain Sauteur: §b" + core.getWaitForJump().get(0));
                        objectiveSign.setLine(4, "§2");
                      	objectiveSign.setLine(5, "  §8• §cPas votre tour...");
                    }
                    else
                    {
                       	objectiveSign.setLine(3, "  §8• §7Temps restant: §b" + core.timerJump);
                        objectiveSign.setLine(4, "§2");
                       	objectiveSign.setLine(5, "  §8• §aC'est votre tour !");
                    }
                    objectiveSign.setLine(6, "  §8• §7Joueurs en vie: §9" + core.getIsLiving().size());
                    objectiveSign.setLine(7, "§4");
                    objectiveSign.setLine(8, "  §8• §7Temps restants: §a" + convertTime(core.timerGame));
                    objectiveSign.setLine(9, "§3");
                    objectiveSign.setLine(10, " §8➡ " + ip);  
    			} else {
    				objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
      	       		 
                    objectiveSign.setLine(0, "§1");
                    objectiveSign.setLine(1, " §f➡ §dInformations:");
                    objectiveSign.setLine(2, "  §8• §7Points: §e" + core.getPoints(player) + "§e✸");
                    objectiveSign.setLine(3, "§4");
                    objectiveSign.setLine(4, "  §8• §7Temps restants: §a" + convertTime(core.timerGame));
                    objectiveSign.setLine(5, "§3");
                    objectiveSign.setLine(6, " §8➡ " + ip);
    			}
    			
    		}
    		if (core.isState(State.FINISH))
    		{
    			objectiveSign.setDisplayName("§8● §6§lDé À Coudre §8●");
 	       		 
                objectiveSign.setLine(0, "§1");
                objectiveSign.setLine(1, " §f➡ §dInformations:");
                objectiveSign.setLine(2, "  §8• §7Points: §e" + core.getPoints(player) + "§e✸");
                objectiveSign.setLine(3, "§4");
                objectiveSign.setLine(4, "  §8• §7Temps restants: §a" + core.timerEnd + "s");
                objectiveSign.setLine(5, "§3");
                objectiveSign.setLine(6, " §8➡ " + ip);
    		}
 
        objectiveSign.updateLines();
    }
 
    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}