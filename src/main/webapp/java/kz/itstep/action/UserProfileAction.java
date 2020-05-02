package kz.itstep.action;

import kz.itstep.dao.*;
import kz.itstep.entity.Article;
import kz.itstep.entity.ArticleRate;
import kz.itstep.entity.Tag;
import kz.itstep.entity.User;
import kz.itstep.model.ProfileViewModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kz.itstep.util.AppConstants.URL_ARTICLES_PAGE;
import static kz.itstep.util.AppConstants.URL_USER_PROFILE;

public class UserProfileAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleRateDao articleRateDao = new ArticleRateDao();
        ArticleTagDao articleTagDao = new ArticleTagDao();
        ArticleDao articleDao = new ArticleDao();
        UserDao userDao = new UserDao();
        TagDao tagDao = new TagDao();

        String userId = request.getParameter("userId");
        if (userId == null) {
            return;
        }
        User user = userDao.findById(Integer.parseInt(userId));
        List<Article> articles = articleDao.findByUserId(user.getId());
        List<ArticleRate> rates = new ArrayList<>();
        articles.forEach(a -> rates.addAll(articleRateDao.getArticleRates(a.getId())));

        int countLikes = (int) rates.stream().filter(ArticleRate::isLike).count();
        int countDislikes = (int) rates.stream().filter(r -> !r.isLike()).count();

        ProfileViewModel model = new ProfileViewModel(user, articles, countLikes, countDislikes);

        request.setAttribute("profile", model);
        request.getRequestDispatcher(URL_USER_PROFILE).forward(request, response);
    }
}
