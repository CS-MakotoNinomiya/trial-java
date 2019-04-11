package trial.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String username;
    private static final String password;
    private static final String url;

    static {
        username = "trial";
        password = "trial";
        url = "jdbc:mysql://localhost/" + "trialdb";
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(url, username, password);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
