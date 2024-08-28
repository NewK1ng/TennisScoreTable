package service;

import dao.PlayerDAO;
import model.Player;

import java.util.Optional;

public class PlayerService {

    private final PlayerDAO playerDAO = new PlayerDAO();

    public Player createPlayer(String name) throws Exception {

        Player player;

        try {
            player = playerDAO.create(name);
        } catch (Exception e) {

            try {
                Optional<Player> optionalPlayer = playerDAO.read(name);

                if (optionalPlayer.isPresent()) {
                    player = optionalPlayer.get();
                } else {
                    throw new Exception("Player with name " + name + " was not found");
                }
            } catch (Exception ex) {
                throw new Exception(e.getMessage());
            }

        }

        return player;
    }

}
