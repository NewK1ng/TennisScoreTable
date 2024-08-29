package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Match;
import service.MatchScoreCalculationService;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        req.setAttribute("uuid", uuid);
        req.setAttribute("match", match);

        RequestDispatcher dispatcher = req.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int playerIndex = Integer.parseInt(req.getParameter("playerIndex"));

        System.out.println("Player " + playerIndex + " won point!");

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Match match = OngoingMatchesService.getMatch(uuid);

        MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

        matchScoreCalculationService.calculateMatchScore(playerIndex, match);

        req.setAttribute("uuid", uuid);
        req.setAttribute("match", match);

        RequestDispatcher dispatcher = req.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(req, resp);


    }
}

