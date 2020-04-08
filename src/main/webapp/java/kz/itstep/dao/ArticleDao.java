package kz.itstep.dao;

import kz.itstep.entity.Article;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;

public class ArticleDao extends AbstractDao<Article> {
    private String sqlInsertArticle = "insert into " + tableName + " (user_id, title, body) values(?, ?, ?)";
    private String sqlUpdateArticle = "UPDATE " + this.tableName + " set user_id=?, title=?, body=? where id=?";

    public ArticleDao() {
        super("\"Article\"");
    }

    @Override
    public boolean update(Article entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateArticle)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getBody());
            preparedStatement.setInt(4, entity.getId());
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
    public boolean insert(Article entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertArticle)) {
            preparedStatement.setInt(1, entity.getUserId());
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

    protected Article setFields(ResultSet resultSet) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setUser_id(resultSet.getInt("user_id"));
        article.setTitle(resultSet.getString("title"));
        article.setBody(resultSet.getString("body"));
        return article;
    }
}
