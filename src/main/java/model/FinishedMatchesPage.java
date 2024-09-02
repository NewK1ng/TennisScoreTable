package model;

import java.util.List;

public class FinishedMatchesPage {

    private final List<Match> matchesList;
    private final Long numberOfMatches;
    private final Long totalPages;
    private final int pageNumber;
    private final int pageSize;
    private final int pageElementStartIndex;

    public FinishedMatchesPage(List<Match> matchesList, Long matchesCount, int pageNumber, Long totalPages, int pageSize, int pageElementStartIndex) {
        this.matchesList = matchesList;
        this.numberOfMatches = matchesCount;
        this.pageNumber = pageNumber;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
        this.pageElementStartIndex = pageElementStartIndex;
    }

    public List<Match> getMatchesList() {
        return matchesList;
    }

    public Long getNumberOfMatches() {
        return numberOfMatches;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageElementStartIndex() {
        return pageElementStartIndex;
    }
}
