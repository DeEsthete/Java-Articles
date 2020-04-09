package kz.itstep.entity;

public class Article extends Entity {
    private int user_id;
    private String title;
    private String body;
    private int commentaryListId;

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCommentaryListId() {
        return commentaryListId;
    }

    public void setCommentaryListId(int commentaryListId) {
        this.commentaryListId = commentaryListId;
    }
}
