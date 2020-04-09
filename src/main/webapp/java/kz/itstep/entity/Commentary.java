package kz.itstep.entity;

public class Commentary extends Entity {
    private int commentaryListId;
    private int userId;
    private String content;

    public int getCommentaryListId() {
        return commentaryListId;
    }

    public void setCommentaryListId(int commentaryListId) {
        this.commentaryListId = commentaryListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
