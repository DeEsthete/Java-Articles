package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.dao.ArticleRateDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Article;
import kz.itstep.entity.ArticleRate;
import kz.itstep.model.ArticleViewModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static kz.itstep.util.AppConstants.URL_ARTICLE_PAGE;

public class ArticleAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        ArticleRateDao rateDao = new ArticleRateDao();
        UserDao userDao = new UserDao();
        int articleId = Integer.parseInt((String) request.getParameter("articleId"));

        Article article = articleDao.findById(articleId);
        ArticleViewModel model = new ArticleViewModel();
        model.setId(article.getId());
        model.setUser_id(article.getUserId());
        model.setTitle(article.getTitle());
        model.setBody(article.getBody());

        List<ArticleRate> likes = rateDao.getArticleRates(articleId).stream().filter(r -> r.isLike()).collect(Collectors.toList());
        List<ArticleRate> dislikes = rateDao.getArticleRates(articleId).stream().filter(r -> !r.isLike()).collect(Collectors.toList());
        model.setLikesCount(likes.size());
        model.setDislikesCount(dislikes.size());
        model.setUsersLike(likes.stream().map(r -> userDao.findById(r.getUserId())).collect(Collectors.toList()));
        model.setUsersDislike(dislikes.stream().map(r -> userDao.findById(r.getUserId())).collect(Collectors.toList()));

        request.setAttribute("article", model);

        request.getRequestDispatcher(URL_ARTICLE_PAGE).forward(request, response);
    }
}
