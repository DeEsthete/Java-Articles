package kz.itstep.dao;

import kz.itstep.entity.Entity;
import kz.itstep.entity.Role;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Entity> {
    protected String tableName;
    protected String sqlSelectAllEntities;
    protected String sqlSelectEntityById;
    protected String sqlDeleteEntityById;

    public AbstractDao(String tableName) {
        this.tableName = tableName;
        sqlSelectAllEntities = "SELECT * FROM " + this.tableName;
        sqlSelectEntityById = "SELECT * FROM " + this.tableName + " where id=?";
        sqlDeleteEntityById = "DELETE FROM" + this.tableName + " where id=?";
    }

    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlSelectAllEntities)) {
            while (resultSet.next()) {
                T article = setFields(resultSet);
                entities.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return entities;
    }

    public T findById(int id) {
        T entity = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectEntityById)) {
            preparedStatement.setInt(1, id);
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

    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteEntityById)) {
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

    public boolean delete(T entity) {
        return this.delete(entity.getId());
    }

    public abstract boolean insert(T entity);

    public abstract boolean update(T entity);

    protected abstract T setFields(ResultSet resultSet) throws SQLException;
}
