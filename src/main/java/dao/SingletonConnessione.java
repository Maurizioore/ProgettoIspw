package dao;

import eccezioni.ErroreLetturaPasswordException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class SingletonConnessione {
    private static Connection connection;
    private static final String URL="jdbc:mysql://localhost:3306/database-app-ispw";
    private static final String USERNAME="ospite";
    private SingletonConnessione() throws SQLException, IOException {
        collegatiAlDB();
    }
    private static void collegatiAlDB() throws IOException, SQLException {
        Properties properties=new Properties();
        InputStream is= new FileInputStream("application.properties");
        properties.load(is);
        connection=DriverManager.getConnection(URL,USERNAME,(String)properties.get("password"));
    }

    public static Connection getInstance() throws SQLException, ErroreLetturaPasswordException {
        try {
            if (connection == null) {
                new SingletonConnessione();
            }
        }catch (SQLException e){
            throw new SQLException("impossibile connettersi al database\nriprova più tardi");
        } catch (IOException e) {
            throw new ErroreLetturaPasswordException ("impossibile estrarre la password\ndi connessione al db");
        }
        return connection;
    }
    public static void closeConnection() throws SQLException{
        if(connection!=null){
            connection.close();
        }
    }
}
