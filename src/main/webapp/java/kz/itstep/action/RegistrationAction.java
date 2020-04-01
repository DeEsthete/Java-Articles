package kz.itstep.action;

import kz.itstep.dao.UserDao;
import kz.itstep.entity.User;

import javax.management.OperationsException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.*;

public class RegistrationAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            try {
                registerUser(request, response);
                request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
            } catch (IllegalArgumentException | OperationsException e) {
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
            }
        }
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws OperationsException {
        UserDao userDao = new UserDao();

        if (request.getParameter("login").length() < 4) {
            throw new IllegalArgumentException("Login must be more than 4 characters");
        }

        if (request.getParameter("password").length() < 4) {
            throw new IllegalArgumentException("Password must be more than 4 characters");
        }

        if (!request.getParameter("password").equals(request.getParameter("password2"))) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (request.getParameter("firstName").length() < 4 || request.getParameter("firstName").length() < 4) {
            throw new IllegalArgumentException("First name and second name must be more than 4 characters");
        }

        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setSecondName(request.getParameter("secondName"));

         if (!userDao.insert(user)) {
             throw new OperationsException("An error occured");
         }
    }
}
