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

    public FinishedMatchesPage getFinishedMatches(int pageNumber, int pageSize) throws Exception {

        int offset = (pageNumber - 1) * pageSize;
        long numberOfMatches = matchDAO.findNumberOfMatches();
        long totalPages = (numberOfMatches + pageSize - 1) / pageSize;

        List<Match> matchList = matchDAO.findAllPaginated(offset, pageSize);

        return new FinishedMatchesPage(matchList, numberOfMatches, pageNumber, totalPages, pageSize);
    }


}
