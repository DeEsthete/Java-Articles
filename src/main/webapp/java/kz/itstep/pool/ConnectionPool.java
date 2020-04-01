package kz.itstep.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool connectionPool = init();
    private static final String URL = "jdbc:postgresql://localhost:5432/project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "0000";
    private static final int POOL_SIZE = 10;

    private final BlockingQueue<Connection> CONNECTIONS;

    private ConnectionPool(String url, String user, String password) throws SQLException {
        CONNECTIONS = new ArrayBlockingQueue<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection = DriverManager.getConnection(url,user,password);
            CONNECTIONS.offer(connection);
        }
    }

    private static ConnectionPool init(){
        ConnectionPool tempCP = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            tempCP = new ConnectionPool(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempCP;
    }

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = CONNECTIONS.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection){
        try {
            if(!connection.isClosed()){
                if(!CONNECTIONS.offer(connection)){
                    System.out.println("Connection wasn't added!");
                }
            } else {
                System.out.println("Connection was closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearConnectionQueue() throws SQLException {
        Connection connection;
        while ((connection = CONNECTIONS.poll()) != null){
            if(!connection.getAutoCommit()){
                connection.commit();
            }
            connection.close();
        }
    }

    public static void dispose() throws SQLException {
        if(connectionPool != null){
            connectionPool.clearConnectionQueue();
            connectionPool = null;
        }
    }
}
