package kz.itstep.action;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

import static kz.itstep.util.AppConstants.ATTR_LANG;

public class ChangeLanguageAction implements Action {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter(ATTR_LANG);
        Config.set(request.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie(ATTR_LANG, language);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        request.setCharacterEncoding("UTF-8");
        response.sendRedirect(request.getHeader("referer"));
    }
}
