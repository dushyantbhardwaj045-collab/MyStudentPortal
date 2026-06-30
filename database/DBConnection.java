package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL =
        "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databaseName=StudentDB;integratedSecurity=true;encrypt=true;trustServerCertificate=true";
    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("SQL Server Driver not found.", e);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}