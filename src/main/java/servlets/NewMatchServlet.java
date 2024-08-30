package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ErrorResponse;
import model.Match;
import model.Player;
import service.OngoingMatchesService;
import service.NewMatchService;

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

        String playerOneName = req.getParameter("player1");
        String playerTwoName = req.getParameter("player2");

        if (playerOneName == null || playerOneName.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Wrong value for Player 1"));
            return;
        }

        if (playerTwoName == null || playerTwoName.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Wrong value for Player 2"));
            return;
        }

        Match match;

        try {
            match = new NewMatchService().createNewMatch(playerOneName,playerTwoName);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_NOT_FOUND,
                    e.getMessage()));
            return;
        }

        UUID uuid = OngoingMatchesService.addMatch(match);

        resp.sendRedirect("/match-score?uuid="+uuid);
    }


}
