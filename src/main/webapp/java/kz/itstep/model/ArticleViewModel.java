package kz.itstep.model;

import kz.itstep.entity.User;

import java.util.List;

public class ArticleViewModel {
    private int id;
    private int userId;
    private String title;
    private String body;
    private int commentaryListId;
    private int likesCount;
    private int dislikesCount;
    private List<User> usersLike;
    private List<User> usersDislike;
    private List<CommentaryViewModel> commentaries;
    private List<TagViewModel> tags;

    public int getId() {
        return id;
    }

    public String getIdString() {
        return String.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public List<User> getUsersLike() {
        return usersLike;
    }

    public void setUsersLike(List<User> usersLike) {
        this.usersLike = usersLike;
    }

    public List<User> getUsersDislike() {
        return usersDislike;
    }

    public void setUsersDislike(List<User> usersDislike) {
        this.usersDislike = usersDislike;
    }

    public List<CommentaryViewModel> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<CommentaryViewModel> commentaries) {
        this.commentaries = commentaries;
    }

    public List<TagViewModel> getTags() {
        return tags;
    }

    public void setTags(List<TagViewModel> tags) {
        this.tags = tags;
    }
}
