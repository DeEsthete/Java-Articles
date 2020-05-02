package kz.itstep.util;

public final class AppConstants {
    public static final String ROLE_USER = "User";
    public static final String ROLE_MODERATOR = "Moderator";
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_ROOT = "Root";

    private static final String BASE_URL = "/WEB-INF/jsp/";

    public static final String URL_WELCOME_PAGE = BASE_URL + "welcome.jsp";
    public static final String URL_INFO_PAGE = BASE_URL + "info.jsp";
    public static final String URL_HI_PAGE = BASE_URL + "hi.jsp";
    public static final String URL_FORBID_PAGE = BASE_URL + "forbid.jsp";
    public static final String URL_REGISTRATION_PAGE = BASE_URL + "registration.jsp";
    public static final String URL_AUTHORIZATION_PAGE = BASE_URL + "authorization.jsp";
    public static final String URL_ARTICLES_PAGE = BASE_URL + "articles.jsp";
    public static final String URL_ARTICLE_PAGE = BASE_URL + "article.jsp";
    public static final String URL_ADD_ARTICLE_PAGE = BASE_URL + "add_article.jsp";
    public static final String URL_UPDATE_ARTICLE_PAGE = BASE_URL + "update_article.jsp";
    public static final String URL_USER_PROFILE = BASE_URL + "user_profile.jsp";
    public static final String URL_EDIT_PROFILE = BASE_URL + "edit_profile.jsp";


    public static final String LOGIN_ERROR = "login_error";

    public static final String ATTR_USER_TOKEN = "user_token";
    public static final String ATTR_LANG = "lang";

}
