package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ErrorResponse;
import model.FinishedMatchesPage;
import service.FinishedMatchesService;
import util.ErrorHandler;

import java.io.IOException;

@WebServlet(urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {

    private ErrorHandler errorHandler;
    private final static int NUMBER_OF_ELEMENTS_PER_PAGE = 5;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ObjectMapper mapper = new ObjectMapper();
        errorHandler = new ErrorHandler(mapper);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNumberParam = req.getParameter("page");
        String playerNameParam = req.getParameter("filter_by_player_name");

        int pageNumber = 1;
        String playerName = null;

        try {
            if (pageNumberParam != null && !pageNumberParam.isBlank()) {
                pageNumber = Integer.parseInt(pageNumberParam);
            }
        } catch (Exception e) {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_BAD_REQUEST, "Wrong value for page number"));
            return;
        }

        if (playerNameParam != null && !playerNameParam.isBlank()) {
            playerName = playerNameParam;
        }

        FinishedMatchesPage finishedMatchesPage;

        try {
            FinishedMatchesService finishedMatchesService = new FinishedMatchesService();
            finishedMatchesPage = finishedMatchesService.getFinishedMatches(pageNumber, NUMBER_OF_ELEMENTS_PER_PAGE, playerName);
        } catch (Exception e) {
            errorHandler.handleError(resp, new ErrorResponse(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
            return;
        }

        req.setAttribute("finishedMatchesPage", finishedMatchesPage);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);

    }

}
