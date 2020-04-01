package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Article;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.ATTR_USER_TOKEN;

public class AddArticleAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        UserDao userDao = new UserDao();
        User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
        Article article = new Article();
        article.setUser_id(user.getId());
        article.setTitle(request.getParameter("title"));
        article.setBody(request.getParameter("body"));
        articleDao.insert(article);
        response.sendRedirect("/fs/articles");
    }
}
