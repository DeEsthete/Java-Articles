package kz.itstep.dao;

import kz.itstep.entity.User;
import kz.itstep.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private static final String TABLE_NAME = "\"User\"";

    private static final String SQL_SELECT_USERS_ALL = "SELECT * FROM " + TABLE_NAME;
    private static final String SQL_INSERT_USER =
            "insert into " + TABLE_NAME + " (login, password, first_name, second_name, role_id, token) values(?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM public.user where id=?";
    private static final String SQL_UPDATE_USER =
            "UPDATE" + TABLE_NAME + " set login=?, password=?, first_name=?, second_name=?, role_id=?, token=? where id=?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM " + TABLE_NAME + " where id=?";
    private static final String SQL_SELECT_USER_BY_TOKEN = "SELECT * FROM " + TABLE_NAME + " where token=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM " + TABLE_NAME + " where login=? and password=?";

    public User findByLoginPassword(String login, String password) {
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserFields(resultSet);
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

    @Override
    public boolean delete(User entity) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, entity.getId());
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
    public User findById(int id) {
        User user = new User();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserFields(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred");
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    public User findByToken(String token) {
        User user = new User();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_TOKEN)) {
            preparedStatement.setString(1, token);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserFields(resultSet);
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
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
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
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
    public boolean insert(User entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)) {
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

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_USERS_ALL)) {
            while (resultSet.next()) {
                users.add(setUserFields(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return users;
    }

    private User setUserFields(ResultSet resultSet) throws SQLException {
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
