package kz.itstep.action;

import kz.itstep.dao.ArticleDao;
import kz.itstep.dao.CommentaryDao;
import kz.itstep.dao.UserDao;
import kz.itstep.entity.Commentary;
import kz.itstep.entity.User;
import kz.itstep.helper.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.itstep.util.AppConstants.ATTR_USER_TOKEN;

public class AddCommentaryAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentaryDao commentaryDao = new CommentaryDao();
        UserDao userDao = new UserDao();
        Commentary commentary = new Commentary();
        commentary.setCommentaryListId(Integer.parseInt((String) request.getParameter("listId")));
        commentary.setContent((String) request.getParameter("content"));
        User user = userDao.findByToken(CookieHelper.getCookie(request, ATTR_USER_TOKEN));
        commentary.setUserId(user.getId());
        commentaryDao.insert(commentary);
    }
}
