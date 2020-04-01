package kz.itstep.dao;

import kz.itstep.entity.Role;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao<Role> {
    private static final String TABLE_NAME = "\"Role\"";

    private static final String SQL_SELECT_ROLES_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_INSERT_ROLE =
            "insert into " + TABLE_NAME + " (name, weight) values(?, ?)";
    private static final String SQL_DELETE_ROLE_BY_ID = "DELETE FROM" + TABLE_NAME + " where id=?";
    private static final String SQL_UPDATE_ROLE =
            "UPDATE " + TABLE_NAME + " set name=?, weight=? where id=?";
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM " + TABLE_NAME + " where id=?";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT * FROM " + TABLE_NAME + " where name=?";

    public Role findByName(String name) {
        Role role = new Role();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_NAME)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    role = setRoleFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return role;
    }

    @Override
    public Role findById(int id) {
        Role role = new Role();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    role = setRoleFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return role;
    }

    @Override
    public boolean update(Role entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ROLE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getWeight());
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
    public boolean delete(Role entity) {
        return delete(entity.getId());
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_ROLE_BY_ID)) {
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
    public boolean insert(Role entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ROLE)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getWeight());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println("Role wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public List<Role> findAll() {
        List<Role> articles = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_ROLES_ALL)) {
            while (resultSet.next()) {
                Role role = setRoleFields(resultSet);
                articles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return articles;
    }

    private Role setRoleFields(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setName(resultSet.getString("name"));
        role.setWeight(resultSet.getInt("weight"));
        return role;
    }
}
