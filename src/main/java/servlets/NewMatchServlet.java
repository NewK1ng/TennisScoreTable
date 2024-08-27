package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.PlayerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ErrorResponse;
import model.Player;

import java.io.IOException;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String player1 = req.getParameter("player1");
        String player2 = req.getParameter("player2");

        if (player1 == null || player1.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Wrong value for Player 1"));
            return;
        }

        if (player2 == null || player2.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getWriter(), new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST,
                    "Wrong value for Player 2"));
            return;
        }

        try {
            Player p1 = new PlayerDAO().create(player1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
