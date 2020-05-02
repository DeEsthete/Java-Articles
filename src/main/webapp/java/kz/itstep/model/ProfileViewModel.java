package kz.itstep.model;

import kz.itstep.entity.Article;
import kz.itstep.entity.User;

import java.util.List;

public class ProfileViewModel {
    private int id;
    private String fistName;
    private String secondName;
    private int countLikes;
    private int countDislikes;
    private List<Article> articles;

    public ProfileViewModel(User user, List<Article> articles, int countLikes, int countDislikes) {
        this.id = user.getId();
        this.fistName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.articles = articles;
        this.countLikes = countLikes;
        this.countDislikes = countDislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    public int getCountDislikes() {
        return countDislikes;
    }

    public void setCountDislikes(int countDislikes) {
        this.countDislikes = countDislikes;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
