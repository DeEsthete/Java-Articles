package kz.itstep.action;

import kz.itstep.dao.UserDao;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.itstep.util.AppConstants.*;

public class ActionFactory {
    private static final Map<String, Action> PAGES = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init() {
        PAGES.put("GET/welcome", new DefaultAction(URL_WELCOME_PAGE));
        PAGES.put("GET/info", new DefaultAction(URL_INFO_PAGE));
        PAGES.put("GET/hi", new DefaultAction(URL_HI_PAGE));
        PAGES.put("GET/forbid", new DefaultAction(URL_FORBID_PAGE));
        PAGES.put("GET/authorization", new AuthorizationAction());
        PAGES.put("POST/authorization", new AuthorizationAction());
        PAGES.put("GET/registration", new RegistrationAction());
        PAGES.put("POST/registration", new RegistrationAction());
        PAGES.put("GET/articles", new ArticlesAction());
        PAGES.put("GET/article", new ArticleAction());
        PAGES.put("POST/delete/article", new DeleteArticleAction());
        PAGES.put("GET/edit/article", new UpdateArticleAction());
        PAGES.put("POST/edit/article", new UpdateArticleAction());
        PAGES.put("GET/add-article", new DefaultAction(URL_ADD_ARTICLE_PAGE));
        PAGES.put("POST/add-article", new AddArticleAction());
        PAGES.put("GET/article-like", new ArticleRateAction());
        PAGES.put("POST/add-commentary", new AddCommentaryAction());
        PAGES.put("POST/add-tag", new AddTagAction());
        PAGES.put("GET/set-language", new ChangeLanguageAction());
        PAGES.put("GET/logout", new LogoutAction());
        PAGES.put("GET/user_profile", new UserProfileAction());
        PAGES.put("GET/edit-profile", new EditProfileAction());
        PAGES.put("POST/edit-profile", new EditProfileAction());
    }

    public Action getAction(HttpServletRequest request, HttpServletResponse response) {
        String userToken = CookieHelper.getCookie(request, ATTR_USER_TOKEN);
        if (userToken != null) {
            UserDao userDao = new UserDao();
            User user = userDao.findByToken(userToken);
            if (user != null) {
                request.setAttribute("user", user);
            }
        }
        return PAGES.get(request.getMethod() + request.getPathInfo());
    }
}
