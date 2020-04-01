package kz.itstep.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.URL_HI_PAGE;

public class DefaultAction implements Action {
    private String page;

    public DefaultAction(String page) {
        this.page = page;
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }
}
