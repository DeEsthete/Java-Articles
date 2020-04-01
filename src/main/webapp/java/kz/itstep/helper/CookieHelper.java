package kz.itstep.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CookieHelper {
    public static void setCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        String result = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies) {
            if (name.equals(c.getName())) {
                result = c.getValue();
                break;
            }
        }
        return result;
    }

    public static boolean removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return true;
        }
        return false;
    }
}
