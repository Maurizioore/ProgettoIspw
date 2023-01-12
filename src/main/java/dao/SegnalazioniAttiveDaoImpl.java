package dao;

import eccezioni.NonEsistonoSegnalazioniException;
import queries.QueriesAccessoAlSistema;
import queries.QueriesPaloIlluminazione;
import utilityaccesso.UtilityAccesso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SegnalazioniAttiveDaoImpl implements SegnalazioniAttiveDao{
    //come tutti i dao deve aprire una connessione
    private Connection connection;
    private void verificaConnessione() throws SQLException {
        if(connection==null){
            connection=SingletonConnessione.getInstance();
        }
    }
    public SegnalazioniAttiveDaoImpl() throws SQLException {
        //il costruttore non fa altro che aprire o prendere una connessione
        connection=SingletonConnessione.getInstance();
    }

    @Override
    public void cercaSegnalazioniAttive(List<String> listaPaliSegnalati,List<String> listaIndirizziDiQueiPali, List<String> listaStatoDellaSegnalazione) throws SQLException, NonEsistonoSegnalazioniException {
        //verifico prima che la connessione sia ancora aperta( magari qualcun altro dao l'ha chiusa
        verificaConnessione();
        //ora devo richiedere al sistema le segnalazioni dell'utente
        PreparedStatement preparedStatement=connection.prepareStatement(QueriesPaloIlluminazione.queriesMostraSegnalazioniEffettuate());
        preparedStatement.setString(1, UtilityAccesso.getCodiceUtente());
        ResultSet rs=preparedStatement.executeQuery();
        //ora devo vedere se la l'utente ha segnalato veramente qualcosa
        //isBeforeFirst ritorna true se il result set contiene qualcosa, quindi se l'utente ha effettuato la segnalazione
        if(!rs.isBeforeFirst()){
            throw new NonEsistonoSegnalazioniException("Non hai effettuato nessuna segnalazione");
        }
        //il result set contiene quindi qualcosa, questo qualcosa lo prendo e lo inserisco nella lista
        while(rs.next()){
            listaPaliSegnalati.add(rs.getString("numeroSeriale"));
            listaIndirizziDiQueiPali.add(rs.getString("indirizzo"));
            listaStatoDellaSegnalazione.add(rs.getString("stato"));
        }
    }
}
