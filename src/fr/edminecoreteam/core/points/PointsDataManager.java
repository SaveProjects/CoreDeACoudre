package fr.edminecoreteam.core.points;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PointsDataManager 
{
	private final Map<UUID, PointsData> players = new HashMap<>();

    public PointsData getPlayerData(UUID playerId) {
        return players.computeIfAbsent(playerId, id -> new PointsData(id, 0));
    }

    public void addPoints(UUID playerId, int amount) {
        getPlayerData(playerId).addPoints(amount);
    }

    public void removePoints(UUID playerId, int amount) {
        getPlayerData(playerId).removePoints(amount);
    }
    
    public Map<UUID, PointsData> returnPlayers()
    {
    	return this.players;
    }
}
