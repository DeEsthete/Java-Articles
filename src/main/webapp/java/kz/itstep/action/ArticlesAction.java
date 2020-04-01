package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static kz.itstep.util.AppConstants.URL_ARTICLES_PAGE;

public class ArticlesAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        List<Article> articles = articleDao.findAll();
        articles.forEach(a -> {
            if (a.getBody().length() > 500) {
                a.setBody(a.getBody().substring(0, 500));
            }
        });
        request.setAttribute("list", articles);
        request.getRequestDispatcher(URL_ARTICLES_PAGE).forward(request, response);
    }
}
