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
    //dao che si preoccupa di salvare l'entita stradale, che in questo caso e' una  buca nel database
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    //variabile che viene impostata a 0 se il salvataggio di una buca nel db e' andato a buon fine,
    //altrimenti viene impostata a -1
    private int esito;
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
        BucaStradale bucaStradaleDaSegnalare = new BucaStradale(instance.infoEntita(), instance.getIndirizzo());
        //verifico se la buca che sto segnalando e' gia presente nel db( cioe' se c'e' già un indirizzo che ha
        //ricevuto quella segnalazione
        //cerca buca torna true se la buca è gia presente, false se non lo è
        if (!cercaBuca(bucaStradaleDaSegnalare)) {
            //la buca non è presente nel db, posso quindi  inviarla
            preparedStatement = connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesSalvaBuca());
            preparedStatement.setString(1, bucaStradaleDaSegnalare.indirizzo);
            preparedStatement.setString(2, bucaStradaleDaSegnalare.profondita);
            preparedStatement.setString(3, UtilityAccesso.getCodiceUtente());
            preparedStatement.executeUpdate();
            System.out.println("sono una sout in buca stradale dao impl e la buca e' stata segnalata con successo");
            esito = 0;
        }else{
            //la buca è presente nel db devo quindi comunicarlo all'utente
            esito=-1;
            throw new SegnalazioneGiaAvvenutaException("la buca e' stata già segnalata da un altro utente");
        }
    }
    private boolean cercaBuca(BucaStradale bucaStradale) throws SQLException, ErroreLetturaPasswordException {
        //ritorna true se c'e 'una segnalazione già effettuata a quell'indirizzo, false altrimenti
        verificaConnessione();
        preparedStatement = connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesVediSeLeBucheAQuellIndirizzoSonoStateGiaSegnalate());
        preparedStatement.setString(1, bucaStradale.getIndirizzo());
        resultSet = preparedStatement.executeQuery();
        //isBeforeFirst ritorna true se il result set contiene qualcosa, false altrimenti
        return resultSet.isBeforeFirst();
    }
    public int getEsito(){
        return this.esito;
    }

}
