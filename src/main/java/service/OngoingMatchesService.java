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

    public static Match getMatch(UUID id) throws Exception {
        Match match = ongoingMatches.get(id);

        if (match == null) {
            throw new Exception("Match with uuid - " + id + " was not found");
        } else return match;

    }

    public static void removeMatch(UUID id) throws Exception {
        Match removedMatch = ongoingMatches.remove(id);

        if (removedMatch == null) {
            throw new Exception("Match with uuid - " + id + " was not found and cannot be removed");
        }
    }
}
