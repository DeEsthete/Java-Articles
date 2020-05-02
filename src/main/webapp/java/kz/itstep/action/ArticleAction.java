package kz.itstep.action;

import kz.itstep.dao.*;
import kz.itstep.entity.Article;
import kz.itstep.entity.ArticleRate;
import kz.itstep.entity.ArticleTag;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;
import kz.itstep.model.ArticleViewModel;
import kz.itstep.model.CommentaryViewModel;
import kz.itstep.model.TagViewModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static kz.itstep.util.AppConstants.ATTR_USER_TOKEN;
import static kz.itstep.util.AppConstants.URL_ARTICLE_PAGE;

public class ArticleAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        ArticleRateDao rateDao = new ArticleRateDao();
        UserDao userDao = new UserDao();
        CommentaryDao commentaryDao = new CommentaryDao();
        ArticleTagDao articleTagDao = new ArticleTagDao();
        TagDao tagDao = new TagDao();
        int articleId = Integer.parseInt((String) request.getParameter("articleId"));

        Article article = articleDao.findById(articleId);
        ArticleViewModel model = new ArticleViewModel();
        model.setId(article.getId());
        model.setUserId(article.getUserId());
        model.setUserName(userDao.findById(article.getUserId()).getFirstName());
        model.setTitle(article.getTitle());
        model.setBody(article.getBody());
        model.setCommentaryListId(article.getCommentaryListId());

        List<ArticleRate> likes = rateDao.getArticleRates(articleId).stream().filter(r -> r.isLike()).collect(Collectors.toList());
        List<ArticleRate> dislikes = rateDao.getArticleRates(articleId).stream().filter(r -> !r.isLike()).collect(Collectors.toList());
        model.setLikesCount(likes.size());
        model.setDislikesCount(dislikes.size());
        model.setUsersLike(likes.stream().map(r -> userDao.findById(r.getUserId())).collect(Collectors.toList()));
        model.setUsersDislike(dislikes.stream().map(r -> userDao.findById(r.getUserId())).collect(Collectors.toList()));
        model.setCommentaries(
                commentaryDao.findAllByCommentaryList(article.getCommentaryListId())
                        .stream()
                        .map(c -> new CommentaryViewModel(c, userDao.findById(c.getUserId()).getLogin()))
                        .collect(Collectors.toList())
        );
        model.setTags(
                articleTagDao.getArticleTags(articleId)
                        .stream()
                        .collect(Collectors.groupingBy(ArticleTag::getTagId))
                        .values()
                        .stream()
                        .map(at -> new TagViewModel(at.stream().findFirst().get(), tagDao.findById(at.stream().findFirst().get().getTagId()), at.size()))
                        .collect(Collectors.toList())
        );

        User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
        request.setAttribute("isMyArticle", model.getUserId() == user.getId());
        request.setAttribute("article", model);

        request.getRequestDispatcher(URL_ARTICLE_PAGE).forward(request, response);
    }
}
