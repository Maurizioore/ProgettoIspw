package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnessione {
    private static Connection connection=null;
    private final String nomeDatabase="database-app-ispw";
    private final String url="jdbc:mysql://localhost:3306/database-app-ispw";
    private final String username="root";
    private final String password="password";
    private SingletonConnessione() throws SQLException {
        connection= DriverManager.getConnection(url,username,password);
    }
    public static Connection getInstance() throws SQLException {
        if(connection==null){
            new SingletonConnessione();
        }
        return connection;
    }

    public static void closeConnection() throws SQLException{
        if(connection!=null){
            connection.close();
        }
    }
}
