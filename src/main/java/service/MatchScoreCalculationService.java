package service;

import model.Match;
import model.MatchScore;

public class MatchScoreCalculationService {


    public void calculateMatchScore(int playerIndex, Match match) {

        MatchScore matchScore = match.getMatchScore();

        addPoint(playerIndex, matchScore);

    }

    private void addPoint(int playerIndex, MatchScore matchScore) {

        if (!matchScore.isEqualPoints()) {
            switch (matchScore.getPlayerPoints(playerIndex)) {

                case 0, 15:
                    matchScore.addPlayerPoints(playerIndex, 15);
                    break;
                case 30:
                    matchScore.addPlayerPoints(playerIndex, 10);
                    break;
                case 40:
                    if (isPointsEqual(matchScore)) {
                        matchScore.setEqualPoints(true);
                        matchScore.addPlayerPoints(playerIndex, 1);
                    } else {
                        matchScore.resetPoints();
                        addGames(playerIndex, matchScore);
                    }
                    break;
            }
        } else {
            matchScore.addPlayerPoints(playerIndex, 1);
            if (isTwoPointsAdvantage(matchScore)) {
                matchScore.setEqualPoints(false);
                matchScore.resetPoints();
                addGames(playerIndex, matchScore);
            }
        }
    }

    private void addGames(int playerIndex, MatchScore matchScore) {
        matchScore.addPlayerGames(playerIndex);
    }

    private boolean isPointsEqual(MatchScore matchScore) {
        return matchScore.getPlayerPoints(1) == matchScore.getPlayerPoints(2);
    }

    private boolean isTwoPointsAdvantage(MatchScore matchScore) {
        int absoluteDifference = Math.abs(matchScore.getPlayerPoints(1) - matchScore.getPlayerPoints(2));
        return absoluteDifference == 2;
    }

    private void addGame() {

    }

    private void addSet() {

    }

}
