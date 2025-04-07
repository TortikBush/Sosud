package DBSourse;

import java.sql.*;
import java.util.Properties;

public class JDBCPosgreSQLConnection {

    public static Connection OpenConection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/sosud";

        // Создание свойств соединения с базой данных
        Properties authorization = new Properties();
        authorization.put("user", "postgres"); // Зададим имя пользователя БД
        authorization.put("password", "12345"); // Зададим пароль доступа в БД

        return DriverManager.getConnection(url, authorization);
    }
}
