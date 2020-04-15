package kz.itstep.action;

import kz.itstep.dao.*;
import kz.itstep.entity.*;
import kz.itstep.helper.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.ATTR_USER_TOKEN;

public class AddTagAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleRateDao rateDao = new ArticleRateDao();
        UserDao userDao = new UserDao();
        TagDao tagDao = new TagDao();
        ArticleTagDao articleTagDao = new ArticleTagDao();
        User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int userId = user.getId();
        String tagName = request.getParameter("tagName");

        Tag tag = new Tag();
        tag.setName(tagName);

        if (tagDao.findByName(tag) == null) {
            tagDao.insert(tag);
        }
        tag = tagDao.findByName(tag);

        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(articleId);
        articleTag.setUserId(userId);
        articleTag.setTagId(tag.getId());
        boolean isArticleTagExist = articleTagDao.isExist(articleTag);

        if (isArticleTagExist) {
            response.sendRedirect("/fs/article?articleId=" + articleId);
            return;
        }

        articleTagDao.insert(articleTag);

        response.sendRedirect("/fs/article?articleId=" + articleId);
    }
}
