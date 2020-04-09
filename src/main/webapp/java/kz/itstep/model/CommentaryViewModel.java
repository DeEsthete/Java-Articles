package kz.itstep.model;

import kz.itstep.entity.Commentary;

public class CommentaryViewModel {
    private int id;
    private int commentaryListId;
    private int userId;
    private String userNickname;
    private String content;

    public CommentaryViewModel() {
    }

    public CommentaryViewModel(Commentary commentary, String userNickname) {
        this.id = commentary.getId();
        this.commentaryListId = commentary.getCommentaryListId();
        this.userId = commentary.getUserId();
        this.content = commentary.getContent();
        this.userNickname = userNickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
