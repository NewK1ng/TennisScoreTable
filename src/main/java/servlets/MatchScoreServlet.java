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

        req.setAttribute("player1", matchScore.getMatch().getPlayer1().getName());
        req.setAttribute("player2", matchScore.getMatch().getPlayer2().getName());
        req.setAttribute("match_id", uuid);

        RequestDispatcher dispatcher = req.getRequestDispatcher("match-score.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

