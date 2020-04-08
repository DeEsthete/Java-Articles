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
import static kz.itstep.util.AppConstants.URL_UPDATE_ARTICLE_PAGE;

public class DeleteArticleAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        UserDao userDao = new UserDao();

        int articleId = -1;
        String articleIdParameter = (String) request.getParameter("articleId");
        if (articleIdParameter != null && !articleIdParameter.isEmpty()) {
            articleId = Integer.parseInt((String) request.getParameter("articleId"));
        }

        User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
        Article article = articleDao.findById(articleId);
        if (user != null && article != null && article.getUserId() == user.getId()) {
            articleDao.delete(articleId);
            response.sendRedirect("/fs/articles");
            return;
        }
        response.sendRedirect("/fs/forbid");
    }
}
