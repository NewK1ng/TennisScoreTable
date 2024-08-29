package model;


public class MatchScore {

    private int[] points;
    private int[] games;
    private int[] sets;
    private boolean isEqualPoints;


    public MatchScore() {
        points = new int[] {0,0};
        games = new int[] {0,0};
        sets = new int[] {0,0};
    }

    public void resetPoints() {
        points[0] = 0;
        points[1] = 0;
    }

    public int getPlayerPoints(int playerIndex) {
        return points[playerIndex-1];
    }

    public void addPlayerPoints(int playerIndex , int points) {
        this.points[playerIndex-1] += points;
    }

    public int getPlayerGames(int playerIndex) {
        return games[playerIndex-1];
    }

    public void addPlayerGames(int playerIndex) {
        this.games[playerIndex-1] += 1;
    }

    public int getPlayerSets(int playerIndex) {
        return sets[playerIndex-1];
    }

    public void addPlayerSets(int playerIndex) {
        this.sets[playerIndex-1] += 1;
    }

    public boolean isEqualPoints() {
        return isEqualPoints;
    }

    public void setEqualPoints(boolean equalPoints) {
        isEqualPoints = equalPoints;
    }
}
