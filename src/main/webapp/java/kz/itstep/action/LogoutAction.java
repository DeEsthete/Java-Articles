package kz.itstep.action;

import kz.itstep.helper.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.ATTR_USER_TOKEN;

public class LogoutAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CookieHelper.removeCookie(request, response, ATTR_USER_TOKEN);
        response.sendRedirect("/fs/authorization");
    }
}
