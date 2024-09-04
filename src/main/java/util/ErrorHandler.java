package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import model.ErrorResponse;

import java.io.IOException;

public class ErrorHandler {

    private final ObjectMapper mapper;

    public ErrorHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void handleError(HttpServletResponse resp, ErrorResponse errorResponse) throws IOException {
        resp.setStatus(errorResponse.getCode());
        mapper.writeValue(resp.getWriter(), errorResponse);
    }
}
