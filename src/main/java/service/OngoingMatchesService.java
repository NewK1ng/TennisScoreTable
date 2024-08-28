package service;

import lombok.Getter;
import lombok.Setter;
import model.MatchScore;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class OngoingMatchesService {

    private static final ConcurrentHashMap<UUID, MatchScore> ongoingMatches = new ConcurrentHashMap<>();

    private OngoingMatchesService() {
    }

    public static UUID addMatch(MatchScore matchScore) {
        UUID uuid = UUID.randomUUID();
        ongoingMatches.put(uuid, matchScore);
        return uuid;
    }

    public static MatchScore getMatch(UUID id) {
        return ongoingMatches.get(id);
    }

}
