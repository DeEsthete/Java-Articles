package kz.itstep.action;

import kz.itstep.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.URL_ARTICLE_PAGE;

public class ArticleAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        int articleId = Integer.parseInt((String) request.getParameter("articleId"));
        request.setAttribute("article", articleDao.findById(articleId));
        request.getRequestDispatcher(URL_ARTICLE_PAGE).forward(request, response);
    }
}
