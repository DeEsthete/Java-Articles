package kz.itstep.filter;

import kz.itstep.dao.RoleDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Role;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static kz.itstep.util.AppConstants.*;

public class RoleFilter implements Filter {
    private final Logger logger = Logger.getLogger(RoleFilter.class);

    private final Set<String> userAccess = new HashSet<>();
    private final Set<String> moderatorAccess = new HashSet<>();
    private final Set<String> adminAccess = new HashSet<>();
    private final Set<String> rootAccess = new HashSet<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initUserAccess();
        initModeratorAccess();
        initAdminAccess();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = request.getMethod() + request.getPathInfo();
        String userToken = CookieHelper.getCookie(request, ATTR_USER_TOKEN);
        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();

        Role userRole = null;
        if (userToken != null) {
            User user = userDao.findByToken(userToken);
            if (user != null) {
                userRole = roleDao.findById(user.getRoleId());
            }
        }

        if (!checkPermission(userRole, path)) {
            request.getRequestDispatcher(URL_FORBID_PAGE).forward(request, response);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean checkPermission(Role userRole, String path) {
        if (userRole != null) {
            return !isExist(path) ||
                    userAccess.contains(path) && userRole.getWeight() >= getRoleByName(ROLE_USER).getWeight() ||
                    moderatorAccess.contains(path) && userRole.getWeight() >= getRoleByName(ROLE_MODERATOR).getWeight() ||
                    adminAccess.contains(path) && userRole.getWeight() >= getRoleByName(ROLE_ADMIN).getWeight() ||
                    rootAccess.contains(path) && userRole.getWeight() >= getRoleByName(ROLE_ROOT).getWeight();
        } else {
            return !isExist(path);
        }
    }

    private Role getRoleByName(String roleName) {
        RoleDao roleDao = new RoleDao();
        return roleDao.findByName(roleName);
    }

    private boolean isExist(String path) {
        return userAccess.contains(path) ||
                moderatorAccess.contains(path) ||
                adminAccess.contains(path) ||
                rootAccess.contains(path);
    }

    private void initUserAccess() {
        userAccess.add("POST/article");
        userAccess.add("PUT/article");
        userAccess.add("DELETE/article");
        userAccess.add("GET/add-article");
        userAccess.add("POST/add-article");
    }

    private void initModeratorAccess() {

    }

    private void initAdminAccess() {

    }
}
