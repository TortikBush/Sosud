import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Users {
    int id;
    String userName;

    public static Connection connect() {
        return null;
    }

    public Connection connect(int id, String userName) {
        this.id = id;
        this.userName = userName;
        return null;
    }
}


