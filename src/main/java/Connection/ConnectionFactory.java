package Connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains the name of the driver, the database location,
 * user and password for accessing the database along with the
 * methods for creating a Connection, getting an active Connection and
 * closing a connection, a Statement or a ResultSet.
 */
public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse";
    private static final String USER = "root";
    private static final String PASS = "1424";

    private static final ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructor which initializes the DRIVER
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to create the connection to the DB.
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Method which returns the connection to the DB.
     * @return connection to DB
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Method that closes the connection to the DB.
     * @param connection connection to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }
    /**
     * Method that closes a statement.
     * @param statement Statement to be closed.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Method that closes a ResultSet.
     * @param resultSet ResultSet to be closed.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
