package service;

import dao.PlayerDAO;
import model.Match;
import model.Player;

import java.util.Optional;

public class NewMatchService {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public Match createNewMatch(String playerOneName, String playerTwoName) throws Exception {

        Player playerOne;
        Player playerTwo;

        Optional<Player> playerOneOpt = playerDAO.findByName(playerOneName);
        Optional<Player> playerTwoOpt = playerDAO.findByName(playerTwoName);

       if (playerOneOpt.isPresent()) {
           playerOne = playerOneOpt.get();
       } else {
           playerOne = new Player(playerOneName);
           playerDAO.save(playerOne);
       }

        if (playerTwoOpt.isPresent()) {
            playerTwo = playerTwoOpt.get();
        } else {
            playerTwo = new Player(playerTwoName);
            playerDAO.save(playerTwo);
        }

        return new Match(playerOne,playerTwo);
    }

}
