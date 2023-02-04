package dao;

import com.example.progettoispw.controllergrafici.Type_of_segnalazione;
import eccezioni.ErroreLetturaPasswordException;
import eccezioni.NonEsistonoSegnalazioniException;
import queries.QueriesPaloIlluminazione;
import queries.QueriesSegnalazioneBucaStradale;
import utilityaccesso.UtilityAccesso;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SegnalazioniAttiveRisolteDaoImpl implements SegnalazioniRisolteAttiveDao{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet rs;
    private ResultSet rs2;
    private void verificaConnessione() throws SQLException, ErroreLetturaPasswordException {
        if(connection==null){
            connection=SingletonConnessione.getInstance();
        }
    }
    public SegnalazioniAttiveRisolteDaoImpl() throws SQLException, ErroreLetturaPasswordException {
        //il costruttore non fa altro che aprire o prendere una connessione
        connection=SingletonConnessione.getInstance();
    }

    @Override
    public void cercaSegnalazioni(List<String> listaPaliSegnalati, List<String> listaIndirizziDiQueiPali, List<String> listaStatoDellaSegnalazione, List<String> listaProfonfitaBucheSegnalate, List<String> listaIndirizziBuche, List<String> listaStatoBuche, Type_of_segnalazione type_of_segnalazione) throws SQLException, NonEsistonoSegnalazioniException, ErroreLetturaPasswordException {
        //verifico prima che la connessione sia ancora aperta( magari qualcun altro dao l'ha chiusa
        verificaConnessione();
        //ora in base al tipo faccio le dovute richieste
        if(type_of_segnalazione==Type_of_segnalazione.ATTIVE){
            preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.queriesMostraSegnalazioniEffettuate());
            preparedStatement.setString(1, UtilityAccesso.getCodiceUtente());
            rs=preparedStatement.executeQuery();
            preparedStatement=connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesMostraSegnalazioniEffettuate());
            preparedStatement.setString(1,UtilityAccesso.getCodiceUtente());
            rs2=preparedStatement.executeQuery();
            //ora devo vedere se la l'utente ha segnalato veramente qualcosa
            //isBeforeFirst ritorna true se il result set contiene qualcosa, quindi se l'utente ha effettuato la segnalazione
            if(!rs.isBeforeFirst() && !rs2.isBeforeFirst()){
                throw new NonEsistonoSegnalazioniException("Non hai effettuato nessuna segnalazione");
            }
        }else{
            //l'utente ha richiesto le risolte
            preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.queriesMostraSegnalazioniCompletate());
            preparedStatement.setString(1, UtilityAccesso.getCodiceUtente());
            rs=preparedStatement.executeQuery();
            preparedStatement=connection.prepareStatement(QueriesSegnalazioneBucaStradale.queriesMostraSegnalazioniCompletate());
            preparedStatement.setString(1,UtilityAccesso.getCodiceUtente());
            rs2=preparedStatement.executeQuery();
            //ora devo vedere se la l'utente ha segnalato veramente qualcosa
            //isBeforeFirst ritorna true se il result set contiene qualcosa, quindi se l'utente ha effettuato la segnalazione
            if(!rs.isBeforeFirst() && !rs2.isBeforeFirst()){
                throw new NonEsistonoSegnalazioniException("Nessuna segnalazione completata");
            }
        }
        //ora devo richiedere al sistema le segnalazioni dell'utente
        //uno dei due result set contiene qualcosa, mostriamo questo qualcosa
        //iniziamo dai pali
        if(rs.isBeforeFirst()) {
            //se è true is before first vuol dire che almeno una buca e' stata segnalata
            while (rs.next()) {
                listaPaliSegnalati.add(rs.getString("numeroSeriale"));
                listaIndirizziDiQueiPali.add(rs.getString("indirizzo"));
                listaStatoDellaSegnalazione.add(rs.getString("stato"));
            }
        }
        //passiamo ora a scorrere le buche
        if(rs2.isBeforeFirst()){
            while (rs2.next()){
                listaProfonfitaBucheSegnalate.add(rs2.getString("profondità"));
                listaIndirizziBuche.add(rs2.getString("indirizzo"));
                listaStatoBuche.add(rs2.getString("stato"));
            }
        }
        //ho finito non lancio nessuna eccezione, quindi l'utente ha segnalato qualcosa, e quel qualcosa e' stato
        //aggiunto alle liste presenti nel bean, ora il controllo ritorna al controller grafico segnalazioni attive che pensera
        //a prendere i dati inseriti nelle liste dei bean
    }
}
