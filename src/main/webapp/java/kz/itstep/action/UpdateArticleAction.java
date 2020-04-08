package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Article;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;
import kz.itstep.helper.PasswordHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static kz.itstep.util.AppConstants.*;

public class UpdateArticleAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher(URL_UPDATE_ARTICLE_PAGE).forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            ArticleDao articleDao = new ArticleDao();
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            Article article = articleDao.findById(articleId);

            UserDao userDao = new UserDao();
            User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));

            if (article.getUserId() != user.getId()) {
                response.sendRedirect("/fs/forbid");
                return;
            }

            article.setId(articleId);
            article.setTitle(request.getParameter("title"));
            article.setBody(request.getParameter("body"));

            articleDao.update(article);
            response.sendRedirect("/fs/articles");
        }
    }
}
