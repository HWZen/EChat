package entity.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLDriver {
    public static Connection SQLConnection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            SQLConnection = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/echat?useUnicode=true&characterEncoding=utf-8", "admin", "admin");
            System.out.println("Connected to MySQL Successfully!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;
    private String encoding;

    public SQLDriver(String host, int port, String database, String username, String password, String encoding) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.encoding = encoding;
    }

    public SQLDriver() {
        this("localhost", 3306, "echat", "admin", "admin", "utf-8");
    }

    public Connection getConnection() throws SQLException {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=" + encoding, username, password);
            System.out.println("Connected to MySQL Successfully!");
        } catch (ClassNotFoundException e) {
            connection = null;
            e.printStackTrace();
        }
        return connection;
    }
}
