package kz.itstep.dao;

import kz.itstep.entity.Role;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;

public class RoleDao extends AbstractDao<Role> {
    private String sqlInsertRole = "insert into " + tableName + " (name, weight) values(?, ?)";
    private String sqlUpdateRole = "UPDATE " + tableName + " set name=?, weight=? where id=?";
    private String sqlSelectRoleByName = "SELECT * FROM " + tableName + " where name=?";

    public RoleDao() {
        super("\"Role\"");
    }

    public Role findByName(String name) {
        Role role = new Role();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectRoleByName)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    role = setFields(resultSet);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateRole)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getWeight());
            preparedStatement.setInt(3, entity.getId());
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
    public boolean insert(Role entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertRole)) {
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

    protected Role setFields(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setName(resultSet.getString("name"));
        role.setWeight(resultSet.getInt("weight"));
        return role;
    }
}
