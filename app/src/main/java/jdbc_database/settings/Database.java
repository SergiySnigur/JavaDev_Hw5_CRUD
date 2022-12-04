package jdbc_database.settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;

    private Database() {
        try {
            String connectionUrl = new Prefs().getPref(Prefs.DATABASE_JDBC_CONNECTION_URL);
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public  Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
