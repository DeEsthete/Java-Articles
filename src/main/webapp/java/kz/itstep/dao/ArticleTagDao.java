package kz.itstep.dao;

import kz.itstep.entity.ArticleRate;
import kz.itstep.entity.ArticleTag;
import kz.itstep.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleTagDao extends AbstractDao<ArticleTag> {
    private String sqlInsertEntity = "insert into " + tableName + " (article_id, user_id, tag_id) values(?, ?, ?)";
    private String sqlUpdateEntity = "UPDATE " + tableName + " set article_id=?, user_id, tag_id where id=?";
    private String sqlEntityIsExist = "SELECT * FROM " + this.tableName + " where article_id=? and user_id=? and tag_id=?";
    private String sqlSelectEntitiesByArticleId = sqlSelectAllEntities + " where article_id=?";

    public ArticleTagDao() {
        super("\"ArticleTag\"");
    }

    public List<ArticleTag> getArticleTags(int articleId) {
        List<ArticleTag> entities = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEntitiesByArticleId)) {
            preparedStatement.setInt(1, articleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ArticleTag entity = setFields(resultSet);
                    entities.add(entity);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return entities;
    }

    public boolean isExist(ArticleTag articleTag) {
        ArticleTag entity = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlEntityIsExist)) {
            preparedStatement.setInt(1, articleTag.getArticleId());
            preparedStatement.setInt(2, articleTag.getUserId());
            preparedStatement.setInt(3, articleTag.getTagId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    entity = setFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return entity != null;
    }

    @Override
    public boolean insert(ArticleTag entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertEntity)) {
            preparedStatement.setInt(1, entity.getArticleId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setInt(3, entity.getTagId());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println("ArticleTag wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(ArticleTag entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateEntity)) {
            preparedStatement.setInt(1, entity.getArticleId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setInt(3, entity.getTagId());
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
    protected ArticleTag setFields(ResultSet resultSet) throws SQLException {
        ArticleTag articleTag = new ArticleTag();
        articleTag.setArticleId(resultSet.getInt("article_id"));
        articleTag.setUserId(resultSet.getInt("user_id"));
        articleTag.setTagId(resultSet.getInt("tag_id"));
        return articleTag;
    }
}
