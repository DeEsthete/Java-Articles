package kz.itstep.dao;

import kz.itstep.entity.Article;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends AbstractDao<Article> {
    private static final String TABLE_NAME = "\"Article\"";

    private static final String SQL_SELECT_ARTICLES_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_INSERT_ARTICLE =
            "insert into " + TABLE_NAME + " (user_id, title, body) values(?, ?, ?)";
    private static final String SQL_DELETE_ARTICLE_BY_ID = "DELETE FROM" + TABLE_NAME + " where id=?";
    private static final String SQL_UPDATE_ARTICLE =
            "UPDATE " + TABLE_NAME + " set user_id=?, title=?, body=? where id=?";
    private static final String SQL_SELECT_ARTICLE_BY_ID = "SELECT * FROM " + TABLE_NAME + " where id=?";

    @Override
    public boolean delete(Article entity) {
        return this.delete(entity.getId());
    }

    @Override
    public Article findById(int id) {
        Article article = new Article();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ARTICLE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    article = setArticleFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return article;
    }

    @Override
    public boolean update(Article entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ARTICLE)) {
            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getBody());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ARTICLE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public boolean insert(Article entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ARTICLE)) {
            preparedStatement.setInt(1, entity.getUser_id());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getBody());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println("Article wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ARTICLES_ALL)) {
            while (resultSet.next()) {
                Article article = setArticleFields(resultSet);
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return articles;
    }

    private Article setArticleFields(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setUser_id(resultSet.getInt("user_id"));
        article.setTitle(resultSet.getString("title"));
        article.setBody(resultSet.getString("body"));
        return article;
    }
}
