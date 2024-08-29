package service;

import lombok.Getter;
import lombok.Setter;
import model.Match;
import model.MatchScore;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class OngoingMatchesService {

    private static final ConcurrentHashMap<UUID, Match> ongoingMatches = new ConcurrentHashMap<>();

    private OngoingMatchesService() {
    }

    public static UUID addMatch(Match match) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, match);
        return uuid;
    }

    public static Match getMatch(UUID id) {
        return ongoingMatches.get(id);
    }

}
