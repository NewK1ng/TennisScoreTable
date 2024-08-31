package service;

import model.Match;
import model.MatchScore;
import model.Player;

public class MatchScoreCalculationService {

    private final MatchScore matchScore;
    private final int playerIndex;

    public MatchScoreCalculationService(int playerIndex, MatchScore matchScore) {
        this.matchScore = matchScore;
        this.playerIndex = playerIndex;
    }

    public void calculateMatchScore() {
        if (!matchScore.isTieBrake()) {
            calculateRegularMatchScore();
        } else {
            calculateTieBreakScore();
        }
    }

    private void calculateRegularMatchScore() {
        if (!matchScore.isEqualPoints()) {
            switch (matchScore.getPlayerPoints(playerIndex)) {
                case 0, 15:
                    matchScore.addPlayerPoints(playerIndex, 15);
                    break;
                case 30:
                    matchScore.addPlayerPoints(playerIndex, 10);
                    break;
                case 40:
                    fortyPointsCalculation();
                    break;
            }
        } else {
            equalPointsCalculation();
        }
    }

    private void fortyPointsCalculation() {
        int player1Points = matchScore.getPlayerPoints(1);
        int player2Points = matchScore.getPlayerPoints(2);

        if (player1Points == player2Points) {
            matchScore.setEqualPoints(true);
            equalPointsCalculation();
        } else {
            addGame();
        }
    }

    private void equalPointsCalculation() {
        matchScore.addPlayerPoints(playerIndex, 1);

        int player1Points = matchScore.getPlayerPoints(1);
        int player2Points = matchScore.getPlayerPoints(2);

        if (calculateAdvantage(player1Points, player2Points) > 1) {
            matchScore.setEqualPoints(false);
            addGame();
        }
    }

    private int calculateAdvantage(int firstNum, int secondNum) {
        return Math.abs(firstNum - secondNum);
    }

    private void addGame() {
        matchScore.resetPoints();
        matchScore.addPlayerGames(playerIndex);

        switch (matchScore.getPlayerGames(playerIndex)) {
            case 6:

                int player1Games = matchScore.getPlayerGames(1);
                int player2Games = matchScore.getPlayerGames(2);

                if (calculateAdvantage(player1Games, player2Games) > 1) {
                    addSet();
                } else if (player1Games == player2Games) {
                    matchScore.resetPoints();
                    matchScore.setTieBrake(true);
                }
                break;
            case 7:
                addSet();

        }
    }

    private void calculateTieBreakScore() {
        matchScore.addPlayerPoints(playerIndex, 1);

        if (matchScore.getPlayerPoints(playerIndex) == 7) {
            checkTieBreakWinner();
        } else if (matchScore.getPlayerPoints(playerIndex) > 7) {
            checkTieBreakWinner();
        }
    }

    private void checkTieBreakWinner() {

        int player1Points = matchScore.getPlayerPoints(1);
        int player2Points = matchScore.getPlayerPoints(2);

        if (calculateAdvantage(player1Points, player2Points) > 1) {
            matchScore.setTieBrake(false);
            addSet();
        }

    }

    private void addSet() {

        matchScore.resetPoints();
        matchScore.resetGames();
        matchScore.addPlayerSets(playerIndex);

        if (matchScore.getPlayerSets(playerIndex) > 1) {

            matchScore.setMatchFinished(true);
        }
    }

    public Player getWinner(Match match) {
        if (playerIndex == 1) {
            return match.getPlayer1();
        } else {
            return match.getPlayer2();
        }
    }


}
