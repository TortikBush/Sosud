package DBSourse;

import java.sql.*;
import java.util.Properties;

/**
 * Utility class for establishing connections to the PostgreSQL database.
 * Contains a method to create and return database connections.
 */
public class JDBCPostgreSQLConnection {
    /**
     * Opens a connection to the PostgreSQL database
     * 
     * @return A Connection object to the database
     * @throws SQLException If there is an error connecting to the database
     * @throws ClassNotFoundException If the PostgreSQL driver class is not found
     */
    public static Connection OpenConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/sosud";

        // Создание свойств соединения с базой данных
        Properties authorization = new Properties();
        authorization.put("user", "postgres"); // Зададим имя пользователя БД
        authorization.put("password", "12345"); // Зададим пароль доступа в БД

        return DriverManager.getConnection(url, authorization);
    }
}
