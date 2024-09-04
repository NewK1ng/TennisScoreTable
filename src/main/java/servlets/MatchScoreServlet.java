package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ErrorResponse;
import model.Match;
import service.FinishedMatchesService;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;
import util.ErrorHandler;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    private  ErrorHandler errorHandler;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ObjectMapper mapper = new ObjectMapper();
        errorHandler = new ErrorHandler(mapper);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uuidParam = req.getParameter("uuid");

        UUID uuid;

        if (uuidParam != null) {
            uuid = UUID.fromString(uuidParam);
        } else {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wrong value for uuid"));
            return;
        }

        Match match;

        try {
            match = OngoingMatchesService.getMatch(uuid);
        } catch (Exception e) {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_NOT_FOUND, e.getMessage()));
            return;
        }

        req.setAttribute("uuid", uuid);
        req.setAttribute("match", match);

        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String playerIndexParam = req.getParameter("playerIndex");
        String uuidParam = req.getParameter("uuid");

        int playerIndex;

        try {
            if (playerIndexParam != null && !playerIndexParam.isBlank()) {
                playerIndex = Integer.parseInt(playerIndexParam);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wrong value for player index"));
            return;
        }

        UUID uuid;

        try {
            if (uuidParam != null && !uuidParam.isBlank()) {
                uuid = UUID.fromString(uuidParam);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wrong value for uuid"));
            return;
        }

        Match match;

        try {
            match = OngoingMatchesService.getMatch(uuid);
        } catch (Exception e) {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_NOT_FOUND, e.getMessage()));
            return;
        }

        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(playerIndex, match.getMatchScore());
        matchScoreCalculationService.calculateMatchScore();

        req.setAttribute("uuid", uuid);
        req.setAttribute("match", match);

        if (matchScoreCalculationService.isMatchFinished(match)) {
            try {
                OngoingMatchesService.removeMatch(uuid);
            } catch (Exception e) {
                errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_NOT_FOUND, e.getMessage()));
                return;
            }

            try {
                new FinishedMatchesService().save(match);
            } catch (Exception e) {
                errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
                return;
            }

            req.getRequestDispatcher("finished-score.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("match-score.jsp").forward(req, resp);
        }
    }
}

