package kz.itstep.dao;

import kz.itstep.entity.Article;
import kz.itstep.entity.ArticleRate;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends AbstractDao<Article> {
    private String sqlInsertArticle = "insert into " + tableName + " (user_id, title, body, commentary_list_id) values(?, ?, ?, ?)";
    private String sqlUpdateArticle = "UPDATE " + this.tableName + " set user_id=?, title=?, body=? where id=?";
    private String sqlSelectEntitiesByUserId = sqlSelectAllEntities + " where user_id=?";

    public ArticleDao() {
        super("\"Article\"");
    }

    public List<Article> findByUserId(int userId) {
        List<Article> entities = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEntitiesByUserId)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Article entity = setFields(resultSet);
                    entities.add(entity);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred " + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return entities;
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

        int commentaryListId = -1;
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into \"CommentaryList\" DEFAULT VALUES RETURNING id")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    commentaryListId = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Commentary wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertArticle)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getBody());
            preparedStatement.setInt(4, commentaryListId);
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
        article.setUserId(resultSet.getInt("user_id"));
        article.setTitle(resultSet.getString("title"));
        article.setBody(resultSet.getString("body"));
        article.setCommentaryListId(resultSet.getInt("commentary_list_id"));
        return article;
    }
}
