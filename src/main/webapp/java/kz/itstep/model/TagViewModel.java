package kz.itstep.model;

import kz.itstep.entity.ArticleTag;
import kz.itstep.entity.Tag;

public class TagViewModel {
    private int articleTagId;
    private int articleId;
    private int userId;
    private int tagId;
    private String name;
    private int count;

    public TagViewModel(ArticleTag articleTag, Tag tag, int count) {
        articleTagId = articleTag.getId();
        articleId = articleTag.getArticleId();
        userId = articleTag.getUserId();
        tagId = articleTag.getTagId();
        name = tag.getName();
        this.count = count;
    }

    public int getArticleTagId() {
        return articleTagId;
    }

    public void setArticleTagId(int articleTagId) {
        this.articleTagId = articleTagId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
