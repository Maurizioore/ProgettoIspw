package dao;

import eccezioni.ErroreLetturaPasswordException;
import eccezioni.SegnalazioneGiaAvvenutaException;
import entita.EntitaStradale;
import entita.PaloIlluminazione;
import queries.QueriesPaloIlluminazione;
import queries.QueriesSegnalazioneBucaStradale;
import utilityaccesso.UtilityAccesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntitaStradaleDaoImpl implements EntitaStradaleDao {
    // il dao si deve preccupare di aprire la connessione
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;


    public EntitaStradaleDaoImpl() throws SQLException, ErroreLetturaPasswordException {
        connection=SingletonConnessione.getInstance();
    }

    @Override
    public void saveEntitaStradale(EntitaStradale instance) throws SQLException, SegnalazioneGiaAvvenutaException, ErroreLetturaPasswordException {
        //verifico perima che la connessione con il db sia aperta
        verificaConnessione();
        //quindi qui passo un tipo Entita stradale e la salvo nel sistema
        //vedo prima se l'utente che la sta salvando e' un utente registrato
        //vedo se il palo e' già presente o no nel db, se lo e' comunico che gi esiste nel db, altrimenti lo salvo nel db
        if (!cercaEntita(instance)) {
            if (UtilityAccesso.getCodiceUtente() != null) {
                //sono un utente registrato che la salva
                //devo poi vedere che tipo di entità stradale sto salvando, se un palo o una buca
                if (instance.getClass() == PaloIlluminazione.class) {
                    //sono un utente loggato e sto segnalando un palo
                    preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.queriesSalvaPaloAdUnUtenteDelSistema());
                    preparedStatement.setString(1, instance.infoEntita());
                    preparedStatement.setString(2, instance.indirizzo);
                    preparedStatement.setString(3, UtilityAccesso.getCodiceUtente());
                    preparedStatement.executeUpdate();
                } else {
                    //sono un utente loggato e sto segnalando una buca
                    preparedStatement = connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesSalvaBuca());
                    preparedStatement.setString(1, instance.getIndirizzo());
                    preparedStatement.setString(2, instance.infoEntita());
                    preparedStatement.setString(3, UtilityAccesso.getCodiceUtente());
                    preparedStatement.executeUpdate();
                }
            } else {
                //gli utenti non loggati possono segnalare solo i pali e non le buche
                if (instance.getClass() == PaloIlluminazione.class) {
                    //sono un utente non loggato e sto segnalando un palo;
                    preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.queriesSalvaPalo());
                    preparedStatement.setString(1, instance.infoEntita());
                    preparedStatement.setString(2, instance.indirizzo);
                    preparedStatement.executeUpdate();
                }
            }
        }else {
            throw new SegnalazioneGiaAvvenutaException("il palo è stato già segnalato");
        }
    }
    private void verificaConnessione() throws SQLException, ErroreLetturaPasswordException {
        if(connection==null){
            new EntitaStradaleDaoImpl();
        }
    }
    private boolean cercaEntita(EntitaStradale entitaStradale) throws SQLException, ErroreLetturaPasswordException {
        //verifichiamo che la connessione sia aperta prima
        verificaConnessione();
        //vedo prima di che tipo e' l'entita e in base al tipo lancio la giusta query
        if(entitaStradale.getClass()==PaloIlluminazione.class) {
            preparedStatement = connection.prepareStatement(QueriesPaloIlluminazione.cercaPaloIlluminazione());
            preparedStatement.setString(1, entitaStradale.infoEntita());
            preparedStatement.setString(2, entitaStradale.indirizzo);
            rs = preparedStatement.executeQuery();
            return rs.isBeforeFirst();
        }
        //se passo una buca devo controllare se la buca e' stata gia segnalata
        //fallo dopo che hai fatto tutto sulla buca, per ora gli faccio tornare false
        return false;
    }
}
