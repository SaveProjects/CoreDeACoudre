package fr.edminecoreteam.core.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BlocsPlaceDataManager 
{
	private final Map<UUID, BlocsPlaceData> players = new HashMap<>();

    public BlocsPlaceData getPlayerData(UUID playerId) {
        return players.computeIfAbsent(playerId, id -> new BlocsPlaceData(id, 0));
    }

    public void addBlocs(UUID playerId, int amount) {
        getPlayerData(playerId).addPoints(amount);
    }

    public void removeBlocs(UUID playerId, int amount) {
        getPlayerData(playerId).removePoints(amount);
    }
    
    public Map<UUID, BlocsPlaceData> returnPlayers()
    {
    	return this.players;
    }
}
