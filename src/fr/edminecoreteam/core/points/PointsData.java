package fr.edminecoreteam.core.points;

import java.util.UUID;

public class PointsData 
{
	private final UUID playerId;
    private int points;

    public PointsData(UUID playerId, int points) {
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
