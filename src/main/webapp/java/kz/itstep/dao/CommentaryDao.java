package kz.itstep.dao;

import kz.itstep.entity.Commentary;
import kz.itstep.entity.Entity;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentaryDao extends AbstractDao<Commentary> {
    private String sqlInsertEntity = "insert into " + this.tableName + " (commentary_list_id, user_id, content) values(?, ?, ?)";
    private String sqlUpdateEntity = "UPDATE " + this.tableName + " set content=? where id=?";
    private String sqlSelectEntitiesByList = "SELECT * FROM " + this.tableName + " where commentary_list_id=?";

    public CommentaryDao() {
        super("\"Commentary\"");
    }

    public List<Commentary> findAllByCommentaryList(int id) {
        List<Commentary> entities = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEntitiesByList)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Commentary entity = setFields(resultSet);
                    entities.add(entity);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return entities;
    }

    @Override
    public boolean insert(Commentary entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertEntity)) {
            preparedStatement.setInt(1, entity.getCommentaryListId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setString(3, entity.getContent());
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
    public boolean update(Commentary entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateEntity)) {
            preparedStatement.setString(1, entity.getContent());
            preparedStatement.setInt(2, entity.getId());
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
    protected Commentary setFields(ResultSet resultSet) throws SQLException {
        Commentary commentary = new Commentary();
        commentary.setCommentaryListId(resultSet.getInt("commentary_list_id"));
        commentary.setUserId(resultSet.getInt("user_id"));
        commentary.setContent(resultSet.getString("content"));
        return commentary;
    }
}
