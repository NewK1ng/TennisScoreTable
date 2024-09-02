package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FinishedMatchesPage;
import service.FinishedMatchesService;

import java.io.IOException;

@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchesService finishedMatchesService = new FinishedMatchesService();
    private final static int NUMBER_OF_ELEMENTS_PER_PAGE = 5;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNumberParam = req.getParameter("page");
        String playerNameParam = req.getParameter("filter_by_player_name");

        int pageNumber = 1;
        String playerName = null;

        try {
            if (pageNumberParam != null) {
                pageNumber = Integer.parseInt(pageNumberParam);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number");
            return;
        }

        if (playerNameParam != null && !playerNameParam.isBlank()) {
            playerName = playerNameParam;
        }

        FinishedMatchesPage finishedMatchesPage;

        try {
            finishedMatchesPage = finishedMatchesService.getFinishedMatches(pageNumber, NUMBER_OF_ELEMENTS_PER_PAGE, playerName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("finishedMatchesPage", finishedMatchesPage);

        getServletContext().getRequestDispatcher("/matches.jsp").forward(req, resp);

    }

}
