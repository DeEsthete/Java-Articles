package kz.itstep.dao;

import kz.itstep.entity.ArticleTag;
import kz.itstep.entity.Entity;
import kz.itstep.entity.Tag;
import kz.itstep.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagDao extends AbstractDao<Tag> {
    private String sqlInsertEntity = "insert into " + tableName + " (name) values(?)";
    private String sqlUpdateEntity = "UPDATE " + tableName + " set name=? where id=?";
    private String sqlEntityIsExist = "SELECT * FROM " + this.tableName + " where \"name\"=?";

    public TagDao() {
        super("\"Tag\"");
    }

    public Tag findByName(Tag tag) {
        Tag entity = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlEntityIsExist)) {
            preparedStatement.setString(1, tag.getName());
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
        return entity;
    }

    @Override
    public boolean insert(Tag entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertEntity)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println("Tag wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public boolean update(Tag entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateEntity)) {
            preparedStatement.setString(1, entity.getName());
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
    protected Tag setFields(ResultSet resultSet) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getInt("id"));
        tag.setName(resultSet.getString("name"));
        return tag;
    }
}
