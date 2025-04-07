package Frame;

import java.sql.Connection;

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


