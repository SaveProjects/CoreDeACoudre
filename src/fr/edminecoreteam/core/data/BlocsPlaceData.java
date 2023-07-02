package fr.edminecoreteam.core.data;

import java.util.UUID;

public class BlocsPlaceData
{
	private final UUID playerId;
    private int points;

    public BlocsPlaceData(UUID playerId, int points) {
        this.playerId = playerId;
        this.points = points;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int amount) {
    	points += amount;
    }

    public void removePoints(int amount) {
    	points -= amount;
    }
}
