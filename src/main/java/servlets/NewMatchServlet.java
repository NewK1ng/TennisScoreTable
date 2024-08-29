package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PlayerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ErrorResponse;
import model.Match;
import model.MatchScore;
import model.Player;
import service.OngoingMatchesService;
import service.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String player1Name = req.getParameter("player1");
        String player2Name = req.getParameter("player2");

        if (player1Name == null || player1Name.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Wrong value for Player 1"));
            return;
        }

        if (player2Name == null || player2Name.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Wrong value for Player 2"));
            return;
        }

        Player player1;
        Player player2;

        try {
            player1 = new PlayerService().createPlayer(player1Name);
            player2 = new PlayerService().createPlayer(player2Name);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_NOT_FOUND,
                    e.getMessage()));
            return;
        }

        Match match = new Match(player1, player2);
        UUID uuid = OngoingMatchesService.addMatch(match);

        resp.sendRedirect("/match-score?uuid="+uuid);
    }


}
