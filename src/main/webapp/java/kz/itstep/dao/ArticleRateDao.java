package kz.itstep.dao;

import kz.itstep.entity.ArticleRate;
import kz.itstep.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleRateDao extends AbstractDao<ArticleRate> {
    private String sqlInsertEntity = "INSERT INTO " + this.tableName + " (user_id, article_id, is_like) values(?, ?, ?)";
    private String sqlUpdateEntity = "UPDATE " + this.tableName + " set user_id=?, article_id=?, is_like=? where=?";
    private String sqlSelectEntitiesByArticleId = sqlSelectAllEntities + " where article_id=?";
    private String sqlEntityIsExist = "SELECT * FROM " + this.tableName + " where user_id=? and article_id=?";

    public ArticleRateDao() {
        super("\"ArticleRate\"");
    }

    public boolean isExist(ArticleRate rate) {
        ArticleRate entity = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlEntityIsExist)) {
            preparedStatement.setInt(1, rate.getUserId());
            preparedStatement.setInt(2, rate.getArticleId());
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

    public List<ArticleRate> getArticleRates(int ideaId) {
        List<ArticleRate> entities = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEntitiesByArticleId)) {
            preparedStatement.setInt(1, ideaId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ArticleRate entity = setFields(resultSet);
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

    @Override
    public boolean insert(ArticleRate entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertEntity)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getArticleId());
            preparedStatement.setBoolean(3, entity.isLike());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(ArticleRate entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateEntity)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getArticleId());
            preparedStatement.setBoolean(3, entity.isLike());
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
    protected ArticleRate setFields(ResultSet resultSet) throws SQLException {
        ArticleRate rate = new ArticleRate();
        rate.setId(resultSet.getInt("id"));
        rate.setUserId(resultSet.getInt("user_id"));
        rate.setArticleId(resultSet.getInt("article_id"));
        rate.setLike(resultSet.getBoolean("is_like"));
        return rate;
    }
}
