package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.BucaStradale;
import entita.EntitaStradale;
import queries.QueriesSegnalazioneBucaStradale;
import utilityaccesso.UtilityAccesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BucaStradaleDaoImplJDBC implements EntitaStradaleDao{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public BucaStradaleDaoImplJDBC() throws SQLException, ErroreLetturaPasswordException {
        connection=SingletonConnessione.getInstance();
    }
    private void verificaConnessione() throws SQLException, ErroreLetturaPasswordException {
        if(connection==null){
            new BucaStradaleDaoImplJDBC();
        }
    }
    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //sono qui sto segnalando una buca quindi faccio il cast da entità stradale a buca
        BucaStradale bucaStradaleDaSegnalare= new BucaStradale(instance.infoEntita(),instance.getIndirizzo());
        verificaConnessione();
        //la connessione e' aperta posso inviare la segnalazione al bd,
        //questa non controlla se la segnalazione sia già esiste la manda al db e basta
        preparedStatement = connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesSalvaBuca());
        preparedStatement.setString(1, bucaStradaleDaSegnalare.indirizzo);
        preparedStatement.setString(2, bucaStradaleDaSegnalare.profondita);
        preparedStatement.setString(3, UtilityAccesso.getCodiceUtente());
        preparedStatement.executeUpdate();
        System.out.println("sono una sout in buca stradale dao impl e la buca e' stata segnalata con successo");
    }

}
