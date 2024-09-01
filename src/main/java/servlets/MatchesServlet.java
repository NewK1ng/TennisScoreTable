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

        int pageNumber;

        try {
            if (pageNumberParam != null) {
                pageNumber = Integer.parseInt(pageNumberParam);
            } else {
                pageNumber = 1;
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid page number");
            return;
        }

        FinishedMatchesPage finishedMatchesPage;

        try {
            finishedMatchesPage = finishedMatchesService.getFinishedMatches(pageNumber, NUMBER_OF_ELEMENTS_PER_PAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("finishedMatchesPage", finishedMatchesPage);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);

    }

}
