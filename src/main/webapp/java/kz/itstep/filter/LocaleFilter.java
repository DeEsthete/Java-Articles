package kz.itstep.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;

import static kz.itstep.util.AppConstants.ATTR_LANG;

public class LocaleFilter implements Filter {
    private final Logger logger = Logger.getLogger(LocaleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LocaleFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("LocaleFilter doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();

        String language = null;
        if (cookies != null) {
            for (Cookie c: cookies) {
                if (ATTR_LANG.equals(c.getName())) {
                    language = c.getValue();
                    break;
                }
            }
            if (language != null) {
                Config.set(request.getSession(), Config.FMT_LOCALE, new Locale(language));
            } else {
                Locale currentLocale = Locale.getDefault();
                language = currentLocale.getLanguage();
                Config.set(request.getSession(), Config.FMT_LOCALE, currentLocale);
                Cookie cookie = new Cookie(ATTR_LANG, language);
                cookie.setMaxAge(60*60*24);
                response.addCookie(cookie);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("LocaleFilter destroy");
    }
}
