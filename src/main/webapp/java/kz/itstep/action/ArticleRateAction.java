package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.dao.ArticleRateDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Article;
import kz.itstep.entity.ArticleRate;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.ATTR_USER_TOKEN;

public class ArticleRateAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleRateDao rateDao = new ArticleRateDao();
        UserDao userDao = new UserDao();
        User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
        ArticleRate rate = new ArticleRate();
        rate.setArticleId(Integer.parseInt(request.getParameter("articleId")));
        rate.setUserId(user.getId());
        rate.setLike(Boolean.parseBoolean(request.getParameter("isLike")));
        if (!rateDao.isExist(rate)) {
            rateDao.insert(rate);
        }
        response.sendRedirect("/fs/articles");
    }
}
