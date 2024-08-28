package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MatchScore;
import service.OngoingMatchesService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);

        req.setAttribute("uuid", uuid);
        req.setAttribute("matchScoreModel", matchScore);


        RequestDispatcher dispatcher = req.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long playerId = Long.parseLong(req.getParameter("playerId"));

        System.out.println("Player " + playerId + " won point!");

        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = OngoingMatchesService.getMatch(uuid);

        if (playerId == matchScore.getMatch().getPlayer1().getId()) {
            matchScore.setPlayer1Points(matchScore.getPlayer1Points() + 10);
        } else {
            matchScore.setPlayer2Points(matchScore.getPlayer2Points() + 10);
        }

        req.setAttribute("uuid", uuid);
        req.setAttribute("matchScoreModel", matchScore);

        RequestDispatcher dispatcher = req.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(req, resp);


    }
}

