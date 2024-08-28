package model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MatchScore {

    private Match match;

    private int player1Sets;
    private int player2Sets;

    private int player1Games;
    private int player2Games;

    private int player1Points;
    private int player2Points;

    public MatchScore(Match match) {
        this.match = match;
    }
}
