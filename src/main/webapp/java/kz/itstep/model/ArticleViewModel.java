package kz.itstep.model;

import kz.itstep.entity.User;

import java.util.List;

public class ArticleViewModel {
    private int id;
    private int user_id;
    private String title;
    private String body;
    private int likesCount;
    private int dislikesCount;
    private List<User> usersLike;
    private List<User> usersDislike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
}
