package kz.itstep.action;

import kz.itstep.dao.UserDao;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;
import kz.itstep.helper.PasswordHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static kz.itstep.util.AppConstants.*;

public class EditProfileAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            UserDao userDao = new UserDao();
            User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
            request.setAttribute("firstName", user.getFirstName());
            request.setAttribute("secondName", user.getSecondName());
            request.getRequestDispatcher(URL_EDIT_PROFILE).forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            UserDao userDao = new UserDao();
            User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
            user.setFirstName(request.getParameter("firstName"));
            user.setSecondName(request.getParameter("secondName"));

            String newPassword = request.getParameter("newPassword");
            if (!newPassword.isEmpty()) {
                user.setPassword(PasswordHelper.getHash(newPassword));
            }

            userDao.update(user);
            response.sendRedirect("/fs/user_profile?userId=" + user.getId());
        }
    }
}
