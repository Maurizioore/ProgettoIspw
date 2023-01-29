package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.BucaStradale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BucaStradaleDaoImpl implements BucaStradaleDao{
    //è una classe dao deve prendere la connessione
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public BucaStradaleDaoImpl() throws SQLException, ErroreLetturaPasswordException {
        //prendo la connessione dalla singleton
        connection=SingletonConnessione.getInstance();
    }
    private void verificaConnessione() throws SQLException, ErroreLetturaPasswordException {
        if(connection==null){
            new BucaStradaleDaoImpl();
        }
    }
    @Override
    public void saveBucaStradale(BucaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //deve inviare la buca stradale al db
        //verifico  se la connessione al db e' aperta oppure no
        //verificaConnessione();
        ////la connessione e' aperta posso inviare la segnalazione al bd,
        ////questa non controlla se la segnalazione sia già esiste la manda al db e basta
        //preparedStatement = connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesSalvaBuca());
        //preparedStatement.setString(1, instance.indirizzo);
        //preparedStatement.setString(2, instance.profondita);
        //preparedStatement.setString(3, UtilityAccesso.getCodiceUtente());
        //preparedStatement.executeUpdate();
        //System.out.println("sono una sout in buca stradale dao impl e la buca e' stata segnalata con successo");
    }
}
