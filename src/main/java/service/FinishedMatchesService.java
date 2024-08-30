package service;

import dao.MatchDAO;
import model.Match;

public class FinishedMatchesService {

    private final MatchDAO matchDAO = new MatchDAO();

    public void save(Match match) throws Exception {
        matchDAO.save(match);
    }

}
