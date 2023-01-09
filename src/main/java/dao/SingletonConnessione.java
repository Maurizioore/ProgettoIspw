package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.*;

public class SingletonConnessione {
    private static Connection connection;
    private static final String url="jdbc:mysql://localhost:3306/database-app-ispw";
    private static final String username="ospite";
    private SingletonConnessione() throws SQLException {
        getSecret();
    }
    /*metodo che prende la password mantenuta in un segreto asw, il nome dell'utente che gestisce il segreto
    * è mycredentials e la regione ce gestisce e' "us-east-1", si collega al aws prende il segreto
    *  il segreto altro non è che la password del db e la usa per collegarsi al db */
    public static void getSecret() throws SQLException {

        String secretName = "mycredentials";
        Region region = Region.of("us-east-1");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // For a list of exceptions thrown, see
            // https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
            throw e;
        }

        String secret = getSecretValueResponse.secretString();

        //praticamente lui prende secret solo che genera una chiave valore e io voglio solo prende il valore
        //vedi su internet la connessione al db seconda voce a sinistra
        //questa che viene scritta è  quella che ho messo com psw di ospite
        connection=DriverManager.getConnection(url,username,secret);

    }

    public static Connection getInstance() throws SQLException {
        try {
            if (connection == null) {
                new SingletonConnessione();
            }
        }catch (SQLException e){
            throw new SQLException("impossibile connettersi al database\nriprova più tardi");
        }
        return connection;
    }
    public static void closeConnection() throws SQLException{
        if(connection!=null){
            connection.close();
        }
    }
}
