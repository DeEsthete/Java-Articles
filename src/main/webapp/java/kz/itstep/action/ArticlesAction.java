package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.dao.ArticleTagDao;
import kz.itstep.dao.TagDao;
import kz.itstep.entity.Article;
import kz.itstep.entity.Tag;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static kz.itstep.util.AppConstants.URL_ARTICLES_PAGE;

public class ArticlesAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleDao articleDao = new ArticleDao();
        TagDao tagDao = new TagDao();
        ArticleTagDao articleTagDao = new ArticleTagDao();
        String tagAttribute = request.getParameter("tag");
        Tag tag = null;
        if (tagAttribute != null) {
            tag = tagDao.findById(Integer.parseInt(tagAttribute));
        }

        Tag finalTag = tag;
        List<Article> articles = articleDao.findAll();

        if (finalTag != null) {
            articles = articles
                    .stream()
                    .filter(a -> articleTagDao.getArticleTags(a.getId())
                            .stream()
                            .anyMatch(t -> t.getTagId() == finalTag.getId()))
                            .collect(Collectors.toList());
        }

        articles.forEach(a -> {
            if (a.getBody().length() > 500) {
                a.setBody(a.getBody().substring(0, 500));
            }
        });

        request.setAttribute("tags", tagDao.findAll());
        request.setAttribute("list", articles);
        request.getRequestDispatcher(URL_ARTICLES_PAGE).forward(request, response);
    }
}
