package kz.itstep.dao;

import kz.itstep.entity.User;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;

public class UserDao extends AbstractDao<User> {
    private static final String TABLE_NAME = "\"User\"";

    private String sqlInsertUser = "insert into " + TABLE_NAME + " (login, password, first_name, second_name, role_id, token) values(?, ?, ?, ?, ?, ?)";
    private String sqlUpdateUser = "UPDATE" + TABLE_NAME + " set login=?, password=?, first_name=?, second_name=?, role_id=?, token=? where id=?";
    private String sqlSelectUserByToken = "SELECT * FROM " + TABLE_NAME + " where token=?";
    private String sqlSelectUserByLogin = "SELECT * FROM " + TABLE_NAME + " where login=?";

    public UserDao() {
        super("\"User\"");
    }

    public User findByLogin(String login) {
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUserByLogin)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    public User findByToken(String token) {
        User user = new User();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUserByToken)) {
            preparedStatement.setString(1, token);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean update(User entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateUser)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setInt(5, entity.getRoleId());
            preparedStatement.setString(6, entity.getToken());
            preparedStatement.setInt(7, entity.getId());
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
    public boolean insert(User entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getSecondName());
            preparedStatement.setInt(5, entity.getRoleId());
            preparedStatement.setString(6, entity.getToken());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            System.out.println("User wasn't inserted!" + e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    protected User setFields(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setSecondName(resultSet.getString("second_name"));
        user.setRoleId(resultSet.getInt("role_id"));
        user.setToken(resultSet.getString("token"));
        return user;
    }
}
