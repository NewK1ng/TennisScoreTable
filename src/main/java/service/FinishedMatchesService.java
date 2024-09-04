package service;

import dao.MatchDAO;
import model.FinishedMatchesPage;
import model.Match;

import java.util.List;

public class FinishedMatchesService {

    private final MatchDAO matchDAO = new MatchDAO();

    public void save(Match match) throws Exception {
        matchDAO.save(match);
    }

    public FinishedMatchesPage getFinishedMatches(int pageNumber, int pageSize, String playerName) throws Exception {
        int offset = (pageNumber - 1) * pageSize;
        int pageElementStartIndex = (pageSize * pageNumber) - pageSize;
        long numberOfMatches;
        long totalPages;
        List<Match> matchList;

        if (playerName == null) {
            numberOfMatches = matchDAO.findNumberOfMatches();
            totalPages = (numberOfMatches + pageSize - 1) / pageSize;
            matchList = matchDAO.findAllPaginated(offset, pageSize);
        } else {
            numberOfMatches = matchDAO.findNumberOfMatchesByPlayerName(playerName);
            totalPages = (numberOfMatches + pageSize - 1) / pageSize;
            matchList = matchDAO.findByPlayerNamePaginated(offset, pageSize, playerName);
        }

        return new FinishedMatchesPage(matchList, numberOfMatches, pageNumber, totalPages, pageSize, pageElementStartIndex, playerName);
    }


}
